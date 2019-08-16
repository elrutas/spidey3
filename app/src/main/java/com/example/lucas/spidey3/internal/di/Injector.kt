package com.example.lucas.spidey3.internal.di

import android.support.annotation.VisibleForTesting

import com.example.lucas.spidey3.SpideyApp

enum class Injector() {
    INSTANCE;

    lateinit var applicationComponent: AppComponent

    companion object {

        internal fun initialize(spideyApp: SpideyApp) {
            val applicationComponent = DaggerAppComponent.builder()
                    .appModule(AppModule(spideyApp))
                    .build()
            INSTANCE.applicationComponent = applicationComponent
        }

        fun getAppComponent(): AppComponent {
            return INSTANCE.applicationComponent
        }

        @VisibleForTesting
        fun setComponent(appComponent: AppComponent) {
            INSTANCE.applicationComponent = appComponent
        }
    }
}
