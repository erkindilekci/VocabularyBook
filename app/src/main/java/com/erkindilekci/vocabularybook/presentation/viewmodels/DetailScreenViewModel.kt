package com.erkindilekci.vocabularybook.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.vocabularybook.domain.use_cases.VocabularyUseCases
import com.erkindilekci.vocabularybook.presentation.viewmodels.screenstates.DetailScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val useCases: VocabularyUseCases
) : ViewModel() {
    private val _detailState = MutableStateFlow(DetailScreenState())
    val detailState: StateFlow<DetailScreenState> = _detailState.asStateFlow()

    fun getVocabulariesByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getVocabulariesByCategoryUseCase(category).collect { list ->
                _detailState.update {
                    it.copy(
                        vocabularyList = list
                    )
                }
            }
        }
    }
}
