package com.github.adeynack.kotti.tests

import org.amshove.kluent.AnyException
import org.junit.ComparisonFailure
import kotlin.reflect.KClass

/**
 * Some tools to help developing assertions.
 *
 * Part of this was taken directly or inspired by the implementation of [Kluent](https://github.com/MarkusAmshove/Kluent).
 *
 */
object AssertionUtilities {

    /**
     * Checks if the [Exception] subject is of the [expected] type.
     */
    fun Exception.isA(expected: KClass<out Exception>) = expected.isAnyException() || expected.java.isAssignableFrom(this.javaClass)

    /**
     * Checks if the type ([KClass]) of the subject is of [Exception] subtype [T].
     */
    fun <T : Exception> KClass<T>.isAnyException(): Boolean = this.javaObjectType == AnyException.javaObjectType

    /**
     * Throws a [ComparisonFailure] out of the given [message], [expected] result and [actual] value.
     */
    fun fail(message: String, expected: String, actual: String): Nothing = throw ComparisonFailure(message, expected, actual)

}
