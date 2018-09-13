package com.example.lucas.spidey2

import com.example.lucas.spidey2.data.repository.di.RepositoryModule
import com.example.lucas.spidey2.internal.di.AppComponent
import com.example.lucas.spidey2.internal.di.AppModule
import com.nhaarman.mockito_kotlin.mock
import it.cosenonjaviste.daggermock.DaggerMock

fun JUnitDaggerMockRule() = DaggerMock.rule<AppComponent>(AppModule(mock()), RepositoryModule()) {
}