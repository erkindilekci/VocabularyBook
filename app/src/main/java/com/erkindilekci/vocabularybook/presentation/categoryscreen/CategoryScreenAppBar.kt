package com.erkindilekci.vocabularybook.presentation.categoryscreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erkindilekci.vocabularybook.R
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyBackgroundColor
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyTopAppBarColor
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyTopAppBarColorPurple
import com.erkindilekci.vocabularybook.presentation.viewmodels.CategoryScreenViewModel
import com.erkindilekci.vocabularybook.util.ColorFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreenAppBar(
) {
    /*TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.categories),
                style = typography.titleMedium,
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 16.dp)
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MyTopAppBarColor,
            actionIconContentColor = Color.White,
            titleContentColor = Color.White
        ),
        actions = {
            var isExpanded by remember { mutableStateOf(false) }

            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "Filter",
                    tint = Color.White
                )

                DropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false },
                    Modifier.background(MyBackgroundColor)
                ) {
                    ColorFilter.values().forEach {
                        DropdownMenuItem(
                            text = { Text(text = it.name, color = it.color, fontSize = 15.sp) },
                            onClick = {
                                isExpanded = false
                                //onFilterClick(it)
                                categoryScreenViewModel.persistColorFilter(it)
                            },
                            leadingIcon = {
                                Canvas(modifier = Modifier.size(16.dp)) {
                                    drawCircle(color = it.color)
                                }
                            }
                        )
                    }
                }
            }
        }
    )*/

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(MyTopAppBarColor)
            .padding(end = 12.dp, start = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.categories),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            fontSize = 23.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun dsa() {
    CategoryScreenAppBar()
}