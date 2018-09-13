package com.example.lucas.spidey2.domain.usecase

import com.example.lucas.spidey2.JUnitDaggerMockRule
import com.example.lucas.spidey2.data.repository.ComicRepository
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetComicsForSuperHeroTest {

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
}