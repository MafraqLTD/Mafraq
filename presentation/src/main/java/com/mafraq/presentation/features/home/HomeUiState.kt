package com.mafraq.presentation.features.home

import com.mafraq.presentation.features.home.components.AdModel
import com.mafraq.presentation.utils.dummyData.Dummy.dummyAds
import com.mafraq.presentation.utils.extensions.emptyString


data class HomeUiState(
    val ads: List<AdModel> = dummyAds,
    val searchQuery: String = emptyString(),
)
