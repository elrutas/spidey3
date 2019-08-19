package com.example.lucas.spidey3.features.common.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.lucas.spidey3.internal.di.Injector
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.getAppComponent().inject(this)
    }

    fun <T : ViewModel> viewModel(modelClass: Class<T> ): T {
        return ViewModelProviders.of(this, viewModelFactory).get(modelClass)
    }
}
