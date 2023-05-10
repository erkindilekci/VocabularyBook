package com.erkindilekci.vocabularybook.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.erkindilekci.vocabularybook.presentation.ui.theme.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.vocabularybook.data.repository.DataStoreRepository
import com.erkindilekci.vocabularybook.domain.repository.VocRepository
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyStatusBarColor
import com.erkindilekci.vocabularybook.presentation.ui.theme.MyStatusBarColorPurple
import com.erkindilekci.vocabularybook.presentation.viewmodels.screenstates.CategoryScreenState
import com.erkindilekci.vocabularybook.util.ColorFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
@HiltViewModel
class CategoryScreenViewModel @Inject constructor(
    private val repository: VocRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _categoryState = MutableStateFlow(CategoryScreenState())
    val categoryState: StateFlow<CategoryScreenState> = _categoryState.asStateFlow()

    init {
        getAllCategories()
        readColorFilter()
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

    fun readColorFilter() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                dataStoreRepository.readColorFilter.map { ColorFilter.valueOf(it) }.collect { colorFilter ->
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

}*/

/*
@HiltViewModel
class CategoryScreenViewModel @Inject constructor(
    private val repository: VocRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _categoryState = MutableStateFlow(CategoryScreenState())
    val categoryState: StateFlow<CategoryScreenState> = _categoryState.asStateFlow()

    init {
        getAllCategories()
        readColorFilter()
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

    fun readColorFilter() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                dataStoreRepository.readColorFilter.map { ColorFilter.valueOf(it) }
                    .collect { colorFilter ->
                        _categoryState.update {
                            // Güncellemeden önce renkleri değiştir
                            when (colorFilter) {
                                ColorFilter.Purple -> {
                                    MyStatusBarColor = MyStatusBarColorPurple
                                    MyTopAppBarColor = MyTopAppBarColorPurple
                                    MyBackgroundColor = MyBackgroundColorPurple
                                    MyButtonTextColor = MyButtonTextColorPurple
                                    MyCardColor = MyCardColorPurple
                                }

                                ColorFilter.Orange -> {
                                    MyStatusBarColor = MyStatusBarColorOrange
                                    MyTopAppBarColor = MyTopAppBarColorOrange
                                    MyBackgroundColor = MyBackgroundColorOrange
                                    MyButtonTextColor = MyButtonTextColorOrange
                                    MyCardColor = MyCardColorOrange
                                }

                                ColorFilter.Maroon -> {
                                    MyStatusBarColor = MyStatusBarColorMaroon
                                    MyTopAppBarColor = MyTopAppBarColorMaroon
                                    MyBackgroundColor = MyBackgroundColorMaroon
                                    MyButtonTextColor = MyButtonTextColorMaroon
                                    MyCardColor = MyCardColorMaroon
                                }

                                ColorFilter.Blue -> {
                                    MyStatusBarColor = MyStatusBarColorBlue
                                    MyTopAppBarColor = MyTopAppBarColorBlue
                                    MyBackgroundColor = MyBackgroundColorBlue
                                    MyButtonTextColor = MyButtonTextColorBlue
                                    MyCardColor = MyCardColorBlue
                                }
                            }

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

        // Renkleri değiştir
        when (colorFilter) {
            ColorFilter.Purple -> {
                MyStatusBarColor = MyStatusBarColorPurple
                MyTopAppBarColor = MyTopAppBarColorPurple
                MyBackgroundColor = MyBackgroundColorPurple
                MyButtonTextColor = MyButtonTextColorPurple
                MyCardColor = MyCardColorPurple
            }

            ColorFilter.Orange -> {
                MyStatusBarColor = MyStatusBarColorOrange
                MyTopAppBarColor = MyTopAppBarColorOrange
                MyBackgroundColor = MyBackgroundColorOrange
                MyButtonTextColor = MyButtonTextColorOrange
                MyCardColor = MyCardColorOrange
            }

            ColorFilter.Maroon -> {
                MyStatusBarColor = MyStatusBarColorMaroon
                MyTopAppBarColor = MyTopAppBarColorMaroon
                MyBackgroundColor = MyBackgroundColorMaroon
                MyButtonTextColor = MyButtonTextColorMaroon
                MyCardColor = MyCardColorMaroon
            }

            ColorFilter.Blue -> {
                MyStatusBarColor = MyStatusBarColorBlue
                MyTopAppBarColor = MyTopAppBarColorBlue
                MyBackgroundColor = MyBackgroundColorBlue
                MyButtonTextColor = MyButtonTextColorBlue
                MyCardColor = MyCardColorBlue
            }
        }
    }
}*/

@HiltViewModel
class CategoryScreenViewModel @Inject constructor(
    private val repository: VocRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    private val _categoryState = MutableStateFlow(CategoryScreenState(
        actualColorFilter = ColorFilter.Purple
    ))
    val categoryState: StateFlow<CategoryScreenState> = _categoryState.asStateFlow()

    init {
        getAllCategories()
        readColorFilter()
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

    fun readColorFilter() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                dataStoreRepository.readColorFilter.map { ColorFilter.valueOf(it) }
                    .collect { colorFilter ->
                        val updatedState = when (colorFilter) {
                            ColorFilter.Purple -> {
                                _categoryState.value.withPurpleColors()
                                    .withPurpleTopAppBarColor()
                            }
                            ColorFilter.Orange -> {
                                _categoryState.value.withOrangeColors()
                                    .withOrangeTopAppBarColor()
                            }
                            ColorFilter.Maroon -> {
                                _categoryState.value.withMaroonColors()
                                    .withMaroonTopAppBarColor()
                            }
                            ColorFilter.Blue -> {
                                _categoryState.value.withBlueColors()
                                    .withBlueTopAppBarColor()
                            }
                        }
                        _categoryState.value = updatedState
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

        _categoryState.update {
            val updatedState = when (colorFilter) {
                ColorFilter.Purple -> CategoryScreenState(
                    categoryList = it.categoryList,
                    actualColorFilter = colorFilter
                ).withPurpleColors()

                ColorFilter.Orange -> CategoryScreenState(
                    categoryList = it.categoryList,
                    actualColorFilter = colorFilter
                ).withOrangeColors()

                ColorFilter.Maroon -> CategoryScreenState(
                    categoryList = it.categoryList,
                    actualColorFilter = colorFilter
                ).withMaroonColors()

                ColorFilter.Blue -> CategoryScreenState(
                    categoryList = it.categoryList,
                    actualColorFilter = colorFilter
                ).withBlueColors()
            }
            updatedState
        }
    }
}

private fun CategoryScreenState.withPurpleColors(): CategoryScreenState {
    MyStatusBarColor = MyStatusBarColorPurple
    MyTopAppBarColor = MyTopAppBarColorPurple
    MyBackgroundColor = MyBackgroundColorPurple
    MyButtonTextColor = MyButtonTextColorPurple
    MyCardColor = MyCardColorPurple
    return copy()
}

private fun CategoryScreenState.withOrangeColors(): CategoryScreenState {
    MyStatusBarColor = MyStatusBarColorOrange
    MyTopAppBarColor = MyTopAppBarColorOrange
    MyBackgroundColor = MyBackgroundColorOrange
    MyButtonTextColor = MyButtonTextColorOrange
    MyCardColor = MyCardColorOrange
    return copy()
}

private fun CategoryScreenState.withMaroonColors(): CategoryScreenState {
    MyStatusBarColor = MyStatusBarColorMaroon
    MyTopAppBarColor = MyTopAppBarColorMaroon
    MyBackgroundColor = MyBackgroundColorMaroon
    MyButtonTextColor = MyButtonTextColorMaroon
    MyCardColor = MyCardColorMaroon
    return copy()
}

private fun CategoryScreenState.withBlueColors(): CategoryScreenState {
    MyStatusBarColor = MyStatusBarColorBlue
    MyTopAppBarColor = MyTopAppBarColorBlue
    MyBackgroundColor = MyBackgroundColorBlue
    MyButtonTextColor = MyButtonTextColorBlue
    MyCardColor = MyCardColorBlue
    return copy()
}

private fun CategoryScreenState.withPurpleTopAppBarColor(): CategoryScreenState {
    MyTopAppBarColor = MyTopAppBarColorPurple
    return copy()
}
private fun CategoryScreenState.withOrangeTopAppBarColor(): CategoryScreenState {
    MyTopAppBarColor = MyTopAppBarColorOrange
    return copy()
}
private fun CategoryScreenState.withMaroonTopAppBarColor(): CategoryScreenState {
    MyTopAppBarColor = MyTopAppBarColorMaroon
    return copy()
}
private fun CategoryScreenState.withBlueTopAppBarColor(): CategoryScreenState {
    MyTopAppBarColor = MyTopAppBarColorBlue
    return copy()
}