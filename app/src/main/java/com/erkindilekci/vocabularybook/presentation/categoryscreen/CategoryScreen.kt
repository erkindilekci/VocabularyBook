package com.erkindilekci.vocabularybook.presentation.categoryscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.erkindilekci.vocabularybook.R
import com.erkindilekci.vocabularybook.presentation.ui.theme.*
import com.erkindilekci.vocabularybook.presentation.viewmodels.CategoryScreenViewModel
import com.erkindilekci.vocabularybook.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryScreen(
    viewModel: CategoryScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val categoryScreenState = viewModel.categoryState.collectAsState().value

    val categoryList = categoryScreenState.categoryList

    fun onCategoryClicked(category: String) {
        navController.navigate("detailscreen/$category")
    }

    Scaffold(
        topBar = {
            CategoryScreenAppBar()
        },
        content = {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(MyBackgroundColor),
                columns = GridCells.Fixed(2)
            ) {
                items(categoryList.toList()) {
                    CategoryCard(category = it, onCategoryClicked = { onCategoryClicked(it) })
                }
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.testTag(Constants.ADD_TAG),
                contentColor = Color.White, containerColor = MyStatusBarColor,
                text = { Text(text = stringResource(id = R.string.add), color = Color.White) },
                onClick = {
                    navController.navigate("addscreen")
                },
                icon = { Icon(Icons.Filled.Add, null) },
            )
        }
    )
}
