package com.example.lucas.spidey3

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import com.example.lucas.spidey3.features.common.data.repository.ComicRepository
import com.example.lucas.spidey3.di.daggerMockRule
import com.example.lucas.spidey3.helpers.UITestHelpers.Companion.viewWithText
import com.example.lucas.spidey3.internal.utils.testing.ComicMother
import com.example.lucas.spidey3.features.comicdetail.ui.ComicDetailActivity
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`

class ComicDetailTest {

    @get:Rule val daggerRule = daggerMockRule()
    @get:Rule var activityRule = ActivityTestRule(ComicDetailActivity::class.java, false, false)

    private val comicRepository: ComicRepository = mock()
    private val comic = ComicMother.aComic()

    @Before
    fun setup() {
        `when`(comicRepository.getComic(any())).thenReturn(Single.just(comic))
    }

    @Test
    fun when_intent_does_not_contain_comic_id_then_activity_finishes() {
        activityRule.launchActivity(Intent())

        assertTrue(activityRule.activity.isFinishing)
    }

    @Test
    fun when_intent_contains_title_then_is_displayed_at_the_bottom() {
        val title = "Awesome Comic"
        activityRule.launchActivity(getIntentWithExtras(title))

        viewWithText(R.id.comic_detail_title, title)
        viewWithText(R.id.comic_detail_description, comic.description)
    }

    @Test
    fun when_comic_is_loaded_then_comic_description_is_displayed() {
        activityRule.launchActivity(getIntentWithExtras())

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
