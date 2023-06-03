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
class UpdateScreenViewModel @Inject constructor(
    private val useCases: VocabularyUseCases
) : ViewModel() {

    var id by mutableStateOf(0)
        private set
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

    fun getVocabularyById(id: Int) {
        viewModelScope.launch {
            useCases.getVocabularyByIdUseCase(id).collect { card ->
                card?.let {
                    this@UpdateScreenViewModel.id = card.id
                    title = card.title
                    desc = card.desc
                    sentence = card.sentence ?: ""
                    image = card.image
                    category = card.category
                }
            }
        }
    }


    fun updateVocabulary() {
        viewModelScope.launch {
            val newVocabularyCard = VocabularyCard(
                id,
                title.trim(),
                desc.trim(),
                sentence.trim(),
                image,
                category.trim()
            )
            useCases.addVocabularyUseCase(newVocabularyCard)
        }
    }

    fun deleteVocabulary() {
        viewModelScope.launch {
            val newVocabularyCard = VocabularyCard(
                id,
                title.trim(),
                desc.trim(),
                sentence.trim(),
                image,
                category.trim()
            )
            useCases.deleteVocabularyUseCase(newVocabularyCard)
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
