package com.example.lucas.spidey2

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.lucas.spidey2.data.repository.ComicRepository
import com.example.lucas.spidey2.di.daggerMockRule
import com.example.lucas.spidey2.helpers.UITestHelpers.Companion.toolbarTitleIs
import com.example.lucas.spidey2.helpers.UITestHelpers.Companion.viewWithText
import com.example.lucas.spidey2.internal.utils.testing.ComicMother
import com.example.lucas.spidey2.ui.features.comicdetail.ComicDetailActivity
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class ComicDetailTest {

    @get:Rule val daggerRule = daggerMockRule()
    @get:Rule var activityRule = ActivityTestRule(ComicDetailActivity::class.java, false, false)

    val comicRepository: ComicRepository = mock()
    val comic = ComicMother.aComic()

    @Before
    fun setup() {
        `when`(comicRepository.getComic(any())).thenReturn(Observable.just(comic))
    }

    @Test
    fun when_intent_does_not_contain_comic_id_then_activity_finishes() {
        activityRule.launchActivity(Intent())

        assertTrue(activityRule.activity.isFinishing)
    }

    @Test
    fun when_intent_contains_title_then_is_displayed_in_bar() {
        val title = "Awesome Comic"
        activityRule.launchActivity(getIntentWithExtras(title))

        toolbarTitleIs(title)
    }

    @Test
    fun when_comic_is_loaded_then_comic_title_and_description_are_displayed() {
        activityRule.launchActivity(getIntentWithExtras())

        viewWithText(R.id.comic_detail_title, comic.title)
        viewWithText(R.id.comic_detail_description, comic.description)
    }

    private fun getIntentWithExtras(title: String = "My title", comicId: Int = 10): Intent {
        val intentWithTitle = Intent()
        intentWithTitle.putExtra(ComicDetailActivity.COMIC_ID_EXTRA, comicId)
        intentWithTitle.putExtra(ComicDetailActivity.COMIC_TITLE_EXTRA, title)
        intentWithTitle.putExtra(ComicDetailActivity.COMIC_THUMBNAIL, ComicMother.aComic().thumbnailUrl)

        return intentWithTitle
    }
}
