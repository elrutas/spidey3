package com.example.lucas.spidey3.domain.usecase

import io.reactivex.Single

interface Usecase<ReturnType> {

    fun getSubscribable(): Single<ReturnType>
}