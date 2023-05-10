package com.erkindilekci.vocabularybook.presentation.categoryscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erkindilekci.vocabularybook.R
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyTopAppBarColor

@Composable
fun CategoryScreenAppBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(MyTopAppBarColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.categories),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 23.sp,
            color = Color.White
        )
    }
}