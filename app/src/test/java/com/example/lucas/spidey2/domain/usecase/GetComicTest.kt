package com.example.lucas.spidey2.domain.usecase

import com.example.lucas.spidey2.JUnitDaggerMockRule
import com.example.lucas.spidey2.data.repository.ComicRepository
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetComicTest {

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
}