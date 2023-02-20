package com.juraj.common.navigation

sealed class NavigationItem(val route: String) {
    object LoginNavigationItem : NavigationItem("Login")
    object HomeNavigationItem : NavigationItem("Home")
}