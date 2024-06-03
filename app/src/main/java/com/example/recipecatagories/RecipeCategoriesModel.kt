package com.example.recipecatagories

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    var idCategory: String = "",
    var strCategory: String = "",
    var strCategoryThumb: String = "",
    var strCategoryDescription: String = ""
): Parcelable

data class ApiResponse(
    var categories: List<Category> = emptyList()
)

data class DetailsScreenStatus(
    var isLoading: Boolean = false,
    var categoryList: List<Category> = emptyList(),
    var error: String? = null
)

sealed class ScreenRoutes(val route: String) {
    data object DetailsScreen: ScreenRoutes("detailsScreen")
    data object CategoryScreen: ScreenRoutes("categoryScreen")
}