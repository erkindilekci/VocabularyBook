package com.erkindilekci.vocabularybook

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyBackgroundColor
import com.erkindilekci.vocabularybook.presentation.ui.theme.VocabularyBookTheme
import com.erkindilekci.vocabularybook.presentation.util.RequestMultiplePermissions
import com.erkindilekci.vocabularybook.presentation.util.RequestPermission
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
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                        RequestMultiplePermissions(
                            permissions = listOf(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            )
                        )
                    } else {
                        RequestPermission(permission = Manifest.permission.READ_MEDIA_IMAGES)
                    }
                }
            }
        }
    }
}
