package com.example.lucas.spidey2

import android.app.Application
import com.example.lucas.spidey2.internal.di.Injector

class SpideyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initialiseInjection()
    }

    private fun initialiseInjection() {
        Injector.initialize(this)
    }
}
