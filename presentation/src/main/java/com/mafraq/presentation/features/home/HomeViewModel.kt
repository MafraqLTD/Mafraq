package com.mafraq.presentation.features.home

import com.altaie.prettycode.core.utils.extenstions.isTrue
import com.mafraq.data.repository.user.UserRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.extensions.emptyString
import com.mafraq.presentation.utils.location.LocationSettingsDelegate
import com.mafraq.presentation.utils.location.LocationSettingsDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val locationSettingsDelegate: LocationSettingsDelegateImpl
) : BaseViewModel<HomeUiState, HomeEvent>(HomeUiState()), HomeInteractionListener,
    LocationSettingsDelegate by locationSettingsDelegate {
    override fun navigateToMap() {
        emitNewEvent(HomeEvent.NavigateToMap)
    }

    override fun navigateToSupportChat() {
        emitNewEvent(HomeEvent.NavigateToSupportChat)
    }

    override fun onSearchQueryChange(value: String) = updateState {
        copy(searchQuery = value)
    }

    override fun onClearSearch() = updateState {
        copy(searchQuery = emptyString())
    }

    val isEmailVerified: Boolean
        get() = userRepository.getUserInfo()?.isEmailVerified.isTrue

    fun onVerificationDone() {
        userRepository.isAuthorized()
    }
}
