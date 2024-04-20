package com.mafraq.presentation.features.profile


import androidx.lifecycle.SavedStateHandle
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
        // TODO("Not yet implemented")
    }

    override fun setEmail(value: String) = updateState { copy(email = value, error = null) }

    override fun setHomeAddress(value: String) =
        updateState { copy(homeAddress = value, error = null) }

    override fun setWorkAddress(value: String) =
        updateState { copy(workAddress = value, error = null) }

    override fun setPhone(value: String) = updateState { copy(phone = value, error = null) }

    override fun setFullname(value: String) = updateState { copy(fullname = value, error = null) }

    override fun validateFields(): Boolean = state.value.run {
        listOf(
            email,
            workAddress,
            homeAddress,
            phone,
            fullname
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

