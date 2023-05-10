package com.erkindilekci.vocabularybook.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VocabularyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVocabulary(card: VocabularyCard)

    @Delete
    suspend fun deleteVocabulary(card: VocabularyCard)

    //@Query("SELECT * FROM vocabulary_table")
    //fun getAllVocabularies(): Flow<List<VocabularyCard>>

    @Query("SELECT category FROM vocabulary_table")
    fun getAllCategories(): Flow<List<String>>

    @Query("SELECT * FROM vocabulary_table WHERE category=:category")
    fun getVocabulariesByCategory(category: String): Flow<List<VocabularyCard>>

    @Query("DELETE FROM vocabulary_table")
    suspend fun deleteAll()
}