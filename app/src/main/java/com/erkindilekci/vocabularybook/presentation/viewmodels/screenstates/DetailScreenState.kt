package com.erkindilekci.vocabularybook.presentation.viewmodels.screenstates

import com.erkindilekci.vocabularybook.data.local.room.VocabularyCard

data class DetailScreenState(
    val vocabularyList: List<VocabularyCard> = emptyList()
)
