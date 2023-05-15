package com.erkindilekci.vocabularybook.presentation.categoryscreen

import android.annotation.SuppressLint
import android.app.Activity
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.erkindilekci.vocabularybook.R
import com.erkindilekci.vocabularybook.presentation.ui.theme.*
//import com.erkindilekci.vocabularybook.presentation.ui.theme.MyBackgroundColorPurple
//import com.erkindilekci.vocabularybook.presentation.ui.theme.MyButtonTextColor
//import com.erkindilekci.vocabularybook.presentation.ui.theme.MyButtonTextColorPurple
//import com.erkindilekci.vocabularybook.presentation.ui.theme.MyCardColor
//import com.erkindilekci.vocabularybook.presentation.ui.theme.MyCardColorPurple
//import com.erkindilekci.vocabularybook.presentation.ui.theme.MyStatusBarColor
//import com.erkindilekci.vocabularybook.presentation.ui.theme.MyStatusBarColorOrange
//import com.erkindilekci.vocabularybook.presentation.ui.theme.MyStatusBarColorPurple
//import com.erkindilekci.vocabularybook.presentation.ui.theme.MyTopAppBarColor
//import com.erkindilekci.vocabularybook.presentation.ui.theme.MyTopAppBarColorPurple
import com.erkindilekci.vocabularybook.presentation.viewmodels.CategoryScreenViewModel
import com.erkindilekci.vocabularybook.util.ColorFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryScreen(
    viewModel: CategoryScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val categoryScreenState = viewModel.categoryState.collectAsState().value

    val categoryList = categoryScreenState.categoryList

    /*val colorFilter = categoryScreenState.actualColorFilter
    val view = LocalView.current
    LaunchedEffect(key1 = MyStatusBarColor) {
        val window = (view.context as Activity).window
        window.statusBarColor = MyStatusBarColor.toArgb()
        WindowCompat.getInsetsController(window, view)
    }*/
/*
    when(colorFilter) {
        ColorFilter.Purple -> {
            MyStatusBarColor = MyStatusBarColorPurple
            MyTopAppBarColor = MyTopAppBarColorPurple
            MyBackgroundColor = MyBackgroundColorPurple
            MyButtonTextColor = MyButtonTextColorPurple
            MyCardColor = MyCardColorPurple
        }
        ColorFilter.Orange -> {
            MyStatusBarColor = MyStatusBarColorOrange
            MyTopAppBarColor = MyTopAppBarColorOrange
            MyBackgroundColor = MyBackgroundColorOrange
            MyButtonTextColor = MyButtonTextColorOrange
            MyCardColor = MyCardColorOrange
        }
        ColorFilter.Maroon -> {
            MyStatusBarColor = MyStatusBarColorMaroon
            MyTopAppBarColor = MyTopAppBarColorMaroon
            MyBackgroundColor = MyBackgroundColorMaroon
            MyButtonTextColor = MyButtonTextColorMaroon
            MyCardColor = MyCardColorMaroon
        }
        ColorFilter.Blue -> {
            MyStatusBarColor = MyStatusBarColorBlue
            MyTopAppBarColor = MyTopAppBarColorBlue
            MyBackgroundColor = MyBackgroundColorBlue
            MyButtonTextColor = MyButtonTextColorBlue
            MyCardColor = MyCardColorBlue
        }
    }*/


    fun onCategoryClicked(category: String) {
        navController.navigate("detailscreen/$category")
    }

    Scaffold(
        topBar = {
            //if (MyTopAppBarColor != Color.White) {
                CategoryScreenAppBar()
            //}
        },
        content = {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(MyBackgroundColor),
                //columns = GridCells.Adaptive(150.dp)
                columns = GridCells.Fixed(2)
            ) {
                items(categoryList.toList()) {
                    CategoryCard(category = it, onCategoryClicked = { onCategoryClicked(it) })
                }
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
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