package fr.ozoneprojects.pokedexcompose.ui.pokedex.domain

interface PokedexRepository {
    suspend fun getAll(): List<PokemonUi>
}

