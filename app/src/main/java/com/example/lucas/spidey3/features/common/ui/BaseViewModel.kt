package com.example.lucas.spidey3.features.common.ui

import androidx.lifecycle.ViewModel
import com.example.lucas.spidey3.features.common.domain.usecase.Usecase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModel : ViewModel() {

    var disposables: CompositeDisposable = CompositeDisposable()

    fun <ResultType> execute(usecase: Usecase<ResultType>,
                             onSuccess: (ResultType) -> Unit,
                             onError: (Throwable) -> Unit) {
        disposables.add(
                usecase.getSubscribable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onSuccess, onError)
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}