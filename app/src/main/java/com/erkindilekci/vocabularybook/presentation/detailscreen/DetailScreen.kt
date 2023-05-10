package com.erkindilekci.vocabularybook.presentation.detailscreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.erkindilekci.vocabularybook.R
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyBackgroundColor
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyButtonTextColor
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyCardColor
import com.erkindilekci.vocabularybook.presentation.viewmodels.DetailScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    viewModel: DetailScreenViewModel = hiltViewModel(),
    navController: NavController,
    category: String
) {
    val activity = (LocalContext.current) as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    DisposableEffect(key1 = true) {
        onDispose {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    val detailScreenState = viewModel.detailState.collectAsState().value

    var currentIndex by remember { mutableStateOf(0) }
    var vocabularyList = detailScreenState.vocabularyList

    LaunchedEffect(key1 = true) {
        viewModel.getVocabulariesByCategory(category)
    }

    Scaffold(
        topBar = {
            DetailScreenAppBar(
                category = category,
                onCloseClicked = {
                    navController.navigate("categoryscreen") {
                        popUpTo("categoryscreen") { inclusive = true }
                    }
                })
        },
        content = {
            if (vocabularyList.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp)
                        .background(MyBackgroundColor),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DetailScreenItem(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize(),
                        vocabularyCard = vocabularyList[currentIndex],
                        onDeleteClicked = {
                            if (currentIndex == 0) {
                                viewModel.deleteVocabulary(vocabularyList[currentIndex])
                                navController.navigate("categoryscreen") {
                                    popUpTo("categoryscreen") { inclusive = true }
                                }
                            }
                        })

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        //.weight(1f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                if (currentIndex > 0) {
                                    currentIndex -= 1
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = MyCardColor,
                                containerColor = MyBackgroundColor
                            ),
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier
                                .weight(0.75f)
                                .padding(bottom = 15.dp)
                                .height(45.dp),
                            border = BorderStroke(2.dp, MyCardColor),
                            enabled = currentIndex > 0
                        )
                        {
                            Text(
                                text = stringResource(id = R.string.back),
                                fontSize = 20.sp,
                                modifier = Modifier.align(
                                    Alignment.CenterVertically
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            onClick = {
                                if (currentIndex < vocabularyList.size - 1) {
                                    currentIndex += 1
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = MyButtonTextColor,
                                containerColor = MyCardColor
                            ),
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier
                                .weight(0.75f)
                                .padding(bottom = 15.dp)
                                .height(45.dp),
                            enabled = currentIndex < vocabularyList.size - 1
                        )
                        {
                            Text(
                                text = stringResource(id = R.string.next),
                                color = Color.White,
                                fontSize = 20.sp,
                                modifier = Modifier.align(
                                    Alignment.CenterVertically
                                )
                            )
                        }
                    }
                }
            }
        }
    )
}