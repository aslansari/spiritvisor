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
import spiritvisor.composeapp.generated.resources.Res

class CocktailViewModel : BaseViewModel<CocktailUIState>() {

    val cocktailService = CocktailService()
    var cocktailsByFlavor: Map<String, List<CocktailDTO>> = emptyMap()
    var args: CocktailArgs? = null

    override fun createInitialState(): CocktailUIState = CocktailUIState()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            cocktailsByFlavor = cocktailService.fetchCocktailsByFlavor().flavors
        }
    }

    fun updateArgs(args: CocktailArgs) {
        this.args = args
        setState { copy(category = args.category, selectedIndex = -1) }
        viewModelScope.launch(Dispatchers.Default) {
            selectCocktailByFlavor(args.category)
        }
    }

    private fun selectCocktailByFlavor(flavor: String) {
        val cocktails = cocktailsByFlavor[flavor]
        if (!cocktails.isNullOrEmpty()) {

            setState { copy(showSuggestAnother = cocktails.size > 1) }

            val randomIndex = randomExcept(currentState.selectedIndex, cocktails.lastIndex)
            setState { copy(selectedIndex = randomIndex) }
            val cocktail = cocktails[randomIndex]
            setState {
                copy(
                    title = cocktail.title,
                    cocktailImageUrl = cocktail.image,
                )
            }
        } else {
            setState { copy(title = "Not Found", loading = false) }
        }
    }

    private fun randomExcept(except: Int, size: Int): Int {
        val random = (0 .. size).filter { it != except }.random()
        return random
    }

    fun suggestAnother() {
        viewModelScope.launch(Dispatchers.Default) {
            args?.let {
                selectCocktailByFlavor(it.category)
            }
        }
    }
}

class CocktailService(
    private val client: HttpClient = HttpClient(),
) {
    suspend fun fetchCocktail(category: String): String {
        val response = client.get("https://hacker-news.firebaseio.com/v0/item/40358041.json?print=pretty")
        val string = Json { ignoreUnknownKeys = true }.decodeFromString(response.bodyAsText()) as Response
        return string.title
    }

    suspend fun fetchCocktailsByFlavor(): CocktailsByFlavorResponse {
        val bytes = Res.readBytes("files/cocktails_by_flavor.json")
        return Json { ignoreUnknownKeys = true }.decodeFromString(bytes.decodeToString())
    }
}

@Serializable
data class Response(
    val type: String,
    val title: String,
    val score: Int,
)

@Serializable
data class CocktailsByFlavorResponse(
    val flavors: Map<String, List<CocktailDTO>>
)

@Serializable
data class CocktailDTO(
    val title: String,
    val ingredients: List<String>,
    val image: String,
    val description: String,
)

@Stable
data class CocktailUIState(
    val loading: Boolean = false,
    val category: String = "",
    val title: String = "",
    val cocktailImageUrl: String = "",
    val selectedIndex: Int = -1,
    val showSuggestAnother: Boolean = false,
) : UIState