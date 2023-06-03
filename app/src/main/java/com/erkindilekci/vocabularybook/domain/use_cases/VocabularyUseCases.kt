package com.erkindilekci.vocabularybook.domain.use_cases

data class VocabularyUseCases(
    val addVocabularyUseCase: AddVocabularyUseCase,
    val deleteVocabularyUseCase: DeleteVocabularyUseCase,
    val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    val getVocabulariesByCategoryUseCase: GetVocabulariesByCategoryUseCase,
    val getVocabularyByIdUseCase: GetVocabularyByIdUseCase
)