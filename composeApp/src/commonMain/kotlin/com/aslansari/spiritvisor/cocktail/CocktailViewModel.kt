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

    override fun createInitialState(): CocktailUIState = CocktailUIState()

    fun updateArgs(args: CocktailArgs) {
        setState { copy(loading = true) }
        setState { copy(category = args.category) }
        viewModelScope.launch(Dispatchers.Default) {
//            val title = cocktailService.fetchCocktail(args.category)
            val cocktailsByFlavor = cocktailService.fetchCocktailsByFlavor()
            val cocktails = cocktailsByFlavor.flavors[args.category]
            if (cocktails != null) {
                val randomIndex = cocktails.indices.random()
                val cocktail = cocktails[randomIndex]
                setState { copy(title = cocktail.title, loading = false) }
            } else {
//                setState { copy(title = title, loading = false) }
                setState { copy(title = "Not Found", loading = false) }
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
) : UIState