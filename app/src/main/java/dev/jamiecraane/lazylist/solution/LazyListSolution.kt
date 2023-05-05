package dev.jamiecraane.lazylist.problem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LazyListSolution() {
    TodoList()
}

@Composable
private fun TodoList() {
    var screenState by remember {
        mutableStateOf(ScreenState())
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "Todo today",
                style = MaterialTheme.typography.h4,
            )
        }

        items(screenState.items, key = TodoItem::id) { todo ->
            Column {
                Divider()

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = todo.name, modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        screenState = screenState.copy(
                            items = screenState.items.filter { it.id != todo.id }
                        )
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}
