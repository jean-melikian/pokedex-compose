package fr.ozoneprojects.pokedexcompose.ui.pokedex.infra

import fr.ozoneprojects.pokedexcompose.ui.pokedex.domain.PokedexRepository
import fr.ozoneprojects.pokedexcompose.ui.pokedex.domain.PokemonUi
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient

class PokedexRepositoryImpl : PokedexRepository {
    override suspend fun getAll(): List<PokemonUi> {
        return pokeApiClient.getPokemonList(0, 20).let { resList ->
            resList.results.map { res ->
                pokeApiClient.getPokemon(res.id).mapToUi()
            }
        }
    }

    companion object {
        private val pokeApiClient by lazy { PokeApiClient() }
    }
}

