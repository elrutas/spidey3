package com.example.lucas.spidey2

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.lucas.spidey2.data.repository.ComicRepository
import com.example.lucas.spidey2.di.daggerMockRule
import com.example.lucas.spidey2.helpers.UITestHelpers.Companion.textInViewInComicListPosition
import com.example.lucas.spidey2.internal.utils.testing.ComicMother
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

        textInViewInComicListPosition(0, R.id.error_list_item_title, R.string.comic_list_item_error)
    }

    @Test
    fun when_comics_are_received_then_titles_are_displayed_in_cards() {
        val comics = ComicMother.listOfComics(20)
        `when`(comicRepository.getListOfComics(any(), any(), any()))
                .thenReturn(Observable.just(comics))

        activityRule.launchActivity(Intent())

        for (index in 0..10) {
            textInViewInComicListPosition(index, R.id.comic_title, comics[index].title)
        }
    }
}
