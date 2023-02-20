package com.juraj.codingtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.juraj.codingtask.ui.theme.CodingTaskTheme
import com.juraj.common.navigation.NavigationItem
import com.juraj.home.ui.HomeScreen
import com.juraj.login.ui.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodingTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val scaffoldState = rememberScaffoldState()
                    val navController = rememberNavController()
                    Scaffold(modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState,
                    ) {
                        MainActivityNavigationConfigurations(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CodingTaskTheme {
        Greeting("Android")
    }
}

@Composable
private fun MainActivityNavigationConfigurations(
    navController: NavHostController,
) {
    NavHost(navController, startDestination = NavigationItem.LoginNavigationItem.route) {
        composable(NavigationItem.LoginNavigationItem.route) {
            LoginScreen(navController)
        }
        composable(NavigationItem.HomeNavigationItem.route) {
            HomeScreen(navController)
        }
    }
}