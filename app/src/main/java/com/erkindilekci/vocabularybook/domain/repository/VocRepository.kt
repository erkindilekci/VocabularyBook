package com.erkindilekci.vocabularybook.domain.repository

import com.erkindilekci.vocabularybook.data.local.room.VocabularyCard
import kotlinx.coroutines.flow.Flow

interface VocRepository {
    //fun getAllVocabularies(): Flow<List<VocabularyCard>>

    fun getVocabulariesByCategory(category: String): Flow<List<VocabularyCard>>

    suspend fun insertVocabulary(vocabularyCard: VocabularyCard)

    suspend fun deleteVocabulary(vocabularyCard: VocabularyCard)

    suspend fun deleteAll()

    fun getAllCategories(): Flow<List<String>>
}