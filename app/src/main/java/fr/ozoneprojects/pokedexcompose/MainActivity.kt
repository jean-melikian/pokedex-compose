package fr.ozoneprojects.pokedexcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import fr.ozoneprojects.pokedexcompose.ui.PokedexComposeTheme
import fr.ozoneprojects.pokedexcompose.ui.pokedex.app.compose.PokedexScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    PokedexComposeTheme {
        PokedexScreen()
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    MyApp()
}