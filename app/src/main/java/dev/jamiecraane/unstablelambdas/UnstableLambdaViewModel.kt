package dev.jamiecraane.unstablelambdas

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class UnstableLambdaViewModel : ViewModel() {
    private val messages = listOf(
        "You got this!",
        "Keep going!",
        "Believe in yourself!",
        "You're amazing!",
        "Don't give up!"
    )

    val greetings = MutableStateFlow(emptyList<String>().toImmutableList())

    fun addGreeting() {
        val randomGreeting = messages.random()
        greetings.update {
            (it + randomGreeting).toImmutableList()
        }
    }

    fun onGreetingClicked() {
        println("Hi from a greeting click")
    }

    fun clear() {
        greetings.value = emptyList<String>().toImmutableList()
    }
}
