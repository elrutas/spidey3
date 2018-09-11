package com.example.lucas.spidey2

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.lucas.spidey2.di.daggerMockRule
import com.example.lucas.spidey2.domain.repository.ComicRepository
import com.example.lucas.spidey2.helpers.textIsDisplayed
import com.example.lucas.spidey2.ui.features.comiclist.ComicListActivity
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class ComicListTest {

    @get:Rule val daggerRule = daggerMockRule()
    @get:Rule var activityRule = ActivityTestRule(ComicListActivity::class.java, false, false)

    val comicRepository: ComicRepository = mock()

    @Test
    fun when_repository_returns_error_then_error_item_is_shown() {
        `when`(comicRepository.getListOfComics(any(), any(), any()))
                .thenReturn(Observable.error(RuntimeException("Fetch fail")))

        activityRule.launchActivity(Intent())

        textIsDisplayed(R.string.comic_list_item_error)

    }
}
