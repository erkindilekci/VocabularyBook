package com.erkindilekci.vocabularybook.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.erkindilekci.vocabularybook.presentation.addscreen.VocabularyAddScreen
import com.erkindilekci.vocabularybook.presentation.categoryscreen.CategoryScreen
import com.erkindilekci.vocabularybook.presentation.detailscreen.DetailScreen

@Composable
fun ComposeNavigation() {
    val navNavController = rememberNavController()
    NavHost(navController = navNavController, startDestination = "categoryscreen") {
        composable("categoryscreen") {
            CategoryScreen(navController = navNavController)
        }
        composable("addscreen") {
            VocabularyAddScreen(navController = navNavController)
        }
        composable("detailscreen/{category}", arguments = listOf(
            navArgument("category") { type = NavType.StringType }
        )) {
            val category = remember { it.arguments?.getString("category") }
            DetailScreen(navController = navNavController, category = category ?: "")
        }
    }
}