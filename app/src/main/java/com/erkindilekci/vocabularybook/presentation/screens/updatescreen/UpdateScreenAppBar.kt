package com.erkindilekci.vocabularybook.presentation.screens.updatescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erkindilekci.vocabularybook.R
import com.erkindilekci.vocabularybook.presentation.util.ui.theme.MyTopAppBarColor
import com.erkindilekci.vocabularybook.util.Constants

@Composable
fun UpdateScreenAppBar(title: String, onCloseClicked: () -> Unit, onDeleteClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(MyTopAppBarColor)
            .padding(end = 12.dp, start = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            color = Color.White
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                modifier = Modifier.testTag(Constants.DELETE_TAG),
                onClick = { onDeleteClick() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    tint = Color.White,
                    contentDescription = stringResource(id = R.string.delete)
                )
            }

            IconButton(onClick = { onCloseClicked() }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    tint = Color.White,
                    contentDescription = stringResource(id = R.string.close)
                )
            }
        }
    }
}
