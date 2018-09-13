package com.example.lucas.spidey2.helpers

import com.example.lucas.spidey2.domain.usecase.Usecase
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import java.util.concurrent.TimeUnit

abstract class BaseUsecaseTest<T> {

    lateinit var testSubscriber: TestObserver<T>
    lateinit var testScheduler: TestScheduler

    @Before
    @Throws(Exception::class)
    fun setUp() {
        this.testSubscriber = TestObserver()
        this.testScheduler = TestScheduler()
    }

    protected fun advanceTime() {
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
    }

    protected fun assertCompleteWithValue(o: T) {
        testSubscriber.assertValue(o)
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()
    }

    protected fun assertErrorWithValue(e: Throwable) {
        testSubscriber.assertValueCount(0)
        testSubscriber.assertError(e)
        testSubscriber.assertNotComplete()
    }

    protected fun assertErrorWithValue(e: Class<out Throwable>) {
        testSubscriber.assertValueCount(0)
        testSubscriber.assertError(e)
        testSubscriber.assertNotComplete()
    }

    protected fun assertCompleteEmpty() {
        testSubscriber.assertValueCount(0)
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()
    }

    @Throws(Exception::class)
    protected fun givenUsecase(useCase: Usecase<T>) {
        useCase.getSubscribable()
                .subscribeOn(testScheduler)
                .observeOn(testScheduler)
                .subscribe(testSubscriber)
    }

}
