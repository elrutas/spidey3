package com.example.lucas.spidey2.helpers

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import org.hamcrest.CoreMatchers

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

} }
