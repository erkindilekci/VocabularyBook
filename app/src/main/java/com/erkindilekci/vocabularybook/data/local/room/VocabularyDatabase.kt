package com.erkindilekci.vocabularybook.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VocabularyCard::class], version = 2)
abstract class VocabularyDatabase : RoomDatabase() {
    abstract fun vocabularyDao(): VocabularyDao
}