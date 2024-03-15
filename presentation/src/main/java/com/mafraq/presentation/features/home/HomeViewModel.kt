package com.mafraq.presentation.features.home

import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.extensions.emptyString
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel<HomeUiState, HomeEvent>(HomeUiState()), HomeInteractionListener {
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
}
