package com.example.lucas.spidey2.di

import android.support.test.InstrumentationRegistry
import com.example.lucas.spidey2.SpideyApp
import com.example.lucas.spidey2.data.repository.di.RepositoryModule
import com.example.lucas.spidey2.internal.di.AppComponent
import com.example.lucas.spidey2.internal.di.AppModule
import com.example.lucas.spidey2.internal.di.Injector
import it.cosenonjaviste.daggermock.DaggerMock

fun daggerMockRule() = DaggerMock.rule<AppComponent>(AppModule(app), RepositoryModule()) {
    set { Injector.setComponent(it) }
}

val app: SpideyApp get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as SpideyApp