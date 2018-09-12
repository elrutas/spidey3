package com.example.lucas.spidey2.helpers

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.example.lucas.spidey2.R
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf

class UITestHelpers { companion object {

    fun textIsDisplayed(resId: Int) {
        Espresso.onView(CoreMatchers.allOf<View>(
                ViewMatchers.withText(getString(resId)),
                ViewMatchers.isDisplayed())
        ).check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
    }

    fun textIsDisplayed(text: String) {
        Espresso.onView(CoreMatchers.allOf<View>(
                ViewMatchers.withText(text),
                ViewMatchers.isDisplayed())
        ).check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
    }

    fun getString(id: Int): String {
        val targetContext = InstrumentationRegistry.getTargetContext()
        return targetContext.resources.getString(id)
    }

    fun toolbarTitleIs(title: String) {
        Espresso.onView(
                CoreMatchers.allOf(
                        ViewMatchers.isAssignableFrom(TextView::class.java),
                        ViewMatchers.withParent(ViewMatchers.isAssignableFrom(Toolbar::class.java))))
                .check(ViewAssertions.matches(ViewMatchers.withText(title)))
    }

    fun viewWithText(viewId: Int, textInView: String) {
        Espresso.onView(CoreMatchers.allOf(
                ViewMatchers.withId(viewId),
                ViewMatchers.withText(textInView),
                ViewMatchers.isDisplayed()
        )).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun viewWithText(viewId: Int, textId: Int) {
        Espresso.onView(CoreMatchers.allOf(
                ViewMatchers.withId(viewId),
                ViewMatchers.withText(getString(textId)),
                ViewMatchers.isDisplayed()
        )).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun textInViewInComicListPosition(position: Int, viewId: Int, resourceId: Int) {
        textInViewInComicListPosition(position,viewId, getString(resourceId))
    }

    fun textInViewInComicListPosition(position: Int, viewId: Int, text: String) {
        scrollToComicListPosition(position)
        onView(allOf(RecyclerViewMatcher.withRecyclerView(R.id.comic_list_grid)
                .atPositionOnView(position, viewId), isDisplayed()))
                .check(matches(withText(text)))
    }

    fun scrollToComicListPosition(position: Int) {
        onView(allOf(withId(R.id.comic_list_grid), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
    }

    fun clickOnComicListPosition(position: Int) {
        scrollToComicListPosition(position)
        onView(withId(R.id.comic_list_grid))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
    }
} }
