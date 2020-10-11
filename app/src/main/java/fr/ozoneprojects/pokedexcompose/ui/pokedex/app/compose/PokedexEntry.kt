package fr.ozoneprojects.pokedexcompose.ui.pokedex.app.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.luca992.compose.image.CoilImage
import fr.ozoneprojects.pokedexcompose.R
import fr.ozoneprojects.pokedexcompose.ui.PokedexComposeTheme
import fr.ozoneprojects.pokedexcompose.ui.pokedex.app.utils.PokemonUtils
import fr.ozoneprojects.pokedexcompose.ui.pokedex.domain.PokemonUi
import java.util.*

@Composable
fun PokedexEntryRoundedCard(
    modifier: Modifier = Modifier,
    pokemon: PokemonUi
) {
    val color by remember { mutableStateOf(Color(pokemon.types.first().color)) }
    Surface(
        modifier = modifier.then(Modifier.clip(RoundedCornerShape(8.dp))),
        color = color
    ) {
        PokedexEntry(modifier, pokemon)
    }
}

@Composable
private fun PokedexEntry(
    modifier: Modifier,
    pokemon: PokemonUi
) {
    val pkmn by remember { mutableStateOf(pokemon) }
    Row(modifier = modifier.padding(8.dp)) {
        PokemonImage(modifier = Modifier.padding(4.dp), pokemon = pkmn)
        val mainTextModifier = Modifier.align(Alignment.CenterVertically)

        Column {
            PokemonIdAndName(mainTextModifier, pkmn)
            PokemonTypes(mainTextModifier, pokemon)
        }
    }
}

@Composable
private fun PokemonIdAndName(
    modifier: Modifier,
    pokemon: PokemonUi
) {
    val style = MaterialTheme.typography.subtitle1
    Row {
        Text(
            modifier = modifier
                .padding(8.dp),
            text = "# %03d".format(pokemon.id), style = style
        )
        Text(
            modifier = modifier.padding(start = 16.dp),
            text = pokemon.name.capitalize(Locale.getDefault()),
            style = style
        )
    }
}

@Composable
private fun PokemonTypes(
    modifier: Modifier,
    pokemon: PokemonUi
) {
    LazyRowFor(modifier = modifier, items = pokemon.types) { type ->
        Text(
            modifier = modifier.padding(4.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = Color.White)
                .padding(4.dp),
            text = type.name.capitalize(Locale.getDefault()),
            color = Color(type.color)
        )

    }
}

@Composable
private fun PokemonImage(
    modifier: Modifier = Modifier,
    pokemon: PokemonUi
) {
    val modif = modifier
        .preferredSize(100.dp)
        .aspectRatio(1f)
    Box(modifier = modifier) {
        val pokeballImage = vectorResource(R.drawable.ic_pokeball)

        Image(
            modifier = modif,
            asset = pokeballImage,
            colorFilter = ColorFilter(MaterialTheme.colors.onSurface, BlendMode.SrcAtop)
        )
        CoilImage(modifier = modif.padding(4.dp), model = pokemon.spriteUri)
    }
}

@Composable
@Preview(name = "Entry Preview")
private fun EntryPreview() {
    PokedexComposeTheme {
        PokedexEntryRoundedCard(
            Modifier,
            PokemonUtils.fakePokemon
        )
    }
}