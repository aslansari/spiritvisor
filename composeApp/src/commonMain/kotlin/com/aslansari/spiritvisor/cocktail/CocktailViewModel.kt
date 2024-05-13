package com.aslansari.spiritvisor.cocktail

import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.aslansari.spiritvisor.BaseViewModel
import com.aslansari.spiritvisor.UIState
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class CocktailViewModel : BaseViewModel<CocktailUIState>() {

    val cocktailService = CocktailService()

    override fun createInitialState(): CocktailUIState = CocktailUIState()

    fun updateArgs(args: CocktailArgs) {
        setState { copy(category = args.category) }
        viewModelScope.launch(Dispatchers.Default) {
            val title = cocktailService.fetchCocktail(args.category)
            setState { copy(title = title) }
        }
    }
}

class CocktailService(
    private val client: HttpClient = HttpClient(),
) {
    suspend fun fetchCocktail(category: String): String {
        val response = client.get("https://hacker-news.firebaseio.com/v0/item/8863.json?print=pretty")
        val string = Json { ignoreUnknownKeys = true }.decodeFromString(response.bodyAsText()) as Response
        return string.title
    }
}

@Serializable
data class Response(
    val type: String,
    val title: String,
    val score: Int,
)

@Stable
data class CocktailUIState(
    val loading: Boolean = false,
    val category: String = "",
    val title: String = "",
) : UIState