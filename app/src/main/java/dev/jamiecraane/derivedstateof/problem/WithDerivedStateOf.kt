package dev.jamiecraane.recompositions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jamiecraane.recompositions.solution.Rating
import kotlinx.coroutines.launch

@Composable
fun WithDerivedStateOf(modifier: Modifier = Modifier) {
    val screenState by remember {
        mutableStateOf(MovieScreenState())
    }

    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val scrollButtonEnabled by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0
        }
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
            }
        ) {
            Text("Scroll to top")
        }
    }
}
