package com.pangidoannsh.jetstore.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Wishlist : Screen("wishlist")
    object DetailProduct : Screen("home/{productId}") {
        fun createRoute(productId: Long) = "home/$productId"
    }

    object About : Screen("about")
}