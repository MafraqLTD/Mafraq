package com.mafraq.presentation.features.profile


import androidx.lifecycle.SavedStateHandle
import com.mafraq.data.entities.profile.DayOff
import com.mafraq.data.entities.profile.Gender
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.data.repository.map.MapPlacesRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.navigation.arguments.ProfileScreenArgs
import com.mafraq.presentation.utils.location.LocationSettingsDelegate
import com.mafraq.presentation.utils.location.LocationSettingsDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val crmRepository: CRMRepository,
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
        emitNewEvent(ProfileEvent.OnLogout)
    }

    override fun onSave() {
        tryToExecute(
            block = {
                updateState {
                    copy(isLoading = true)
                }
                crmRepository.saveProfile(state.value.toProfile())
            },
            onSuccess = {
                updateState {
                    copy(error = null)
                }
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

    override fun validateFields(): Boolean = state.value.run {
        listOf(
            email,
            workLocation.formattedAddress,
            homeLocation.formattedAddress,
            phone,
            fullName
        ).all(String::isNotEmpty)
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

    private fun initialize() {
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
}

