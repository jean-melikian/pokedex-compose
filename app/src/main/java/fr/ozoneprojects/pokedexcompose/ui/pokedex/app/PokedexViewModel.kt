package fr.ozoneprojects.pokedexcompose.ui.pokedex.app

import androidx.lifecycle.*
import fr.ozoneprojects.pokedexcompose.ui.pokedex.domain.PokedexRepository
import fr.ozoneprojects.pokedexcompose.ui.pokedex.domain.PokemonUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokedexViewModelFactory(private val pokedexRepository: PokedexRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(PokedexRepository::class.java)
            .newInstance(pokedexRepository)

}

class PokedexViewModel(private val pokedexRepository: PokedexRepository) : ViewModel() {
    private val pokedexEntries = MutableLiveData<List<PokemonUi>>()
    fun pokedexEntries(): LiveData<List<PokemonUi>> = pokedexEntries

    fun getAll() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val result = pokedexRepository.getAll()
            withContext(Dispatchers.Main) {
                pokedexEntries.value = result
            }
        }
    }
}

