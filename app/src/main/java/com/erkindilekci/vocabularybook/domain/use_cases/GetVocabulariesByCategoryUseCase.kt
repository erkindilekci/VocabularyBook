package com.erkindilekci.vocabularybook.domain.use_cases

import com.erkindilekci.vocabularybook.domain.model.VocabularyCard
import com.erkindilekci.vocabularybook.domain.repository.VocRepository
import kotlinx.coroutines.flow.Flow

class GetVocabulariesByCategoryUseCase(
    private val repository: VocRepository
) {
    operator fun invoke(category: String): Flow<List<VocabularyCard>> {
        return repository.getVocabulariesByCategory(category)
    }
}