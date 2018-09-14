package com.example.lucas.spidey2

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.lucas.spidey2.data.repository.ComicRepository
import com.example.lucas.spidey2.di.daggerMockRule
import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.helpers.UITestHelpers.Companion.clickOnComicListPosition
import com.example.lucas.spidey2.helpers.UITestHelpers.Companion.textInViewInComicListPosition
import com.example.lucas.spidey2.helpers.UITestHelpers.Companion.viewWithText
import com.example.lucas.spidey2.internal.utils.testing.ComicMother
import com.example.lucas.spidey2.ui.features.comiclist.ComicListActivity
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import java.util.*

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

        textInViewInComicListPosition(0, R.id.comic_list_error_item_title, R.string.comic_list_item_error)
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

    @Test
    fun when_usecase_has_not_returned_yet_then_loading_card_is_displayed() {
        val publisher: PublishSubject<List<Comic>> = PublishSubject.create()

        `when`(comicRepository.getListOfComics(any(), any(), any()))
                .thenReturn(publisher)

        activityRule.launchActivity(Intent())

        textInViewInComicListPosition(0, R.id.comic_list_loading_item_title, R.string.comic_list_item_loading)
    }

    @Test
    fun when_clicking_on_comic_then_comic_detail_activity_starts() {
        val comics = ComicMother.listOfComics(20)
        val positionToClick = Random().nextInt(10)

        `when`(comicRepository.getListOfComics(any(), any(), any()))
                .thenReturn(Observable.just(comics))
        `when`(comicRepository.getComic(comics[positionToClick].id))
                .thenReturn(Observable.just(comics[positionToClick]))

        activityRule.launchActivity(Intent())

        clickOnComicListPosition(positionToClick)

        viewWithText(R.id.comic_detail_title, comics[positionToClick].title)
        viewWithText(R.id.comic_detail_description, comics[positionToClick].description)
    }
}
