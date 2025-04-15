package com.ucb.ucbtest.navigation

sealed class Screen(val route: String) {
    //object MovieDetailScreen: Screen("movieDetail")
    //object GitaliasScreen : Screen("gitlab")
    //object TakePhotoScreen: Screen("takephoto")
    //object MenuScreen: Screen("menu")
    //object LoginScreen: Screen("login")
    //object MoviesScreen: Screen("movies")
    object CounterScreen: Screen("counter")
    object BooksScreen: Screen("books")

}