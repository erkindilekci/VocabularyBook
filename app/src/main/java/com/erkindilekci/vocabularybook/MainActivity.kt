package com.erkindilekci.vocabularybook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.erkindilekci.vocabularybook.presentation.navigation.Navigation
import com.erkindilekci.vocabularybook.presentation.ui.theme.VocabularyBookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VocabularyBookTheme {
                Navigation()
            }
        }
    }
}
