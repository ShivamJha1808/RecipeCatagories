package com.example.recipecatagories

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeCategoriesApp(navController: NavHostController) {
    val viewModel: RecipeCategoriesViewModel = viewModel()
    NavHost(navController = navController, startDestination = ScreenRoutes.DetailsScreen.route){
        composable(ScreenRoutes.DetailsScreen.route){
            DetailsScreenView(viewModel = viewModel, navigateToCategoryScreen = {
                navController.currentBackStackEntry?.savedStateHandle?.set("Category",it)
                navController.navigate(ScreenRoutes.CategoryScreen.route)
            })
        }
        composable(ScreenRoutes.CategoryScreen.route){
            val categoryPassed = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("Category")?: Category()
            CategoryScreenView(category = categoryPassed)
        }
    }
}