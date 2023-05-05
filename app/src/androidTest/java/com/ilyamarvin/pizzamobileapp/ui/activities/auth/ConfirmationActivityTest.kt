package com.ilyamarvin.pizzamobileapp.ui.activities.auth

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.ilyamarvin.pizzamobileapp.R

@RunWith(AndroidJUnit4::class)
class ConfirmationActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(ConfirmationActivity::class.java)

    @Test
    fun emptyCodeField_causesError() {
        onView(withId(R.id.confirmation_btn))
            .perform(ViewActions.click())

        onView(withId(R.id.confirmation_code_edit_text))
            .check(ViewAssertions.matches(hasErrorText("Поле не должно быть пустым")))
    }

    @Test
    fun shortCodeField_causesError() {

        onView(withId(R.id.confirmation_code_edit_text))
            .perform(ViewActions.typeText("123"))

        Espresso.closeSoftKeyboard()

        onView(withId(R.id.confirmation_btn))
            .perform(ViewActions.click())

        onView(withId(R.id.confirmation_code_edit_text))
            .check(ViewAssertions.matches(hasErrorText("Код состоит из 6 значений")))
    }

    @Test
    fun correctNumberField_activitySwitches() {

        onView(withId(R.id.confirmation_code_edit_text))
            .perform(ViewActions.typeText("123456"))

        Espresso.closeSoftKeyboard()

        onView(withId(R.id.confirmation_btn))
            .perform(ViewActions.click())
    }

}