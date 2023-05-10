package dev.jamiecraane.recompositions.solution

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
import dev.jamiecraane.recompositions.problem.Movie
import dev.jamiecraane.recompositions.problem.generateMovies

@Composable
fun RecompositionsSolutionsScreen(modifier: Modifier = Modifier) {
    var usersScreenState by remember {
        mutableStateOf(
            MoviesScreenStateSolution(
                "My Favorite Movies",
                "No movie selected",
                MovieCollection(generateMovies()),
            )
        )
    }

    Column(modifier = modifier) {
        Title(usersScreenState.title)
        Description(usersScreenState.selectedMovieTitle)
        MovieList(
            usersScreenState.movieCollection
        ) { movie ->
            usersScreenState = usersScreenState.copy(selectedMovieTitle = movie.title)
        }
    }
}

@Composable
private fun Title(value: String) {
    Text(value)
}

@Composable
private fun Description(value: String) {
    Text(value)
}

@Composable
private fun MovieList(movieCollection: MovieCollection, onMovieSelected: (Movie) -> Unit) {
    println(onMovieSelected.hashCode())
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(movieCollection.movies, key = { it.id }) { movie ->
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

data class MoviesScreenStateSolution(
    val title: String,
    val selectedMovieTitle: String,
    val movieCollection: MovieCollection,
)

@Immutable
data class MovieCollection(val movies: List<Movie>)
