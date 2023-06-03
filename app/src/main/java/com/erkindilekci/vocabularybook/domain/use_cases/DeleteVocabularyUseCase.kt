package com.erkindilekci.vocabularybook.domain.use_cases

import com.erkindilekci.vocabularybook.domain.model.VocabularyCard
import com.erkindilekci.vocabularybook.domain.repository.VocRepository

class DeleteVocabularyUseCase(
    private val repository: VocRepository
) {
    suspend operator fun invoke(vocabularyCard: VocabularyCard) {
        repository.deleteVocabulary(vocabularyCard)
    }
}
