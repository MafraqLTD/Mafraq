package com.mafraq.presentation.features.home

import com.altaie.prettycode.core.utils.extenstions.isTrue
import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.data.repository.map.MapPlacesRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.features.search.SearchInteractionListener.Preview.onClearSearch
import com.mafraq.presentation.utils.extensions.emptyString
import com.mafraq.presentation.utils.location.LocationSettingsDelegate
import com.mafraq.presentation.utils.location.LocationSettingsDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val crmRepository: CRMRepository,
    private val locationSettingsDelegate: LocationSettingsDelegateImpl
) : BaseViewModel<HomeUiState, HomeEvent>(HomeUiState()), HomeInteractionListener,
    LocationSettingsDelegate by locationSettingsDelegate {

    init {
        initialization()
    }

    override fun navigateToMap() {
        emitNewEvent(HomeEvent.NavigateToMap)
    }

    override fun onFindDriver() {
        navigateToMap()
    }

    override fun navigateToSearch() {
        emitNewEvent(HomeEvent.NavigateToSearch)
    }

    override fun navigateToSupportChat() {
        emitNewEvent(HomeEvent.NavigateToSupportChat)
    }

    val isEmailVerified: Boolean
        get() = authRepository.getUserInfo()?.isEmailVerified.isTrue

    fun onVerificationDone() {
        authRepository.isAuthorized()
    }

    private fun initialization() {
        tryToExecute(
            block = crmRepository::getAds,
            onSuccess = {
                updateState {
                    copy(ads = it)
                }
            },
        )
    }
}
