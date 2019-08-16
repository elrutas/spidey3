package com.example.lucas.spidey3

import android.app.Application
import com.example.lucas.spidey3.internal.di.Injector

class SpideyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initialiseInjection()
    }

    private fun initialiseInjection() {
        Injector.initialize(this)
    }
}
