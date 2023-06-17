package com.erkindilekci.vocabularybook.presentation

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.erkindilekci.vocabularybook.MainActivity
import com.erkindilekci.vocabularybook.di.AppModule
import com.erkindilekci.vocabularybook.presentation.util.navigation.Navigation
import com.erkindilekci.vocabularybook.presentation.ui.theme.VocabularyBookTheme
import com.erkindilekci.vocabularybook.util.Constants
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class VocabularyEndToEndTest {

    @get:Rule(order = 0)
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltAndroidRule.inject()
        composeRule.activity.setContent {
            VocabularyBookTheme {
                Navigation()
            }
        }
    }

    @Test
    fun saveNewVocabulary_EditAfterwards() {
        composeRule.onNodeWithTag(Constants.ADD_TAG).performClick()
        composeRule.onNodeWithTag(Constants.TITLE_TEXT_FIELD).performTextInput("title test")
        composeRule.onNodeWithTag(Constants.DESCRIPTION_TEXT_FIELD).performTextInput("description test")
        composeRule.onNodeWithTag(Constants.SENTENCE_TEXT_FIELD).performTextInput("sentence test")
        composeRule.onNodeWithTag(Constants.CATEGORY_TEXT_FIELD).performTextInput("category test")
        composeRule.onNodeWithTag(Constants.SAVE_TAG).performClick()

        composeRule.onNodeWithText("category test").assertIsDisplayed()
        composeRule.onNodeWithText("category test").performClick()

        composeRule.onNodeWithTag(Constants.DROPDOWN_MENU).performClick()
        composeRule.onNodeWithTag(Constants.DROPDOWN_MENU_ITEM).performClick()

        composeRule.onNodeWithTag(Constants.TITLE_TEXT_FIELD).performTextInput(" updated")
        composeRule.onNodeWithTag(Constants.DESCRIPTION_TEXT_FIELD).performTextInput(" updated")
        composeRule.onNodeWithTag(Constants.SENTENCE_TEXT_FIELD).performTextInput(" updated")
        composeRule.onNodeWithTag(Constants.CATEGORY_TEXT_FIELD).performTextInput(" updated")
        composeRule.onNodeWithTag(Constants.UPDATE_TAG).performClick()

        composeRule.onNodeWithText("category test updated").assertIsDisplayed()
        composeRule.onNodeWithText("category test updated").performClick()

        composeRule.onNodeWithText("title test updated").assertIsDisplayed()
    }

    @Test
    fun saveNewVocabulary_DeleteAfterwards() {
        composeRule.onNodeWithTag(Constants.ADD_TAG).performClick()
        composeRule.onNodeWithTag(Constants.TITLE_TEXT_FIELD).performTextInput("title test")
        composeRule.onNodeWithTag(Constants.DESCRIPTION_TEXT_FIELD).performTextInput("description test")
        composeRule.onNodeWithTag(Constants.SENTENCE_TEXT_FIELD).performTextInput("sentence test")
        composeRule.onNodeWithTag(Constants.CATEGORY_TEXT_FIELD).performTextInput("category test")
        composeRule.onNodeWithTag(Constants.SAVE_TAG).performClick()

        composeRule.onNodeWithText("category test").assertIsDisplayed()
        composeRule.onNodeWithText("category test").performClick()

        composeRule.onNodeWithTag(Constants.DROPDOWN_MENU).performClick()
        composeRule.onNodeWithTag(Constants.DROPDOWN_MENU_ITEM).performClick()

        composeRule.onNodeWithTag(Constants.DELETE_TAG).performClick()

        composeRule.onNodeWithText("category test").assertDoesNotExist()
    }
}
