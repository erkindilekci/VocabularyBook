package com.erkindilekci.vocabularybook.presentation.viewmodels.screenstates

import com.erkindilekci.vocabularybook.util.ColorFilter

data class CategoryScreenState(
    val categoryList: Set<String> = emptySet(),
    val actualColorFilter: ColorFilter = ColorFilter.Purple
)
