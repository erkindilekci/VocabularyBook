package com.erkindilekci.vocabularybook.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.vocabularybook.data.repository.DataStoreRepository
import com.erkindilekci.vocabularybook.domain.use_cases.VocabularyUseCases
import com.erkindilekci.vocabularybook.presentation.viewmodels.screenstates.CategoryScreenState
import com.erkindilekci.vocabularybook.util.ColorFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryScreenViewModel @Inject constructor(
    private val useCases: VocabularyUseCases,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _categoryState = MutableStateFlow(CategoryScreenState())
    val categoryState: StateFlow<CategoryScreenState> = _categoryState.asStateFlow()

    init {
        getAllCategories()
    }

    fun getAllCategories() {
        viewModelScope.launch {
            useCases.getAllCategoriesUseCase().collect { list ->
                val categoryList = list.toSet()

                _categoryState.update {
                    it.copy(
                        categoryList = categoryList
                    )
                }
            }
        }
    }

    fun readColorFilter() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                dataStoreRepository.readColorFilter.map { ColorFilter.valueOf(it) }
                    .collect { colorFilter ->
                        _categoryState.update {
                            it.copy(
                                actualColorFilter = colorFilter
                            )
                        }
                    }
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    fun persistColorFilter(colorFilter: ColorFilter) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.persistColorFilter(colorFilter)
        }
    }
}
