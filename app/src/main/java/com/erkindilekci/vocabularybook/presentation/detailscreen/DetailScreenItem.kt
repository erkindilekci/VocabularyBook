package com.erkindilekci.vocabularybook.presentation.detailscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erkindilekci.vocabularybook.R
import com.erkindilekci.vocabularybook.domain.model.VocabularyCard
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyCardColor
import com.erkindilekci.vocabularybook.util.Constants
import com.erkindilekci.vocabularybook.util.byteArrayToImageBitmap


@Composable
fun DetailScreenItem(
    modifier: Modifier = Modifier,
    vocabularyCard: VocabularyCard,
    onDeleteClicked: (Int) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    val center = if (vocabularyCard.image == null) Arrangement.Center else Arrangement.SpaceBetween
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 30.dp, bottom = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MyCardColor),
        verticalArrangement = center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.testTag(Constants.DROPDOWN_MENU),
                onClick = { isExpanded = !isExpanded }
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = null,
                    tint = Color.White
                )

                DropdownMenu(
                    modifier = Modifier.background(Color.White),
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }
                ) {
                    DropdownMenuItem(
                        modifier = Modifier.testTag(Constants.DROPDOWN_MENU_ITEM),
                        text = {
                            Text(
                                text = stringResource(id = R.string.delete),
                                color = Color.Black
                            )
                        },
                        onClick = {
                            onDeleteClicked(vocabularyCard.id)
                            isExpanded = false
                        }
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .weight(if (vocabularyCard.image == null) 2.85f else 7f)
                .fillMaxSize()
                .padding(top = 30.dp, start = 25.dp, end = 25.dp, bottom = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            vocabularyCard.image?.let { byteArray ->
                byteArray?.let { byteArrayToImageBitmap(it) }?.let { imageBitmap ->
                    Image(
                        bitmap = imageBitmap,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .widthIn(min = 200.dp)
                            .heightIn(min = 80.dp)
                    )
                }
            }
        }

        Column(modifier = Modifier.weight(5f)) {
            Text(
                text = vocabularyCard.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                color = Color.White,
                fontSize = if (vocabularyCard.image == null) 30.sp else 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = vocabularyCard.desc,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 14.dp),
                color = Color.White,
                fontSize = if (vocabularyCard.image == null) 27.sp else 19.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(3.dp))

            vocabularyCard.sentence?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
                    color = Color.White,
                    fontSize = if (vocabularyCard.image == null) 23.sp else 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
