package com.erkindilekci.vocabularybook.util

import androidx.compose.ui.graphics.Color
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyTopAppBarColorBlue
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyTopAppBarColorMaroon
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyTopAppBarColorOrange
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyTopAppBarColorPurple

enum class ColorFilter(val color: Color) {
    Purple(MyTopAppBarColorPurple),
    Orange(MyTopAppBarColorOrange),
    Maroon(MyTopAppBarColorMaroon),
    Blue(MyTopAppBarColorBlue),
}
