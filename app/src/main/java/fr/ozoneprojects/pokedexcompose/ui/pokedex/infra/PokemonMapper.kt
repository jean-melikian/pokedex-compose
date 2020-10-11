package fr.ozoneprojects.pokedexcompose.ui.pokedex.infra

import fr.ozoneprojects.pokedexcompose.ui.pokedex.app.utils.PokemonUtils.Companion.getTypeColor
import fr.ozoneprojects.pokedexcompose.ui.pokedex.domain.PokemonTypeUi
import fr.ozoneprojects.pokedexcompose.ui.pokedex.domain.PokemonUi
import me.sargunvohra.lib.pokekotlin.model.Pokemon

fun Pokemon.mapToUi(): PokemonUi =
    PokemonUi(
        id,
        name,
        order,
        types.map { PokemonTypeUi(it.type.name, getTypeColor(it.type.name)) },
        sprites.frontDefault ?: ""
    )