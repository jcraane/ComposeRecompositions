package dev.jamiecraane.recompositions.solution

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jamiecraane.recompositions.problem.Person
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlin.random.Random

@Composable
fun RecompositionsSolutionsScreen2(modifier: Modifier = Modifier) {
    var usersScreenState by remember {
        mutableStateOf(
            UsersScreenStateSolution2(
                "Users",
                "This is the users screen",
                (1..40).toList().map { Person(it, "Firstname Lastname $it") }.toImmutableList(),
            )
        )
    }

    Column(modifier = modifier) {
        Title(usersScreenState.title)
        Description(usersScreenState.description)

        Button(onClick = { usersScreenState = usersScreenState.copy(title = Random.nextInt().toString()) }) {
            Text("Change title")
        }

        PersonList(usersScreenState.personCollection)
    }
}

@Composable
private fun Title(value: String) {
    Text(value)
}

@Composable
private fun Description(value: String) {
    Text(value)
}

@Composable
private fun PersonList(persons: ImmutableList<Person>) {
    println("debug:: Compose PersonList")
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(persons, key = { it.id }) { person ->
            println("debug:: Compose Items")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(person.id.toString())
                Spacer(Modifier.height(8.dp))
                Text(text = person.name)
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

private data class UsersScreenStateSolution2(
    val title: String,
    val description: String,
    val personCollection: ImmutableList<Person>,
)
