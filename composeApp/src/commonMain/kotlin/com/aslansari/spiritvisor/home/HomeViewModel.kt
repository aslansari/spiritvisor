package com.aslansari.spiritvisor.home

import com.aslansari.spiritvisor.BaseViewModel
import com.aslansari.spiritvisor.UIState

class HomeViewModel : BaseViewModel<HomeUiState>() {
    override fun createInitialState(): HomeUiState = HomeUiState()
}

data class HomeUiState(
    val title: String = "Home"
) : UIState