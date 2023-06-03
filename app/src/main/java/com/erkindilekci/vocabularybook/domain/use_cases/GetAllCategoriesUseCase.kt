package com.erkindilekci.vocabularybook.domain.use_cases

import com.erkindilekci.vocabularybook.domain.repository.VocRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategoriesUseCase(
    private val repository: VocRepository
) {
    operator fun invoke(): Flow<List<String>> {
        return repository.getAllCategories()
    }
}