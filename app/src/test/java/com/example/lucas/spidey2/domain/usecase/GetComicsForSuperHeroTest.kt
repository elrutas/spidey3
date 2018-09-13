package com.example.lucas.spidey2.domain.usecase

import com.example.lucas.spidey2.data.repository.ComicRepository
import com.example.lucas.spidey2.di.JUnitDaggerMockRule
import com.example.lucas.spidey2.domain.model.Comic
import com.example.lucas.spidey2.domain.model.SuperHero
import com.example.lucas.spidey2.helpers.BaseUsecaseTest
import com.example.lucas.spidey2.internal.utils.testing.ComicMother
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`

class GetComicsForSuperHeroTest : BaseUsecaseTest<List<Comic>>() {

    @get:Rule val rule = JUnitDaggerMockRule()

    val comicRepository: ComicRepository = mock()
    lateinit var getComicsForSuperHero: GetComicsForSuperHero

    @Before
    fun setup() {
        getComicsForSuperHero = GetComicsForSuperHero(comicRepository)
    }

    @Test (expected = UninitializedPropertyAccessException::class)
    fun when_params_are_not_set_then_exception_is_thrown() {
        getComicsForSuperHero.getSubscribable()
    }

    @Test
    fun when_repo_returns_error_then_usecase_calls_onError() {
        val exception = RuntimeException("The server is on fire")
        `when`(comicRepository.getListOfComics(any(), any(), any()))
                .thenReturn(Observable.error(exception))

        givenUsecase(getComicsForSuperHero.withParams(SuperHero.SPIDEY, 20, 20))

        advanceTime()

        assertErrorWithValue(exception)
    }

    @Test
    fun when_repo_returns_values_then_usecase_calls_onNext_with_values() {
        val comics: List<Comic> = ComicMother.listOfComics(10)
        `when`(comicRepository.getListOfComics(any(), any(), any()))
                .thenReturn(Observable.just(comics))

        givenUsecase(getComicsForSuperHero.withParams(SuperHero.SPIDEY, 20, 20))

        advanceTime()

        assertCompleteWithValue(comics)
    }
}