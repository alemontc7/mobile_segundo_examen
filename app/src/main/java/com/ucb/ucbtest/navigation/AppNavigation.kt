package com.ucb.ucbtest.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ucb.ucbtest.books.BooksMainScreen
import com.ucb.ucbtest.books.BooksUI
import com.ucb.ucbtest.counter.CounterUI
import com.ucb.ucbtest.takephoto.TakePhotoUI
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MainBookScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }

    ) {
        composable(Screen.CounterScreen.route) {
            CounterUI()
        }

        composable(Screen.SearchBooksScreen.route) {
            BooksUI()
        }

        composable(Screen.MainBookScreen.route) {
            BooksMainScreen()
        }

    }


}