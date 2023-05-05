package com.ilyamarvin.pizzamobileapp

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ilyamarvin.pizzamobileapp.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class NavigationTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun test_isMenuFragmentVisible_onAppLaunch() {

        onView(withId(R.id.menu_products_recycler_view)).check(matches(isDisplayed()))

    }

    @Test
    fun test_isProfileFragmentVisible_onBotNavBar() {

        onView(withId(R.id.profile_navigation)).perform(click())

        onView(withId(R.id.profile_hello)).check(matches(isDisplayed()))

    }

    @Test
    fun test_isContactsFragmentVisible_onBotNavBar() {

        onView(withId(R.id.contacts_navigation)).perform(click())

        onView(withId(R.id.text_about_contact)).check(matches(isDisplayed()))

    }

    @Test
    fun test_isCartFragmentVisible_onBotNavBar() {

        onView(withId(R.id.cart_navigation)).perform(click())

        onView(withId(R.id.fragment_cart)).check(matches(isDisplayed()))

    }
}
