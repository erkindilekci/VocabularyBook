package com.erkindilekci.vocabularybook.domain.use_cases

import com.erkindilekci.vocabularybook.data.repository.FakeVocRepository
import com.erkindilekci.vocabularybook.domain.model.VocabularyCard
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetVocabulariesByCategoryUseCaseTest {

    private lateinit var addVocabularyUseCase: AddVocabularyUseCase
    private lateinit var getVocabulariesByCategoryUseCase: GetVocabulariesByCategoryUseCase
    private lateinit var fakeVocRepository: FakeVocRepository

    @Before
    fun setup() {
        fakeVocRepository = FakeVocRepository()
        getVocabulariesByCategoryUseCase = GetVocabulariesByCategoryUseCase(fakeVocRepository)
        addVocabularyUseCase = AddVocabularyUseCase(fakeVocRepository)
    }

    @Test
    fun `Get vocabularies by category, returns true`() = runBlocking {
        val vocabularyToInsert = VocabularyCard(
            title = "title",
            desc = "description",
            sentence = "sentence",
            image = byteArrayOf(),
            category = "category",
        )

        addVocabularyUseCase(vocabularyToInsert)

        val vocabularies = getVocabulariesByCategoryUseCase("category").first()

        assertThat(vocabularies).contains(vocabularyToInsert)
    }
}
