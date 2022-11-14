package dev.jamiecraane.recompositions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun UsersScreen(modifier: Modifier = Modifier) {
    var usersScreenState by remember {
        mutableStateOf(
            UsersScreenState(
                "Persons",
                "Description",
                (1..40).toList().map { Person(it, "Voornaam Achternaam $it") },
            )
        )
    }

    Column() {
        Title(usersScreenState.title)
        Description(usersScreenState.description)
        Button(onClick = { usersScreenState = usersScreenState.copy(title = Random.nextInt().toString()) }) {
            Text("Change title")
        }

        PersonList(usersScreenState.persons)
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
private fun PersonList(persons: List<Person>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(persons, key = { it.id }) { person ->
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

data class UsersScreenState(
    val title: String,
    val description: String,
    val persons: List<Person>,
)

data class Person(val id: Int, val name: String)
