package com.pangidoannsh.jetstore.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pangidoannsh.jetstore.R
import com.pangidoannsh.jetstore.data.StoreRepository
import com.pangidoannsh.jetstore.ui.components.BottomBar
import com.pangidoannsh.jetstore.ui.components.TopBar
import com.pangidoannsh.jetstore.ui.navigation.Screen
import com.pangidoannsh.jetstore.ui.screens.about.AboutScreen
import com.pangidoannsh.jetstore.ui.screens.cart.CartScreen
import com.pangidoannsh.jetstore.ui.screens.detail.DetailScreen
import com.pangidoannsh.jetstore.ui.screens.home.HomeScreen
import com.pangidoannsh.jetstore.ui.screens.home.HomeViewModel
import com.pangidoannsh.jetstore.ui.screens.wishlist.WishlistScreen
import com.pangidoannsh.jetstore.ui.theme.JetStoreTheme

@Composable
fun App(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(StoreRepository.getInstance())
    )
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val query by viewModel.query.collectAsState(initial = "")

    Scaffold(
        topBar = {
            if (currentRoute != Screen.DetailProduct.route) {
                TopBar(
                    stringResource(
                        when (currentRoute) {
                            Screen.Cart.route -> R.string.menu_cart
                            Screen.Wishlist.route -> R.string.menu_wishlist
                            Screen.About.route -> R.string.menu_about
                            else -> R.string.app_name
                        }
                    ),
                    isHome = currentRoute == Screen.Home.route,
                    isAbout = currentRoute == Screen.About.route,
                    navigateToAbout = {
                        navController.navigate(Screen.About.route)
                    },
                    navigateToBack = {
                        navController.popBackStack()
                    },
                    query = query,
                    onQueryChange = {
                        viewModel.setQuery(it)
                    },
                    onSearch = {
                        viewModel.search()
//                        Log.d("SearchCheck", "test")
                    }
                )
            }
        },
        bottomBar = {
            if (currentRoute != Screen.DetailProduct.route && currentRoute != Screen.About.route) {
                BottomBar(navController)
            }
        }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { productId ->
                        navController.navigate(Screen.DetailProduct.createRoute(productId))
                    },
                    viewModel = viewModel
                )
            }
            composable(Screen.Cart.route) {
                val context = LocalContext.current
                CartScreen(onOrderButtonClicked = { message ->
                    shareOrder(context, message)
                })
            }
            composable(Screen.Wishlist.route) {
                WishlistScreen(navigateToDetail = { productId ->
                    navController.navigate(Screen.DetailProduct.createRoute(productId))
                })
            }
            composable(
                route = Screen.DetailProduct.route,
                arguments = listOf(navArgument("productId") { type = NavType.LongType })
            ) {
                val id = it.arguments?.getLong("productId") ?: -1L
                DetailScreen(
                    productId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToCart = {
                        navController.popBackStack()
                        navController.navigate(Screen.Cart.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable(route = Screen.About.route) {
                AboutScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetStoreTheme {
        App()
    }
}

private fun shareOrder(context: Context, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
        putExtra(Intent.EXTRA_TEXT, summary)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.app_name)
        )
    )
}