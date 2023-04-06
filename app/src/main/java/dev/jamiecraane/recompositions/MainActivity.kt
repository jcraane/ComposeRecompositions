package dev.jamiecraane.recompositions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jamiecraane.lazylist.problem.LazyListGotcha
import dev.jamiecraane.lazylist.solution.LazyListSolution
import dev.jamiecraane.recompositions.problem.RecompositionsProblemScreen
import dev.jamiecraane.recompositions.solution.RecompositionsSolutionsScreen
import dev.jamiecraane.ui.theme.RecompositionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecompositionsTheme {
                var currentScreen by remember {
                    mutableStateOf(Screens.SCREEN_LIST)
                }

                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    BackPressHandler(onBackPressed = {
                        currentScreen = Screens.SCREEN_LIST
                    })

                    when (currentScreen) {
                        Screens.SCREEN_LIST -> {
                            ScreenList { screenToShow ->
                                currentScreen = screenToShow
                            }
                        }
                        Screens.RECOMPOSITION_PROBLEM -> RecompositionsProblemScreen()
                        Screens.DERIVED_STATE_OF -> CounterScreen()
                        Screens.RECOMPOSITION_SOLUTION -> RecompositionsSolutionsScreen()
                        Screens.LAZYLIST_KEY_PROBLEM -> LazyListGotcha()
                        Screens.LAZYLIST_KEY_SOLUTION -> LazyListSolution()
                    }
                }
            }
        }
    }
}

@Composable
private fun ScreenList(onDemoClicked: (Screens) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(Screens.navigatableScreens) { screen ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        remember { MutableInteractionSource() },
                        rememberRipple(bounded = true),
                        onClick = {
                            onDemoClicked(screen)
                        },
                    )
                    .padding(16.dp)
            ) {
                Text(screen.label)
            }
        }
    }
}

@Composable
private fun BackPressHandler(
    backPressedDispatcher: OnBackPressedDispatcher? =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)

    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }
}

enum class Screens(val label: String) {
    SCREEN_LIST("Screen list"),
    RECOMPOSITION_PROBLEM("Demo - Recompositions Problem"),
    RECOMPOSITION_SOLUTION("Demo - Recompositions Solution"),
    LAZYLIST_KEY_PROBLEM("Demo - LazyList Gotcha"),
    DERIVED_STATE_OF("Demo - derivedStateOf"),
    LAZYLIST_KEY_SOLUTION("Demo - LazyList Solution");

    companion object {
        val navigatableScreens = values().toList() - SCREEN_LIST
    }
}
