package com.erkindilekci.vocabularybook.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.erkindilekci.vocabularybook.domain.model.VocabularyCard

@Database(entities = [VocabularyCard::class], version = 3, exportSchema = false)
abstract class VocabularyDatabase : RoomDatabase() {
    abstract fun vocabularyDao(): VocabularyDao
}
