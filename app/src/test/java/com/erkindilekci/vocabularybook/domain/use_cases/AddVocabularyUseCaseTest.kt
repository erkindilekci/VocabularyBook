package com.erkindilekci.vocabularybook.domain.use_cases

import com.erkindilekci.vocabularybook.data.repository.FakeVocRepository
import com.erkindilekci.vocabularybook.domain.model.InvalidVocabularyException
import com.erkindilekci.vocabularybook.domain.model.VocabularyCard
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddVocabularyUseCaseTest {

    private lateinit var addVocabularyUseCase: AddVocabularyUseCase
    private lateinit var fakeVocRepository: FakeVocRepository

    @Before
    fun setup() {
        fakeVocRepository = FakeVocRepository()
        addVocabularyUseCase = AddVocabularyUseCase(fakeVocRepository)
    }

    @Test(expected = InvalidVocabularyException::class)
    fun `Add vocabulary with empty title, throw exception`() = runBlocking {
        val vocabularyCardToInsert = VocabularyCard(
            title = "",
            desc = "description",
            sentence = "sentence",
            image = byteArrayOf(),
            category = "category",
        )

        addVocabularyUseCase(vocabularyCardToInsert)
    }

    @Test(expected = InvalidVocabularyException::class)
    fun `Add vocabulary with empty description, throw exception`() = runBlocking {
        val vocabularyCardToInsert = VocabularyCard(
            title = "title",
            desc = "",
            sentence = "sentence",
            image = byteArrayOf(),
            category = "category",
        )

        addVocabularyUseCase(vocabularyCardToInsert)
    }

    @Test(expected = InvalidVocabularyException::class)
    fun `Add vocabulary with empty category, throw exception`() = runBlocking {
        val vocabularyCardToInsert = VocabularyCard(
            title = "title",
            desc = "description",
            sentence = "sentence",
            image = byteArrayOf(),
            category = "",
        )

        addVocabularyUseCase(vocabularyCardToInsert)
    }

    @Test
    fun `Add vocabulary with a title, description and category, returns true`() = runBlocking {
        val vocabularyCardToInsert = VocabularyCard(
            title = "title",
            desc = "description",
            sentence = "sentence",
            image = byteArrayOf(),
            category = "category",
        )

        fakeVocRepository.deleteVocabulary(vocabularyCardToInsert)
        addVocabularyUseCase(vocabularyCardToInsert)


        assertThat(
            fakeVocRepository.getVocabularyById(vocabularyCardToInsert.id).first()
        ).isEqualTo(vocabularyCardToInsert)
    }
}
