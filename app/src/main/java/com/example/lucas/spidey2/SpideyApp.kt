package com.example.lucas.spidey2

import android.app.Application

import com.example.lucas.spidey2.di.AppComponent
import com.example.lucas.spidey2.di.AppModule
import com.example.lucas.spidey2.di.DaggerAppComponent

class SpideyApp : Application() {

    val component = createComponent()

    protected fun createComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}
