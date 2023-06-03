package com.erkindilekci.vocabularybook.domain.use_cases

import com.erkindilekci.vocabularybook.data.repository.FakeVocRepository
import com.erkindilekci.vocabularybook.domain.model.VocabularyCard
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllCategoriesUseCaseTest {

    private lateinit var addVocabularyUseCase: AddVocabularyUseCase
    private lateinit var getAllCategoriesUseCase: GetAllCategoriesUseCase
    private lateinit var fakeVocRepository: FakeVocRepository

    @Before
    fun setup() {
        fakeVocRepository = FakeVocRepository()
        getAllCategoriesUseCase = GetAllCategoriesUseCase(fakeVocRepository)
        addVocabularyUseCase = AddVocabularyUseCase(fakeVocRepository)

        val vocabularyToInsert1 = VocabularyCard(
            title = "title",
            desc = "description",
            sentence = "sentence",
            image = byteArrayOf(),
            category = "category1",
        )

        val vocabularyToInsert2 = VocabularyCard(
            title = "title",
            desc = "description",
            sentence = "sentence",
            image = byteArrayOf(),
            category = "category2",
        )

        runBlocking {
            addVocabularyUseCase(vocabularyToInsert1)
            addVocabularyUseCase(vocabularyToInsert2)
        }
    }

    @Test
    fun `Get call category names, returns true`() = runBlocking {
        val categories = getAllCategoriesUseCase().first()

        assertThat(categories).isEqualTo(listOf("category1", "category2"))
    }
}
