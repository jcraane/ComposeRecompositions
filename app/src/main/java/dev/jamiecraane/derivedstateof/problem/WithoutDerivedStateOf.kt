package dev.jamiecraane.recompositions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jamiecraane.recompositions.problem.Movie
import dev.jamiecraane.recompositions.problem.generateMovies
import dev.jamiecraane.recompositions.solution.Rating
import kotlinx.coroutines.launch


data class MovieScreenState(
    val movies: List<Movie> = generateMovies()
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

    Box(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp).padding(top = 8.dp)) {
        LazyColumn(
            modifier = Modifier.padding(top = 52.dp),
            state = lazyListState
        ) {
            items(screenState.movies) { movie ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
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
