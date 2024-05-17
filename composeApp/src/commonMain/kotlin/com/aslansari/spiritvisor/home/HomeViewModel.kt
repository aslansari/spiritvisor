package com.aslansari.spiritvisor.home

import androidx.lifecycle.viewModelScope
import com.aslansari.spiritvisor.BaseViewModel
import com.aslansari.spiritvisor.UIState
import com.aslansari.spiritvisor.cocktail.CocktailDTO
import com.aslansari.spiritvisor.cocktail.CocktailService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel<HomeUiState>() {
    val cocktailService = CocktailService()
    var cocktailsByFlavor: Map<String, List<CocktailDTO>> = emptyMap()
    override fun createInitialState(): HomeUiState = HomeUiState()
    init {
        viewModelScope.launch(Dispatchers.Default) {
            setState { copy(loading = true) }
            cocktailsByFlavor = cocktailService.fetchCocktailsByFlavor().flavors
            setState { copy(flavors = cocktailsByFlavor.keys.toList()) }
            setState { copy(loading = false) }
        }
    }
}

data class HomeUiState(
    val loading: Boolean = false,
    val flavors: List<String> = emptyList(),
) : UIState