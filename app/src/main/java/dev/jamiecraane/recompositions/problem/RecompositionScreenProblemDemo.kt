package dev.jamiecraane.recompositions.problem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jamiecraane.recompositions.solution.Rating
import kotlin.random.Random

val movieTitles = arrayOf(
    "The Dark Knight",
    "Star Wars: A New Hope",
    "The Godfather",
    "Jurassic Park",
    "Forrest Gump",
    "The Shawshank Redemption",
    "The Lord of the Rings: The Fellowship of the Ring",
    "The Matrix",
    "Pulp Fiction",
    "The Silence of the Lambs",
    "The Lion King",
    "The Terminator",
    "Fight Club",
    "Back to the Future",
    "Titanic",
    "Avatar",
    "The Avengers",
    "The Incredibles",
    "Finding Nemo",
    "Toy Story"
)

fun generateMovies(): List<Movie> {
    val maxRating = 5
    return movieTitles.mapIndexed { index, title ->
        Movie(index.toString(), title, Random.nextInt(maxRating) + 1)
    }
}

@Composable
fun RecompositionsProblemScreen(modifier: Modifier = Modifier) {
    var usersScreenState by remember {
        mutableStateOf(
            MoviesScreenStateProblem(
                title = "My Favorite Movies",
                selectedMovieTitle = "No movie selected",
                movies = generateMovies(),
            )
        )
    }

    Column(modifier = modifier.padding(16.dp)) {
        Title(usersScreenState.title)
        Description(usersScreenState.selectedMovieTitle)
        Spacer(Modifier.height(16.dp))

        MovieList(
            usersScreenState.movies
        ) { movie ->
            usersScreenState = usersScreenState.copy(selectedMovieTitle = movie.title)
        }
    }
}

@Composable
private fun Title(value: String) {
    Text(
        value,
        style = MaterialTheme.typography.h4,
    )
}

@Composable
private fun Description(value: String) {
    Text(
        value,
        style = MaterialTheme.typography.h5,
    )
}

@Composable
private fun MovieList(
    movies: List<Movie>,
    onMovieSelected: (Movie) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(movies, key = { it.id }) { movie ->
            Column(
                modifier = Modifier
                    .clickable(
                        remember { MutableInteractionSource() },
                        rememberRipple(bounded = true),
                        onClick = { onMovieSelected(movie) },
                    )
                    .fillMaxWidth()
            ) {
                Divider()
                Spacer(Modifier.height(4.dp))

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6,
                )
                Rating(movie.rating)
                Spacer(Modifier.height(4.dp))
            }
        }
    }
}

data class MoviesScreenStateProblem(
    val title: String,
    val selectedMovieTitle: String,
    val movies: List<Movie>,
)

data class Movie(
    val id: String,
    val title: String,
    val rating: Int
)
