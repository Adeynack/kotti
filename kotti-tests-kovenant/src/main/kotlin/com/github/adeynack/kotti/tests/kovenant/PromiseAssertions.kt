package com.github.adeynack.kotti.tests.kovenant

import com.github.adeynack.kotti.tests.AssertionUtilities.fail
import com.github.adeynack.kotti.tests.AssertionUtilities.isA
import nl.komponents.kovenant.Promise
import org.amshove.kluent.ExceptionResult
import kotlin.reflect.KClass

object PromiseAssertions {

    infix fun <V, E : Exception> Promise<V, E>.shouldFailWith(expectedFailureType: KClass<out E>): ExceptionResult<E> {
        try {
            val result = this.get()
            fail(
                "Expected the promise to fail.",
                "a failure of ${expectedFailureType.javaObjectType.name}",
                "a success of $result")
        } catch (e: Exception) {
            @Suppress("UNCHECKED_CAST")
            if (e.isA(expectedFailureType)) return ExceptionResult(e as E)
            else fail(
                "Expected promise to fail with a ${expectedFailureType.javaObjectType.name}.",
                "a failure of ${expectedFailureType.javaObjectType.name}",
                "a failure of ${e.javaClass.name}")
        }
    }

    infix fun <V> Promise<V, Throwable>.shouldSucceedWith(expectedValue: V) {
        try {
            val result = this.get()
            if (result != expectedValue) fail(
                "Expected the promise to succeed with $expectedValue.",
                "a success of $expectedValue",
                "a success of $result")
        } catch (e: Exception) {
            fail(
                "Expected the promise to succeed with $expectedValue.",
                "a success of $expectedValue",
                "a failure of ${e.javaClass.name}")
        }

    }

}
