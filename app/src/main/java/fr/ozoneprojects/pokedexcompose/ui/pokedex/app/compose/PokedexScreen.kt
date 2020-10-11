package fr.ozoneprojects.pokedexcompose.ui.pokedex.app.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import fr.ozoneprojects.pokedexcompose.R
import fr.ozoneprojects.pokedexcompose.ui.PokedexComposeTheme
import fr.ozoneprojects.pokedexcompose.ui.pokedex.app.PokedexViewModel
import fr.ozoneprojects.pokedexcompose.ui.pokedex.app.PokedexViewModelFactory
import fr.ozoneprojects.pokedexcompose.ui.pokedex.infra.PokedexRepositoryImpl


@Composable
fun PokedexScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier,
        topBar = { PokedexAppBar() }) { innerPadding ->
        PokedexList(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun PokedexList(modifier: Modifier = Modifier) {
    val pokedexRepository by remember { mutableStateOf(PokedexRepositoryImpl()) }
    val pokedexVm = viewModel<PokedexViewModel>(
        factory = PokedexViewModelFactory(pokedexRepository)
    )
    val pokedexEntries by pokedexVm.pokedexEntries().observeAsState()
    pokedexVm.getAll()
    pokedexEntries?.let { pokemonsList ->
        LazyColumnForIndexed(
            modifier = modifier.fillMaxWidth(),
            items = pokemonsList
        ) { index, pokemon ->
            val itemModifier: Modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)

            PokedexEntryRoundedCard(
                modifier = itemModifier.then(
                    when (index) {
                        pokemonsList.lastIndex -> Modifier.padding(top = 8.dp, bottom = 8.dp)
                        else -> Modifier.padding(top = 8.dp)
                    }
                ),
                pokemon = pokemon
            )
        }
    }
}

@Composable
private fun PokedexAppBar() {
    TopAppBar(
        title = { Text("Pokédex") },
        navigationIcon = {
            Image(
                modifier = Modifier.padding(4.dp),
                asset = imageFromResource(
                    ContextAmbient.current.resources,
                    R.drawable.ic_launcher_foreground
                )
            )
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}

@Composable
@Preview
private fun DefaultPreview() {
    PokedexComposeTheme {
        PokedexScreen()
    }
}