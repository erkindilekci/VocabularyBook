package com.erkindilekci.vocabularybook.presentation.screens.updatescreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.erkindilekci.vocabularybook.R
import com.erkindilekci.vocabularybook.presentation.util.ui.theme.MyBackgroundColor
import com.erkindilekci.vocabularybook.presentation.util.ui.theme.MyButtonTextColor
import com.erkindilekci.vocabularybook.presentation.util.ui.theme.MyCardColor
import com.erkindilekci.vocabularybook.presentation.util.ui.theme.MyTopAppBarColor
import com.erkindilekci.vocabularybook.presentation.viewmodels.UpdateScreenViewModel
import com.erkindilekci.vocabularybook.util.Constants
import com.erkindilekci.vocabularybook.util.byteArrayToUri
import com.erkindilekci.vocabularybook.util.uriToByteArray

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SourceLockedOrientationActivity")
@Composable
fun UpdateScreen(
    viewModel: UpdateScreenViewModel = hiltViewModel(),
    navController: NavController,
    id: Int
) {
    LaunchedEffect(key1 = true) {
        viewModel.getVocabularyById(id)
    }

    val activity = (LocalContext.current) as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    DisposableEffect(key1 = true) {
        onDispose {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }
    val contentResolver = LocalContext.current.contentResolver

    val focusManager = LocalFocusManager.current

    val title = viewModel.title
    val desc = viewModel.desc
    val meaning = viewModel.meaning
    val sentence = viewModel.sentence
    val category = viewModel.category

    var selectedImageUri by remember(key1 = viewModel.image) {
        mutableStateOf(viewModel.image?.let { byteArrayToUri(it, contentResolver) }
            ?: Uri.parse("android.resource://com.erkindilekci.vocabularybook/drawable/outline_add_photo_alternate_24"))
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            selectedImageUri?.let {
                viewModel.updateImage(uriToByteArray(it, 30, contentResolver))
            }
        }
    )

    Scaffold(
        topBar = {
            UpdateScreenAppBar(
                title = title,
                onCloseClicked = { navController.popBackStack() },
                onDeleteClick = {
                    viewModel.deleteVocabulary()
                    navController.navigate("categoryscreen") {
                        popUpTo("categoryscreen") { inclusive = true }
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp)
                    .background(MyBackgroundColor),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(8f)
                        .padding(start = 20.dp, end = 20.dp, top = 30.dp, bottom = 15.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(MyCardColor)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(30.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = selectedImageUri,
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    photoPickerLauncher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                }
                        )
                    }


                    OutlinedTextField(
                        value = title,
                        onValueChange = { viewModel.updateTitle(it) },
                        modifier = Modifier
                            .testTag(Constants.TITLE_TEXT_FIELD)
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.title),
                                color = MyCardColor,
                                textAlign = TextAlign.Center
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            textColor = MyTopAppBarColor,
                            cursorColor = MyTopAppBarColor,
                            placeholderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent
                        ),
                        singleLine = true,
                        shape = RoundedCornerShape(15.dp),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = desc,
                        onValueChange = { viewModel.updateDescription(it) },
                        modifier = Modifier
                            .testTag(Constants.DESCRIPTION_TEXT_FIELD)
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.description),
                                color = MyCardColor
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            textColor = MyTopAppBarColor,
                            cursorColor = MyTopAppBarColor,
                            placeholderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent
                        ),
                        singleLine = true,
                        shape = RoundedCornerShape(15.dp),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = meaning ?: "",
                        onValueChange = { viewModel.updateMeaning(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.description_in_other_language_optional),
                                color = MyCardColor
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            textColor = MyTopAppBarColor,
                            cursorColor = MyTopAppBarColor,
                            placeholderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent
                        ),
                        singleLine = true,
                        shape = RoundedCornerShape(15.dp),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = sentence ?: "",
                        onValueChange = { viewModel.updateSentence(it) },
                        modifier = Modifier
                            .testTag(Constants.SENTENCE_TEXT_FIELD)
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .height(55.dp),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.sentence),
                                color = MyCardColor
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            textColor = MyTopAppBarColor,
                            cursorColor = MyTopAppBarColor,
                            placeholderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent
                        ),
                        singleLine = true,
                        shape = RoundedCornerShape(15.dp),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        )
                    )

                    OutlinedTextField(
                        value = category,
                        onValueChange = { viewModel.updateCategory(it) },
                        modifier = Modifier
                            .testTag(Constants.CATEGORY_TEXT_FIELD)
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, top = 10.dp, bottom = 15.dp),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.category),
                                color = MyCardColor
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            textColor = MyTopAppBarColor,
                            cursorColor = MyTopAppBarColor,
                            placeholderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent
                        ),
                        singleLine = true,
                        shape = RoundedCornerShape(15.dp),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        )
                    )
                }

                val titleCant = stringResource(id = R.string.title_cant)
                val descCant = stringResource(id = R.string.desc_cant)
                val categoryCant = stringResource(id = R.string.category_Cant)

                Button(
                    onClick = {
                        if (title.trim().isEmpty()) {
                            Toast.makeText(activity, titleCant, Toast.LENGTH_LONG).show()
                        } else if (desc.trim().isEmpty()) {
                            Toast.makeText(activity, descCant, Toast.LENGTH_LONG).show()
                        } else if (category.trim().isEmpty()) {
                            Toast.makeText(activity, categoryCant, Toast.LENGTH_LONG).show()
                        } else {
                            viewModel.updateVocabulary()

                            navController.navigate("categoryscreen") {
                                popUpTo("categoryscreen") { inclusive = true }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MyButtonTextColor,
                        containerColor = MyCardColor
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .testTag(Constants.UPDATE_TAG)
                        .weight(0.75f)
                        .padding(bottom = 15.dp)
                        .height(45.dp)
                )
                {
                    Text(
                        text = stringResource(id = R.string.update),
                        color = Color.White,
                        fontSize = 20.sp,
                        modifier = Modifier.align(
                            Alignment.CenterVertically
                        )
                    )
                }
            }
        }
    )
}
