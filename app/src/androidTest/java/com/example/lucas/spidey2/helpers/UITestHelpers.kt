package com.example.lucas.spidey2.helpers

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import org.hamcrest.CoreMatchers.allOf

fun textIsDisplayed(resId: Int) {
    onView(allOf<View>(
            withText(getString(resId)),
            isDisplayed())
    ).check(matches(isCompletelyDisplayed()))
}

fun textIsDisplayed(text: String) {
    onView(allOf<View>(
            withText(text),
            isDisplayed())
    ).check(matches(isCompletelyDisplayed()))
}

fun getString(id: Int): String {
    val targetContext = InstrumentationRegistry.getTargetContext()
    return targetContext.resources.getString(id)
}