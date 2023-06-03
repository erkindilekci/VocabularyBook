package com.erkindilekci.vocabularybook.domain.use_cases

import com.erkindilekci.vocabularybook.domain.model.InvalidVocabularyException
import com.erkindilekci.vocabularybook.domain.model.VocabularyCard
import com.erkindilekci.vocabularybook.domain.repository.VocRepository

class AddVocabularyUseCase(
    private val repository: VocRepository
) {
    @Throws(InvalidVocabularyException::class)
    suspend operator fun invoke(vocabulary: VocabularyCard) {
        if (vocabulary.title.trim().isEmpty()) {
            throw InvalidVocabularyException("Title can't be empty!")
        }
        if (vocabulary.desc.trim().isEmpty()) {
            throw InvalidVocabularyException("Description can't be empty!")
        }
        if (vocabulary.category.trim().isEmpty()) {
            throw InvalidVocabularyException("Category can't be empty!")
        }

        repository.insertVocabulary(vocabulary)
    }
}
