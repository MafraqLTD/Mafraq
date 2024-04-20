package com.mafraq.presentation.features.profile


import androidx.lifecycle.SavedStateHandle
import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.profile.DayOff
import com.mafraq.data.entities.profile.Gender
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.presentation.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val crmRepository: CRMRepository,
) : BaseViewModel<ProfileUiState, ProfileEvent>(ProfileUiState()), ProfileInteractionListener {
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

    override fun setHomeAddress(value: Location) =
        updateState { copy(homeLocation = value, error = null) }

    override fun setWorkAddress(value: Location) =
        updateState { copy(workLocation = value, error = null) }

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
}

