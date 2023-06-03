package com.erkindilekci.vocabularybook.domain.repository

import com.erkindilekci.vocabularybook.domain.model.VocabularyCard
import kotlinx.coroutines.flow.Flow

interface VocRepository {
    fun getVocabulariesByCategory(category: String): Flow<List<VocabularyCard>>

    suspend fun insertVocabulary(vocabularyCard: VocabularyCard)

    suspend fun deleteVocabulary(vocabularyCard: VocabularyCard)

    fun getAllCategories(): Flow<List<String>>

    fun getVocabularyById(id: Int): Flow<VocabularyCard>
}
