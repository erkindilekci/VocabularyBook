package com.erkindilekci.vocabularybook.di

import android.content.Context
import androidx.room.Room
import com.erkindilekci.vocabularybook.data.datasource.VocabularyDao
import com.erkindilekci.vocabularybook.data.datasource.VocabularyDatabase
import com.erkindilekci.vocabularybook.data.repository.VocRepositoryImpl
import com.erkindilekci.vocabularybook.domain.repository.VocRepository
import com.erkindilekci.vocabularybook.domain.use_cases.AddVocabularyUseCase
import com.erkindilekci.vocabularybook.domain.use_cases.DeleteVocabularyUseCase
import com.erkindilekci.vocabularybook.domain.use_cases.GetAllCategoriesUseCase
import com.erkindilekci.vocabularybook.domain.use_cases.GetVocabulariesByCategoryUseCase
import com.erkindilekci.vocabularybook.domain.use_cases.GetVocabularyByIdUseCase
import com.erkindilekci.vocabularybook.domain.use_cases.VocabularyUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.inMemoryDatabaseBuilder(
        context,
        VocabularyDatabase::class.java
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: VocabularyDatabase) = database.vocabularyDao()

    @Provides
    @Singleton
    fun provideRepo(dao: VocabularyDao): VocRepository = VocRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideVocabularyUseCases(repository: VocRepository): VocabularyUseCases {
        return VocabularyUseCases(
            addVocabularyUseCase = AddVocabularyUseCase(repository),
            deleteVocabularyUseCase = DeleteVocabularyUseCase(repository),
            getAllCategoriesUseCase = GetAllCategoriesUseCase(repository),
            getVocabulariesByCategoryUseCase = GetVocabulariesByCategoryUseCase(repository),
            getVocabularyByIdUseCase = GetVocabularyByIdUseCase(repository)
        )
    }
}
