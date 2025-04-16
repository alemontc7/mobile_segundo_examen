package com.ucb.ucbtest.navigation

sealed class Screen(val route: String) {
    object CounterScreen: Screen("counter")
    object SearchBooksScreen: Screen("search")
    object MainBookScreen: Screen("main")

}