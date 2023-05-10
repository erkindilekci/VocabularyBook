package com.erkindilekci.vocabularybook.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.vocabularybook.domain.repository.VocRepository
import com.erkindilekci.vocabularybook.presentation.viewmodels.screenstates.CategoryScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryScreenViewModel @Inject constructor(
    private val repository: VocRepository
) : ViewModel() {

    private val _categoryState = MutableStateFlow(CategoryScreenState())
    val categoryState: StateFlow<CategoryScreenState> = _categoryState.asStateFlow()

    init {
        getAllCategories()
    }

    fun getAllCategories() {
        viewModelScope.launch {
            repository.getAllCategories().collect { list ->
                val categoryList = list.toSet()

                _categoryState.update {
                    it.copy(
                        categoryList = categoryList
                    )
                }
            }
        }
    }
}