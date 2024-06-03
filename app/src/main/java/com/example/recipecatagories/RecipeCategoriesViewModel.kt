package com.example.recipecatagories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RecipeCategoriesViewModel: ViewModel() {
    private val _detailsScreenStatus = mutableStateOf(DetailsScreenStatus())
    val detailsScreenStatus: State<DetailsScreenStatus> = _detailsScreenStatus

    private fun fetchResponse(){
        viewModelScope.launch {
            try {
                _detailsScreenStatus.value=_detailsScreenStatus.value.copy(
                    isLoading = true
                )
                val response = receiverService.getCategories()
                _detailsScreenStatus.value=_detailsScreenStatus.value.copy(
                    categoryList = response.categories,
                    isLoading = false,
                    error = null
                )
            }
            catch(e: Exception){
                _detailsScreenStatus.value=_detailsScreenStatus.value.copy(
                    categoryList = emptyList(),
                    isLoading = false,
                    error = "Error occurred : ${e.message}"
                )
            }
        }
    }

    init {
        fetchResponse()
    }

    fun onReload(){
        fetchResponse()
    }


}