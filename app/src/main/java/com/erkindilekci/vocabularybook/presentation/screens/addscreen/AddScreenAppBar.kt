package com.erkindilekci.vocabularybook.presentation.screens.addscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.erkindilekci.vocabularybook.presentation.util.ui.theme.MyTopAppBarColor

@Composable
fun AddScreenAppBar(
    onBackClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(MyTopAppBarColor),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClicked) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                tint = Color.White,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Text(
            text = stringResource(id = R.string.vas),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 23.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}
