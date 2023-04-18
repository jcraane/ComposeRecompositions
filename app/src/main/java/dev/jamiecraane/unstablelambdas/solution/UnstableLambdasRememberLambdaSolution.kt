package dev.jamiecraane.unstablelambdas.solution

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.jamiecraane.unstablelambdas.UnstableLambdaViewModel
import dev.jamiecraane.unstablelambdas.problem.RandomGreeting

@Composable
fun UnstableLambdasRememberedLambdaSolution() {
    Test()
}

@Composable
private fun Test() {
    println("Recompose UnstableLambdasMethodRefProblem")
    val viewModel: UnstableLambdaViewModel = viewModel()

    LaunchedEffect(key1 = null) {
        viewModel.clear()
    }

    val greetings by viewModel.greetings.collectAsStateWithLifecycle()
    RandomGreeting(greetings, viewModel::addGreeting, remember {
        { viewModel.onGreetingClicked() }
    })
}