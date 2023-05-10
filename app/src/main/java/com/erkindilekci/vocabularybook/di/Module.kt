package com.erkindilekci.vocabularybook.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.erkindilekci.vocabularybook.data.local.room.VocabularyDao
import com.erkindilekci.vocabularybook.data.local.room.VocabularyDatabase
import com.erkindilekci.vocabularybook.data.repository.VocRepositoryImpl
import com.erkindilekci.vocabularybook.domain.repository.VocRepository
import com.erkindilekci.vocabularybook.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    val migration1to2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
        }
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        VocabularyDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(migration1to2).build()

    @Singleton
    @Provides
    fun provideDao(database: VocabularyDatabase) = database.vocabularyDao()

    @Provides
    @Singleton
    fun provideRepo(dao: VocabularyDao): VocRepository = VocRepositoryImpl(dao)
}