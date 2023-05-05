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
class LoginActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun emptyNumberField_causesError() {
        onView(withId(R.id.button_check_number))
            .perform(ViewActions.click())

        onView(withId(R.id.phone_number_edit_text))
            .check(ViewAssertions.matches(hasErrorText("Поле не должно быть пустым")))
    }

    @Test
    fun shortNumberField_causesError() {

        onView(withId(R.id.phone_number_edit_text))
            .perform(ViewActions.typeText("123"))

        Espresso.closeSoftKeyboard()

        onView(withId(R.id.button_check_number))
            .perform(ViewActions.click())

        onView(withId(R.id.phone_number_edit_text))
            .check(ViewAssertions.matches(hasErrorText("Вы ввели не полный номер телефона")))
    }

    @Test
    fun correctNumberField_activitySwitches() {

        onView(withId(R.id.phone_number_edit_text))
            .perform(ViewActions.typeText("89999999999"))

        Espresso.closeSoftKeyboard()

        onView(withId(R.id.button_check_number))
            .perform(ViewActions.click())
    }

}