package com.mafraq.presentation.features.profile


import androidx.lifecycle.SavedStateHandle
import com.mafraq.data.entities.AppUserType
import com.mafraq.data.entities.profile.DayOff
import com.mafraq.data.entities.profile.Gender
import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.data.repository.map.MapPlacesRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.navigation.arguments.ProfileScreenArgs
import com.mafraq.presentation.utils.location.LocationSettingsDelegate
import com.mafraq.presentation.utils.location.LocationSettingsDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userType: AppUserType,
    private val crmRepository: CRMRepository,
    private val authRepository: AuthRepository,
    private val placesRepository: MapPlacesRepository,
    private val locationSettingsDelegate: LocationSettingsDelegateImpl
) : BaseViewModel<ProfileUiState, ProfileEvent>(ProfileUiState()),
    LocationSettingsDelegate by locationSettingsDelegate,
    ProfileInteractionListener {

    private val args by lazy { ProfileScreenArgs(savedStateHandle) }


    init {
        initialize()
    }

    override fun onLogout() {
        authRepository.logout()
        emitNewEvent(ProfileEvent.OnLogout)
    }

    override fun onSave() {
        tryToExecute(
            block = {
                updateState {
                    copy(isLoading = true)
                }
                val saveProfile = if (userType.isDriverApp)
                    crmRepository::saveDriverProfile
                else
                    crmRepository::saveEmployeeProfile
                saveProfile(state.value.toProfile())
            },
            onSuccess = {
                updateState {
                    copy(error = null)
                }
                emitNewEvent(ProfileEvent.OnNavigateToHome)
            },
            onError = {
                updateState {
                    copy(error = it)
                }
            },
            onCompleted = {
                updateState {
                    copy(isLoading = false)
                }
            }
        )
    }

    override fun setEmail(value: String) = updateState { copy(email = value, error = null) }

    override fun onHomeAddressClicked() {
        emitNewEvent(ProfileEvent.OnNavigateToMapForHomeAddress())
    }

    override fun onWorkAddressClicked() {
        emitNewEvent(ProfileEvent.OnNavigateToMapForWorkAddress())
    }

    override fun setPhone(value: String) = updateState { copy(phone = value, error = null) }

    override fun setFullname(value: String) = updateState { copy(fullName = value, error = null) }

    override fun validateFields(): Boolean {
        return if (userType.isDriverApp)
            validateDriverFields()
        else
            validateEmployeeFields()
    }

    override fun setGender(value: Gender) = updateState {
        copy(gender = value)
    }

    override fun setOffDays(selected: Boolean, offDay: DayOff) = updateState {
        val offDays = offDays.toMutableSet()
        if (selected)
            offDays.add(offDay)
        else
            offDays.remove(offDay)
        copy(offDays = offDays)
    }

    override fun setBirthday(value: String) = updateState {
        copy(birthday = value)
    }

    override fun setNationalId(value: String) = updateState {
        copy(nationalId = value)
    }

    override fun setCarName(value: String) = updateState {
        copy(carName = value)
    }

    override fun setCarNumber(value: String) = updateState {
        copy(carNumber = value)
    }

    override fun setSnippet(value: String) = updateState {
        copy(snippet = value)
    }

    private fun initialize() {
        if (userType.isDriverApp)
            driverInitialize()
        else
            employeeInitialize()

        if (args.addressId == null) return

        tryToExecute(
            block = {
                placesRepository.getLocationInfo(
                    args.latitude!!.toDouble(),
                    args.longitude!!.toDouble()
                )
            },
            onSuccess = {
                updateState {
                    if (args.addressId == ProfileEvent.OnNavigateToMapForHomeAddress().id)
                        copy(
                            homeLocation = it
                        )
                    else
                        copy(
                            workLocation = it
                        )
                }
            }
        )
    }

    private fun driverInitialize() {
        tryToExecute(
            block = { crmRepository.getDriver() },
            onSuccess = {
                runCatching {
                    updateState {
                        copy(
                            email = it.email,
                            fullName = it.fullName,
                            birthday = it.birthday,
                            gender = Gender.fromString(it.gender),
                            homeLocation = it.location,
                            carName = it.carName,
                            carNumber = it.carNumber,
                            snippet = it.snippet,
                            nationalId = it.nationalId,
                            phone = it.phone,
                            error = null
                        )
                    }
                }.onFailure {
                    Timber.e(it)
                }
            },
        )
    }

    private fun employeeInitialize() {
        tryToExecute(
            block = { crmRepository.getEmployee() },
            onSuccess = {
                runCatching {
                    updateState {
                        copy(
                            email = it.email,
                            fullName = it.fullName,
                            birthday = it.birthday,
                            gender = Gender.fromString(it.gender),
                            workLocation = it.workLocation,
                            homeLocation = it.homeLocation,
                            offDays = it.offDays.toSet(),
                            phone = it.phone,
                            error = null
                        )
                    }
                }.onFailure {
                    Timber.e(it)
                }
            },
        )
    }

    private fun validateEmployeeFields(): Boolean = state.value.run {
        listOf(
            email,
            workLocation.formattedAddress,
            homeLocation.formattedAddress,
            phone,
            fullName,
            birthday
        ).all(String::isNotEmpty)
                && gender != null
                && offDays.isNotEmpty()
    }

    private fun validateDriverFields(): Boolean = state.value.run {
        listOf(
            email,
            carName,
            carNumber,
            snippet,
            nationalId,
            homeLocation.formattedAddress,
            phone,
            fullName,
            birthday
        ).all(String::isNotEmpty) && gender != null
    }
}

