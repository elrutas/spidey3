package com.example.lucas.spidey2.domain.usecase

import com.example.lucas.spidey2.data.repository.ComicRepository
import com.example.lucas.spidey2.di.JUnitDaggerMockRule
import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.helpers.BaseUsecaseTest
import com.example.lucas.spidey2.internal.utils.testing.ComicMother
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class GetComicTest : BaseUsecaseTest<Comic>() {

    @get:Rule val rule = JUnitDaggerMockRule()

    val comicRepository: ComicRepository = mock()
    lateinit var getComic: GetComic

    @Before
    fun setup() {
        getComic = GetComic(comicRepository)
    }

    @Test(expected = IllegalStateException::class)
    fun when_params_are_not_set_then_exception_is_thrown() {
        getComic.getSubscribable()
    }

    @Test
    fun when_repo_returns_error_then_usecase_calls_onError() {
        val exception = RuntimeException("The server is on fire")
        Mockito.`when`(comicRepository.getComic(any()))
                .thenReturn(Observable.error(exception))

        givenUsecase(getComic.withParams(42))

        advanceTime()

        assertErrorWithValue(exception)
    }

    @Test
    fun when_repo_returns_values_then_usecase_calls_onNext_with_values() {
        val comic = ComicMother.aComic()
        Mockito.`when`(comicRepository.getComic(eq(comic.id)))
                .thenReturn(Observable.just(comic))

        givenUsecase(getComic.withParams(comic.id))

        advanceTime()

        assertCompleteWithValue(comic)
    }
}