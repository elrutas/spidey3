package com.example.lucas.spidey2.domain.usecase

import io.reactivex.Observable

interface Usecase<ReturnType> {

    fun getSubscribable(): Observable<ReturnType>
}