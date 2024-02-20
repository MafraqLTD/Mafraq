package com.mafraq.presentation.features.profile


import com.mafraq.presentation.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(

) : BaseViewModel<ProfileUiState, ProfileEvent>(ProfileUiState()), ProfileInteractionListener {
    override fun onLogout() {
        emitNewEvent(ProfileEvent.OnLogout)
    }

    override fun onSave() {
        // TODO("Not yet implemented")
    }

    override fun setEmail(value: String) = updateState { copy(email = value, error = null) }

    override fun setAddress(value: String) = updateState { copy(address = value, error = null) }

    override fun setUsername(value: String) = updateState { copy(username = value, error = null) }

    override fun setPhone(value: String) = updateState { copy(phone = value, error = null) }

    override fun setFullname(value: String) = updateState { copy(fullname = value, error = null) }

    override fun validateFields(): Boolean = state.value.run {
        listOf(
            email,
            username,
            address,
            phone,
            fullname
        ).all(String::isNotEmpty)
    }
}
