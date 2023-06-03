package com.erkindilekci.vocabularybook.domain.use_cases

import com.erkindilekci.vocabularybook.data.repository.FakeVocRepository
import com.erkindilekci.vocabularybook.domain.model.VocabularyCard
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetVocabulariesByIdUseCaseTest {
    private lateinit var addVocabularyUseCase: AddVocabularyUseCase
    private lateinit var getVocabularyByIdUseCase: GetVocabularyByIdUseCase
    private lateinit var fakeVocRepository: FakeVocRepository

    @Before
    fun setup() {
        fakeVocRepository = FakeVocRepository()
        getVocabularyByIdUseCase = GetVocabularyByIdUseCase(fakeVocRepository)
        addVocabularyUseCase = AddVocabularyUseCase(fakeVocRepository)
    }

    @Test
    fun `Get vocabulary by id, returns true`() = runBlocking {
        val vocabularyToInsert = VocabularyCard(
            title = "title",
            desc = "description",
            sentence = "sentence",
            image = byteArrayOf(),
            category = "category",
        )

        addVocabularyUseCase(vocabularyToInsert)

        val vocabulary = getVocabularyByIdUseCase(vocabularyToInsert.id).first()

        Truth.assertThat(vocabulary).isEqualTo(vocabularyToInsert)
    }
}
