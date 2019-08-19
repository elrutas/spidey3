package com.example.lucas.spidey3.di

import androidx.test.InstrumentationRegistry
import com.example.lucas.spidey3.SpideyApp
import com.example.lucas.spidey3.features.common.data.repository.di.RepositoryModule
import com.example.lucas.spidey3.internal.di.AppComponent
import com.example.lucas.spidey3.internal.di.AppModule
import com.example.lucas.spidey3.internal.di.Injector
import it.cosenonjaviste.daggermock.DaggerMock

fun daggerMockRule() = DaggerMock.rule<AppComponent>(AppModule(app), RepositoryModule()) {
    set { Injector.setComponent(it) }
}

val app: SpideyApp get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as SpideyApp