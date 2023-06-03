package com.erkindilekci.vocabularybook.data.repository

import com.erkindilekci.vocabularybook.domain.model.VocabularyCard
import com.erkindilekci.vocabularybook.domain.repository.VocRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeVocRepository : VocRepository {

    private val vocabularyList = mutableListOf<VocabularyCard>()

    override fun getVocabulariesByCategory(category: String): Flow<List<VocabularyCard>> {
        return flow { emit(vocabularyList) }
    }

    override suspend fun insertVocabulary(vocabularyCard: VocabularyCard) {
        vocabularyList.add(vocabularyCard)
    }

    override suspend fun deleteVocabulary(vocabularyCard: VocabularyCard) {
        vocabularyList.remove(vocabularyCard)
    }

    override fun getAllCategories(): Flow<List<String>> {
        return flow { emit(vocabularyList.map { it.category }) }
    }

    override fun getVocabularyById(id: Int): Flow<VocabularyCard> {
        return flow {
            emit(vocabularyList.find { it.id == id }
                ?: VocabularyCard(
                    0,
                    "",
                    "",
                    "",
                    byteArrayOf(),
                    ""
                )
            )
        }
    }
}
