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

@Composable
fun WithDerivedStateOf(modifier: Modifier = Modifier) {
    val screenState by remember {
        mutableStateOf(MovieScreenState())
    }

    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
//    todo what is the difference between this and remember with key?
    val scrollButtonEnabled by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 1
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.padding(top = 52.dp),
            state = lazyListState
        ) {
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
