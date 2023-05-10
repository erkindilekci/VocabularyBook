package com.erkindilekci.vocabularybook.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.vocabularybook.data.local.room.VocabularyCard
import com.erkindilekci.vocabularybook.domain.repository.VocRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateScreenViewModel @Inject constructor(
    private val repository: VocRepository
) : ViewModel() {

    var id by mutableStateOf(0)
    var title by mutableStateOf("")
    var desc by mutableStateOf("")
    var sentence by mutableStateOf("")
    var image by mutableStateOf<ByteArray?>(null)
    var category by mutableStateOf("")

    fun getVocabularyById(id: Int) {
        viewModelScope.launch {
            repository.getVocabularyById(id).collect { card ->
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
            val newVocabularyCard = VocabularyCard(id, title.trim(), desc.trim(), sentence.trim(), image, category.trim())
            repository.updateVocabulary(newVocabularyCard)
        }
    }

    fun deleteVocabulary() {
        viewModelScope.launch {
            val newVocabularyCard = VocabularyCard(id, title.trim(), desc.trim(), sentence.trim(), image, category.trim())
            repository.deleteVocabulary(newVocabularyCard)
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