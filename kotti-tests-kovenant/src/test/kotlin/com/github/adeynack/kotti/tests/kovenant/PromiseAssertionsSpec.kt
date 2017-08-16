package com.github.adeynack.kotti.tests.kovenant

import com.github.adeynack.kotti.tests.kovenant.PromiseAssertions.shouldFailWith
import com.github.adeynack.kotti.tests.kovenant.PromiseAssertions.shouldSucceedWith
import nl.komponents.kovenant.Promise
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.withMessage
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.ComparisonFailure

object PromiseAssertionsSpec : Spek({

    class TestShouldFailError(message: String? = null) : Error(message)

    describe("shouldSucceedWith") {

        given("a successful promise") {
            val p = Promise.ofSuccess<Int, Exception>(42)
            it("should succeed when the promise contains the expected value") {
                p shouldSucceedWith 42
            }
            it("should fail when the promise does not contain the expected value") {
                try {
                    p shouldSucceedWith 101
                    throw TestShouldFailError()
                } catch (e: ComparisonFailure) {
                    e.message shouldEqual "Expected the promise to succeed with 101. expected:<a success of [101]> but was:<a success of [42]>"
                    e.actual shouldEqual "a success of 42"
                    e.expected shouldEqual "a success of 101"
                }
            }
        }

        given("a failed promise") {
            val p = Promise.ofFail<Int, Exception>(ArithmeticException("Only Chuck Norris can divide by zero."))
            it("should fail") {
                try {
                    p shouldSucceedWith 42
                    throw TestShouldFailError()
                } catch(e: ComparisonFailure) {
                    e.message shouldEqual "Expected the promise to succeed with 42. expected:<a [success of 42]> but was:<a [failure of java.lang.ArithmeticException]>"
                    e.expected shouldEqual "a success of 42"
                    e.actual shouldEqual "a failure of java.lang.ArithmeticException"
                }
            }
        }

    }

    describe("shouldFailWith") {

        given("a successful promise") {
            val p = Promise.ofSuccess<Int, Exception>(42)
            it("should fail") {
                try {
                    p shouldFailWith Exception::class
                    throw TestShouldFailError()
                } catch (e: ComparisonFailure) {
                    e.message shouldEqual "Expected the promise to fail. expected:<a [failure of java.lang.Exception]> but was:<a [success of 42]>"
                    e.expected shouldEqual "a failure of java.lang.Exception"
                    e.actual shouldEqual "a success of 42"
                }
            }
        }
        given("a failed promise") {
            val p = Promise.ofFail<Int, Exception>(ArithmeticException("Only Chuck Norris can divide by zero."))
            it("should succeed when the promise failed with the expected type") {
                val result = p shouldFailWith ArithmeticException::class
                result.exception shouldBeInstanceOf ArithmeticException::class
                result withMessage "Only Chuck Norris can divide by zero."
                result.exceptionCause.shouldBeNull()
            }
            it("should fail when the promise failed with the expected type, but not the expected message") {
                try {
                    p shouldFailWith ArithmeticException::class withMessage "Only God can divide by zero."
                    throw TestShouldFailError()
                } catch (e: ComparisonFailure) {
                    e.actual shouldEqual "Only Chuck Norris can divide by zero."
                    e.expected shouldEqual "Only God can divide by zero."
                }
            }
            it("should fail when the promise failed for another reason than expected") {
                try {
                    p shouldFailWith NullPointerException::class
                    throw TestShouldFailError()
                } catch (e: ComparisonFailure) {
                    e.message shouldEqual "Expected promise to fail with a java.lang.NullPointerException. expected:<...ailure of java.lang.[NullPointer]Exception> but was:<...ailure of java.lang.[Arithmetic]Exception>"
                    e.actual shouldEqual "a failure of java.lang.ArithmeticException"
                    e.expected shouldEqual "a failure of java.lang.NullPointerException"
                }
            }
        }

    }

})
