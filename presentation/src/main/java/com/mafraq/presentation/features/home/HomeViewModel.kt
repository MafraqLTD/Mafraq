package com.mafraq.presentation.features.home

import com.altaie.prettycode.core.utils.extenstions.isTrue
import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.data.repository.map.MapPlacesRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.extensions.emptyString
import com.mafraq.presentation.utils.location.LocationSettingsDelegate
import com.mafraq.presentation.utils.location.LocationSettingsDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val crmRepository: CRMRepository,
    private val mapPlacesRepository: MapPlacesRepository,
    private val locationSettingsDelegate: LocationSettingsDelegateImpl
) : BaseViewModel<HomeUiState, HomeEvent>(HomeUiState()), HomeInteractionListener,
    LocationSettingsDelegate by locationSettingsDelegate {

    var mapDestination: Location = Location()

    init {
        initialization()
    }

    override fun navigateToMap() {
        emitNewEvent(HomeEvent.NavigateToMap)
    }

    override fun navigateToSupportChat() {
        emitNewEvent(HomeEvent.NavigateToSupportChat)
    }

    override fun onSearchQueryChange(value: String) {
        updateState { copy(searchQuery = value) }

        tryToExecute(
            block = { mapPlacesRepository.getPlaceSuggestions(query = value) },
            onSuccess = {
                updateState { copy(placesSuggestions = it) }
            }
        )
    }

    override fun onSelectPlace(place: PlaceSuggestion) {
        tryToExecute(
            block = { mapPlacesRepository.selectSuggestedPlace(place) },
            onSuccess = { selectedPlace ->
                mapDestination = selectedPlace.location
                navigateToMap()
            },
            onError = {
                // TODO: Handle error
            }
        )
    }

    override fun onClearSearch() = updateState {
        copy(searchQuery = emptyString())
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
