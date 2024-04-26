package com.mafraq.presentation.features.search

import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.data.repository.map.MapPlacesRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.extensions.emptyString
import com.mafraq.presentation.utils.location.LocationSettingsDelegate
import com.mafraq.presentation.utils.location.LocationSettingsDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mapPlacesRepository: MapPlacesRepository,
    private val locationSettingsDelegate: LocationSettingsDelegateImpl
) : BaseViewModel<SearchUiState, SearchEvent>(SearchUiState()), SearchInteractionListener,
    LocationSettingsDelegate by locationSettingsDelegate {

    var mapDestination: Location = Location()

    override fun navigateToMap() {
        emitNewEvent(SearchEvent.NavigateToMap)
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
        updateState { copy(isLoading = true) }
        tryToExecute(
            block = { mapPlacesRepository.selectSuggestedPlace(place) },
            onSuccess = { selectedPlace ->
                mapDestination = selectedPlace.location
                navigateToMap()
            },
            onError = {
                // TODO: Handle error
            },
            onCompleted = {
                updateState { copy(isLoading = false) }
            }
        )
    }

    override fun navigateBack() {
        emitNewEvent(SearchEvent.NavigateBack)
    }

    override fun onClearSearch() = updateState {
        copy(searchQuery = emptyString())
    }
}
