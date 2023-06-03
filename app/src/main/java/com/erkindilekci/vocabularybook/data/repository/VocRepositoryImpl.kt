package com.erkindilekci.vocabularybook.data.repository

import com.erkindilekci.vocabularybook.domain.model.VocabularyCard
import com.erkindilekci.vocabularybook.data.datasource.VocabularyDao
import com.erkindilekci.vocabularybook.domain.repository.VocRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VocRepositoryImpl @Inject constructor(
    private val dao: VocabularyDao
) : VocRepository {
    override fun getVocabulariesByCategory(category: String): Flow<List<VocabularyCard>> {
        return dao.getVocabulariesByCategory(category)
    }

    override suspend fun insertVocabulary(vocabularyCard: VocabularyCard) {
        dao.insertVocabulary(vocabularyCard)
    }

    override suspend fun deleteVocabulary(vocabularyCard: VocabularyCard) {
        dao.deleteVocabulary(vocabularyCard)
    }

    override fun getAllCategories(): Flow<List<String>> {
        return dao.getAllCategories()
    }

    override fun getVocabularyById(id: Int): Flow<VocabularyCard> {
        return dao.getVocabularyById(id)
    }
}
