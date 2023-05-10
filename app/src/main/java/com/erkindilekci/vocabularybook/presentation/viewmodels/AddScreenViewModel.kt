package com.erkindilekci.vocabularybook.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.vocabularybook.data.local.room.VocabularyCard
import com.erkindilekci.vocabularybook.domain.repository.VocRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddScreenViewModel @Inject constructor(
    private val repository: VocRepository
) : ViewModel() {

    fun addVocabulary(vocabularyCard: VocabularyCard) {
        viewModelScope.launch {
            repository.insertVocabulary(vocabularyCard)
        }
    }
}