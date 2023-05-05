package dev.jamiecraane.basicconcepts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BasicConcepts() {
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Basic Concepts",
            style = MaterialTheme.typography.h4,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "This label is not recomposed when the count changes.",
            style = MaterialTheme.typography.h6,
        )

        Spacer(Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Counter: $count",
                style = MaterialTheme.typography.h6,
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = { count++ }) {
                Text(
                    text = "Increment",
                    style = MaterialTheme.typography.h6,
                )
            }
        }
    }
}

@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) }

    Row {
        Text(
            text = "Counter: $count",
        )
        Button(onClick = { count++ }) {
            Text(text = "Increment")
        }
    }
}