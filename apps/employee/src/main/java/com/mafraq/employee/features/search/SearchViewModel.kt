package com.mafraq.employee.features.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.data.repository.map.MapPlacesRepository
import com.mafraq.employee.navigation.arguments.SearchScreenArgs
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.extensions.emptyString
import com.mafraq.presentation.utils.location.LocationSettingsDelegate
import com.mafraq.presentation.utils.location.LocationSettingsDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val mapPlacesRepository: MapPlacesRepository,
    private val locationSettingsDelegate: LocationSettingsDelegateImpl
) : BaseViewModel<SearchUiState, SearchEvent>(SearchUiState()), SearchInteractionListener,
    LocationSettingsDelegate by locationSettingsDelegate {

    private val args by lazy { SearchScreenArgs(savedStateHandle) }
    private val searchQueryFlow = MutableStateFlow(emptyString())

    var mapDestination: Location = Location()
    val isFromProfile: Boolean
        get() = args.isFromProfile

    override fun navigateToMap() {
        emitNewEvent(SearchEvent.NavigateToMap)
    }

    override fun onSearchQueryChange(value: String) {
        searchQueryFlow.value = value
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

    override fun onClearSearch() {
        searchQueryFlow.value = emptyString()
    }

    private fun onSearch(query: String) {
        Timber.d("onSearch: $query")
        tryToExecute(
            block = { mapPlacesRepository.getPlaceSuggestions(query = query) },
            onSuccess = {
                updateState { copy(placesSuggestions = it) }
            }
        )
    }

    @OptIn(FlowPreview::class)
    private fun searchObserving() = viewModelScope.launch {
        searchQueryFlow.debounce(KEYBOARD_STOP_TYPING_WAITING_TIME)
            .map { it.trim() }
            .filter { it.length >= MINIMUM_SEARCH_QUERY_SIZE }
            .distinctUntilChanged()
            .collect(::onSearch)
    }

    init {
        initialize()
    }

    private fun initialize() {
        searchObserving()
        updateState { copy(searchQuery = searchQueryFlow) }
    }

    private companion object {
        const val MINIMUM_SEARCH_QUERY_SIZE = 3
        const val KEYBOARD_STOP_TYPING_WAITING_TIME = 750L
    }
}
