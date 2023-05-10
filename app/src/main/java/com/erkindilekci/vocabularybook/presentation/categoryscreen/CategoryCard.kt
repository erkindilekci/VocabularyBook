package com.erkindilekci.vocabularybook.presentation.categoryscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyTopAppBarColor

@Composable
fun CategoryCard(category: String, modifier: Modifier = Modifier, onCategoryClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .width(150.dp)
            .height(80.dp)
            .background(MyTopAppBarColor)
            .clickable { onCategoryClicked() }
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 22.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}