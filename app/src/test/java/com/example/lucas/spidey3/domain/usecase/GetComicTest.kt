package com.example.lucas.spidey3.domain.usecase

import com.example.lucas.spidey3.features.common.data.repository.ComicRepository
import com.example.lucas.spidey3.features.comicdetail.domain.model.Comic
import com.example.lucas.spidey3.features.comicdetail.domain.usecase.GetComic
import com.example.lucas.spidey3.helpers.BaseUsecaseTest
import com.example.lucas.spidey3.internal.utils.testing.ComicMother
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetComicTest : BaseUsecaseTest<Comic>() {

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
                .thenReturn(Single.error(exception))

        givenUsecase(getComic.withParams(42))

        advanceTime()

        assertErrorWithValue(exception)
    }

    @Test
    fun when_repo_returns_values_then_usecase_calls_onNext_with_values() {
        val comic = ComicMother.aComic()
        Mockito.`when`(comicRepository.getComic(eq(comic.id)))
                .thenReturn(Single.just(comic))

        givenUsecase(getComic.withParams(comic.id))

        advanceTime()

        assertCompleteWithValue(comic)
    }
}