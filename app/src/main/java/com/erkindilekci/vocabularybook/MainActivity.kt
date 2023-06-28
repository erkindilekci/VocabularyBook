package com.erkindilekci.vocabularybook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.erkindilekci.vocabularybook.presentation.util.ui.theme.MyBackgroundColor
import com.erkindilekci.vocabularybook.presentation.util.ui.theme.VocabularyBookTheme
import com.erkindilekci.vocabularybook.presentation.util.Navigation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VocabularyBookTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MyBackgroundColor) {
                    Navigation()
                }
            }
        }
    }
}
