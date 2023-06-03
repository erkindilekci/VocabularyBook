package com.erkindilekci.vocabularybook.presentation.viewmodels.screenstates

import com.erkindilekci.vocabularybook.domain.model.VocabularyCard

data class DetailScreenState(
    val vocabularyList: List<VocabularyCard> = emptyList()
)
