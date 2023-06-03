package com.erkindilekci.vocabularybook.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.vocabularybook.domain.model.VocabularyCard
import com.erkindilekci.vocabularybook.domain.repository.VocRepository
import com.erkindilekci.vocabularybook.domain.use_cases.VocabularyUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddScreenViewModel @Inject constructor(
    private val useCases: VocabularyUseCases
) : ViewModel() {

    var title by mutableStateOf("")
        private set
    var desc by mutableStateOf("")
        private set
    var sentence by mutableStateOf("")
        private set
    var image by mutableStateOf<ByteArray?>(null)
        private set
    var category by mutableStateOf("")
        private set

    fun addVocabulary() {
        val newVocabularyCard = VocabularyCard(
            title = title,
            desc = desc,
            sentence = sentence,
            image = image,
            category = category
        )

        viewModelScope.launch {
            useCases.addVocabularyUseCase(newVocabularyCard)
        }
    }

    fun updateTitle(newTitle: String) {
        title = newTitle
    }

    fun updateDescription(newDesc: String) {
        desc = newDesc
    }

    fun updateSentence(newSentence: String) {
        sentence = newSentence
    }

    fun updateCategory(newCategory: String) {
        category = newCategory
    }

    fun updateImage(newImage: ByteArray?) {
        image = newImage
    }
}
