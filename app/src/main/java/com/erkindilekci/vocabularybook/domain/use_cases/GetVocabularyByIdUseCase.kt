package com.erkindilekci.vocabularybook.domain.use_cases

import com.erkindilekci.vocabularybook.domain.model.VocabularyCard
import com.erkindilekci.vocabularybook.domain.repository.VocRepository
import kotlinx.coroutines.flow.Flow

class GetVocabularyByIdUseCase(
    private val repository: VocRepository
) {
    operator fun invoke(id: Int): Flow<VocabularyCard> {
        return repository.getVocabularyById(id)
    }
}
