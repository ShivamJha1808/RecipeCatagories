package com.example.recipecatagories

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun DetailsScreenView(viewModel: RecipeCategoriesViewModel,navigateToCategoryScreen: (Category)->Unit) {
    val viewState by viewModel.detailsScreenStatus
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.TopCenter) {
        if(viewState.isLoading) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
                Spacer(Modifier.height(16.dp))
                Text(text = "Loading...")
            }
        }
        else if (viewState.error != null) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(viewState.error ?: "",
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                )
                Spacer(Modifier.height(16.dp))
                Button(onClick = { viewModel.onReload() }) {
                    Text(text = "Reload")

                }
            }
        }
        else {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                LazyVerticalGrid(columns = GridCells.Fixed(2),Modifier.weight(1f)) {
                    items(viewState.categoryList) {
                        GridItem(category = it,navigateToCategoryScreen=navigateToCategoryScreen)
                    }
                }
                Spacer(Modifier.height(16.dp))
                Button(onClick = { viewModel.onReload() }) {
                    Text(text = "Reload")

                }
            }
        }

    }
}

@Composable
fun GridItem(category:Category,navigateToCategoryScreen: (Category)->Unit){
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(8.dp)
        .clickable {navigateToCategoryScreen(category)},
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .aspectRatio(1f))
        Text(text = category.strCategory,
            fontWeight = FontWeight.Bold
        )
    }
}