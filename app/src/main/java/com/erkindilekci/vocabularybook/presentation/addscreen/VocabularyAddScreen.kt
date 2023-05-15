package com.erkindilekci.vocabularybook.presentation.addscreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.erkindilekci.vocabularybook.R
import com.erkindilekci.vocabularybook.data.local.room.VocabularyCard
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyBackgroundColor
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyButtonTextColor
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyCardColor
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyTopAppBarColor
import com.erkindilekci.vocabularybook.presentation.viewmodels.AddScreenViewModel
import com.erkindilekci.vocabularybook.util.uriToByteArray
import java.io.ByteArrayOutputStream
import java.io.InputStream


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VocabularyAddScreen(
    viewModel: AddScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val activity = (LocalContext.current) as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    DisposableEffect(key1 = true) {
        onDispose {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    val focusManager = LocalFocusManager.current

    var title by rememberSaveable { mutableStateOf("") }
    var desc by rememberSaveable { mutableStateOf("") }
    var sentence by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf("") }

    var selectedImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri = uri }
    )

    val contentResolver = LocalContext.current.contentResolver

    Scaffold(
        topBar = {
            VocabularyAddScreenAppBar()
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
                            //.height(150.dp)
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(30.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (selectedImageUri == null) {
                            Image(
                                painter = painterResource(id = R.drawable.outline_add_photo_alternate_24),
                                //modifier = Modifier.size(150.dp),
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
                        } else {
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
                    }


                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        //.height(55.dp),
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
                        onValueChange = { desc = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        //.height(55.dp),
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
                        value = sentence,
                        onValueChange = { sentence = it },
                        modifier = Modifier
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
                        onValueChange = { category = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, top = 10.dp, bottom = 15.dp),
                        //.height(55.dp)
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
                        val byteArray =
                            selectedImageUri?.let { uriToByteArray(it, 30, contentResolver) }
                        val newVocabularyCard = VocabularyCard(
                            title = title.trim(),
                            desc = desc.trim(),
                            sentence = sentence.trim(),
                            image = byteArray,
                            category = category.trim()
                        )

                        if (title.trim().isEmpty()) {
                            Toast.makeText(activity, titleCant, Toast.LENGTH_LONG).show()
                        } else if (desc.trim().isEmpty()) {
                            Toast.makeText(activity, descCant, Toast.LENGTH_LONG).show()
                        } else if (category.trim().isEmpty()) {
                            Toast.makeText(activity, categoryCant, Toast.LENGTH_LONG).show()
                        } else {
                            viewModel.addVocabulary(newVocabularyCard)

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
                        .weight(0.75f)
                        .padding(bottom = 15.dp)
                        .height(45.dp)
                )
                {
                    Text(
                        text = stringResource(id = R.string.save),
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