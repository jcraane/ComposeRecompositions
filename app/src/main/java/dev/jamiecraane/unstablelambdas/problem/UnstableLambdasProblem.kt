package dev.jamiecraane.unstablelambdas.problem

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.jamiecraane.unstablelambdas.UnstableLambdaViewModel

@Composable
fun UnstableLambdasMethodRefProblem() {
    println("Recompose UnstableLambdasMethodRefProblem")
    val viewModel: UnstableLambdaViewModel = viewModel()

    LaunchedEffect(key1 = null) {
        viewModel.clear()
    }

    val greetings by viewModel.greetings.collectAsStateWithLifecycle()
    RandomGreeting(greetings, viewModel::addGreeting) { viewModel.onGreetingClicked() }
}

@Composable
fun RandomGreeting(
    greetings: List<String>,
    onAddGreetingClicked: () -> Unit,
    onGreetingClicked: () -> Unit,
) {
    println("Recompose RandomGreeting")
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Button(
                onClick = onAddGreetingClicked,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Greet")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                greetings.forEach { greeting ->
                    Greeting(greeting, onGreetingClicked)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun Greeting(greeting: String, onClick: () -> Unit) {
    println("Recompose Greeting: $greeting")
    Text(
        modifier = Modifier.clickable(
            onClick = onClick,
        ),
        text = greeting,
        style = MaterialTheme.typography.h5
    )
}
