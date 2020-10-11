package fr.ozoneprojects.pokedexcompose.ui.pokedex.app.utils

import androidx.compose.ui.graphics.toArgb
import fr.ozoneprojects.pokedexcompose.ui.*
import fr.ozoneprojects.pokedexcompose.ui.pokedex.domain.PokemonTypeUi
import fr.ozoneprojects.pokedexcompose.ui.pokedex.domain.PokemonUi
import java.util.*

class PokemonUtils {
    companion object {
        val fakePokemon: PokemonUi = PokemonUi(
            1,
            "Bulbasaur",
            1,
            listOf(
                PokemonTypeUi("Grass", getTypeColor("Grass")),
                PokemonTypeUi("Poison", getTypeColor("Poison"))
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )

        fun getTypeColor(type: String) =
            when (type.toLowerCase(Locale.getDefault())) {
                "grass", "bug" -> pokeLightTeal
                "fire" -> pokeLightRed
                "water", "fighting", "normal" -> pokeLightBlue
                "electric", "psychic" -> pokeLightYellow
                "poison", "ghost" -> pokeLightPurple
                "ground", "rock" -> pokeLightBrown
                "dark" -> pokeBlack
                else -> pokeLightBlue
            }.toArgb()
    }
}