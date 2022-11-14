package dev.jamiecraane.recompositions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.jamiecraane.recompositions.ui.theme.RecompositionsTheme

@Composable
fun CounterScreen(modifier: Modifier = Modifier) {
    var firstCount by remember {
        mutableStateOf(0)
    }

    var secondCount by remember {
        mutableStateOf(0)
    }

//    val totalIsOver10 = (firstCount + secondCount) > 10
    val totalIsOver10 by remember {
        derivedStateOf {
            val total = firstCount + secondCount
            total > 10
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(if (totalIsOver10) "Total is over 10" else "Total is less than 10")
        Button(
            onClick = { firstCount += 1 }
        ) {
            Text(text = "Increment(Count 1)")
        }
        Button(
            onClick = { secondCount += 1 }
        ) {
            Text(text = "Increment(Count 2)")
        }
    }
}

@Preview
@Composable
private fun CounterScreenPreview() {
    RecompositionsTheme {
        CounterScreen()
    }
}
