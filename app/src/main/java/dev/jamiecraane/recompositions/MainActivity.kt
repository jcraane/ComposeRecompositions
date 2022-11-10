package dev.jamiecraane.recompositions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.jamiecraane.recompositions.ui.theme.RecompositionsTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecompositionsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//                    CounterScreen()
                    ListScreen()
                }
            }
        }
    }
}

@Composable
fun ListScreen() {
    var personScreenState by remember {
        mutableStateOf(
            PersonScreenState(
                "Persons",
                "Descrioption",
//                PersonCollection((1..40).toList().map { Person(it, "Voornaam Achternaam $it") }),
                (1..40).toList().map { Person(it, "Voornaam Achternaam $it") },
            )
        )
    }

    Column() {
        Text(personScreenState.title)
        Text(personScreenState.description)
        Button(onClick = { personScreenState = personScreenState.copy(title = Random.nextInt().toString()) }) {
            Text("Update title")
        }

        PersonList(personScreenState.persons)
    }
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

@Stable
data class PersonScreenState(
    val title: String,
    val description: String,
//    val persons: PersonCollection,
    val persons: List<Person>,
)

@Stable
data class PersonCollection(val persons: List<Person>)

data class Person(val id: Int, val name: String)

@Composable
fun CounterScreen() {
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
        modifier = Modifier.fillMaxSize(),
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
