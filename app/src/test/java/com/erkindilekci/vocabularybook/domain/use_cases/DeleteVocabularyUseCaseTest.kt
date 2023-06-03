package com.erkindilekci.vocabularybook.domain.use_cases

import com.erkindilekci.vocabularybook.data.repository.FakeVocRepository
import com.erkindilekci.vocabularybook.domain.model.VocabularyCard
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteVocabularyUseCaseTest {
    private lateinit var addVocabularyUseCase: AddVocabularyUseCase
    private lateinit var deleteVocabularyUseCase: DeleteVocabularyUseCase
    private lateinit var fakeVocRepository: FakeVocRepository

    @Before
    fun setup() {
        fakeVocRepository = FakeVocRepository()
        addVocabularyUseCase = AddVocabularyUseCase(fakeVocRepository)
        deleteVocabularyUseCase = DeleteVocabularyUseCase(fakeVocRepository)
    }

    @Test
    fun `Delete vocabulary with vocabulary instance, returns true`() = runBlocking {
        val vocabularyCardToDelete = VocabularyCard(
            title = "title",
            desc = "description",
            sentence = "sentence",
            image = byteArrayOf(),
            category = "category",
        )

        addVocabularyUseCase(vocabularyCardToDelete)
        deleteVocabularyUseCase(vocabularyCardToDelete)

        val vocabularyList = fakeVocRepository.getVocabulariesByCategory("category").first()

        assertThat(vocabularyList).doesNotContain(vocabularyCardToDelete)
    }
}
