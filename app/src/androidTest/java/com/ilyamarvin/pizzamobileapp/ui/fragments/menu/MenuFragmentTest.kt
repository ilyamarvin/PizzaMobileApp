package com.ilyamarvin.pizzamobileapp.ui.fragments.menu

import android.os.SystemClock
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ilyamarvin.pizzamobileapp.R
import com.ilyamarvin.pizzamobileapp.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MenuFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isMenuFragmentVisible() {

        onView(withId(R.id.menu_products_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_selectListItem_isDetailFragmentVisible() {

        onView(withId(R.id.menu_products_recycler_view)).check(matches(isDisplayed()))

        onView(withId(R.id.menu_products_recycler_view))
            .perform(actionOnItemAtPosition<MenuAdapter.MenuViewHolder>(0, click()))

        onView(withId(R.id.product_details_title)).check(matches(withText("Пицца Курица Терияки")))
    }

    @Test
    fun test_backNavigation_toMenuFragment() {

        onView(withId(R.id.menu_products_recycler_view))
            .perform(actionOnItemAtPosition<MenuAdapter.MenuViewHolder>(0, click()))

        onView(withId(R.id.product_details_title)).check(matches(withText("Пицца Курица Терияки")))

        pressBack()

        onView(withId(R.id.menu_products_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_pressAddBtn_fromProductDetailsFragment() {

        onView(withId(R.id.menu_products_recycler_view))
            .perform(actionOnItemAtPosition<MenuAdapter.MenuViewHolder>(0, click()))

        onView(withId(R.id.product_details_title)).check(matches(withText("Пицца Курица Терияки")))

        onView(withId(R.id.add_product_details_to_cart_btn)).perform(click())

        onView(withId(R.id.menu_products_recycler_view)).check(matches(isDisplayed()))
    }
}
