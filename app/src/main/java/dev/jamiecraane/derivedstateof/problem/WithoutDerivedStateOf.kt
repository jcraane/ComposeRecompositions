package dev.jamiecraane.recompositions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class Movie(val id: Int, val name: String)

data class MovieScreenState(
    val movies: List<Movie> = listOf(
        Movie(1, "The Shawshank Redemption"),
        Movie(2, "The Godfather"),
        Movie(3, "The Godfather: Part II"),
        Movie(4, "The Dark Knight"),
        Movie(5, "12 Angry Men"),
        Movie(6, "Schindler's List"),
        Movie(7, "The Lord of the Rings: The Return of the King"),
        Movie(8, "Pulp Fiction"),
        Movie(9, "The Lord of the Rings: The Fellowship of the Ring"),
        Movie(10, "Forrest Gump"),
        Movie(11, "The Good, the Bad and the Ugly"),
        Movie(12, "The Lord of the Rings: The Two Towers"),
        Movie(13, "Fight Club"),
        Movie(14, "Inception"),
        Movie(15, "The Matrix"),
        Movie(16, "Goodfellas"),
        Movie(17, "Star Wars: Episode V - The Empire Strikes Back"),
        Movie(18, "One Flew Over the Cuckoo's Nest"),
        Movie(19, "Seven Samurai"),
        Movie(20, "Se7en"),
        Movie(id = 21, "Blade Runner"),
        Movie(id = 22, "The Terminator"),
        Movie(id = 23, "Star Wars"),
        Movie(id = 24, "Alien"),
        Movie(id = 25, "The Matrix"),
        Movie(id = 26, "Back to the Future"),
        Movie(id = 27, "Close Encounters of the Third Kind"),
        Movie(id = 28, "E.T. the Extra-Terrestrial"),
        Movie(id = 29, "Jurassic Park"),
        Movie(id = 30, "Avatar"),
    )
)

@Composable
fun WithoutDerivedStateOf(modifier: Modifier = Modifier) {
    val screenState by remember {
        mutableStateOf(MovieScreenState())
    }

    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val scrollButtonEnabled = remember(lazyListState.firstVisibleItemIndex) {
        lazyListState.firstVisibleItemIndex > 0
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.padding(top = 52.dp),
            state = lazyListState) {
            items(screenState.movies) { movie ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = movie.name, modifier = Modifier.weight(1f))
                }
            }

        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = scrollButtonEnabled,
            onClick = {
                scope.launch {
                    lazyListState.animateScrollToItem(0)
                }
            }) {
            Text("Scroll to top")
        }

    }
}
