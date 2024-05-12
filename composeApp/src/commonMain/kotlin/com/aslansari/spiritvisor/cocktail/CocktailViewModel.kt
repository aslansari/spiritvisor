package com.aslansari.spiritvisor.cocktail

import androidx.compose.runtime.Stable
import com.aslansari.spiritvisor.BaseViewModel
import com.aslansari.spiritvisor.UIState

class CocktailViewModel : BaseViewModel<CocktailUIState>() {

    override fun createInitialState(): CocktailUIState = CocktailUIState()
}

@Stable
data class CocktailUIState(
    val loading: Boolean = false,
) : UIState