package dev.jamiecraane.unstablelambdas

import androidx.lifecycle.ViewModel
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

    val greetings = MutableStateFlow(emptyList<String>())

    fun addGreeting() {
        val randomGreeting = messages.random()
        greetings.update {
            it + randomGreeting
        }
    }

    fun onGreetingClicked() {
        println("Hi from a greeting click")
    }

    fun clear() {
        greetings.value = emptyList()
    }
}
