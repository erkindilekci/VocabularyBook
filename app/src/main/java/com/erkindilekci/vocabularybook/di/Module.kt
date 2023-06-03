package com.erkindilekci.vocabularybook.di

import android.content.Context
import androidx.room.Room
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
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        VocabularyDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: VocabularyDatabase) = database.vocabularyDao()

    @Provides
    @Singleton
    fun provideRepo(dao: VocabularyDao): VocRepository = VocRepositoryImpl(dao)
}
