package fr.ozoneprojects.pokedexcompose.ui.pokedex.domain

import androidx.annotation.ColorInt

data class PokemonUi(
    val id: Int,
    val name: String,
    val order: Int,
    val types: List<PokemonTypeUi>,
    val spriteUri: String,
)

data class PokemonTypeUi(
    val name: String,
    @ColorInt val color: Int
)