package com.example.freshcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.freshcomposeproject.ui.navigation.Screen
import com.example.freshcomposeproject.ui.screens.CreatePostView
import com.example.freshcomposeproject.ui.screens.LoginView
import com.example.freshcomposeproject.ui.screens.HomeView
import com.example.freshcomposeproject.ui.theme.FreshComposeProjectTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FreshComposeProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AppNavigator()
                }
            }
        }
    }

    @Composable
    fun AppNavigator() {
        val navController = rememberNavController()
        val items = listOf(
            Screen.Home,
            Screen.Post,
            Screen.Chat,
            Screen.Notification
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Scaffold(
            topBar = {
                if (currentRoute in items.map { item -> item.route }) {
                    TopAppBar {
                        items.forEach { screen ->
                            BottomNavigationItem(
                                icon = { Icon(Icons.Filled.Home, "Home") },
                                label = { Text(stringResource(screen.resourceId)) },
                                selected = currentRoute == screen.route,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.startDestinationRoute!!) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        ) {

            NavHost(navController = navController, startDestination = Screen.Login.route) {
                composable(Screen.Login.route) { LoginView(navController = navController) }
                composable(Screen.Home.route) { HomeView(navController = navController) }
                composable(Screen.CreatePost.route){ CreatePostView(navController = navController)}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FreshComposeProjectTheme {
        val navController = rememberNavController()
        LoginView(navController)
    }
}