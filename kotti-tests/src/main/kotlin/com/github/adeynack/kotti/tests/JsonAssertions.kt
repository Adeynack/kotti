package com.github.adeynack.kotti.tests

import org.skyscreamer.jsonassert.JSONCompare
import org.skyscreamer.jsonassert.JSONCompareMode
import org.skyscreamer.jsonassert.JSONParser.parseJSON

object JsonAssertions {

    /**
     * Compares a [String] containing JSON to another one, expecting a strict equality.
     * - not extensible (the [actual] value must not have more fields than the expected value)
     * - strict array ordering
     */
    infix fun String?.shouldEqualJson(actual: String?) =
        equalJson(this, actual, JSONCompareMode.STRICT, "is not equal to")

    /**
     * Compares a [String] containing JSON to another one, expecting at least a certain set of properties.
     * - extensible (the [actual] value can have more fields than the expected value)
     * - strict array ordering
     */
    infix fun String?.shouldContainJson(actual: String?) =
        equalJson(this, actual, JSONCompareMode.STRICT_ORDER, "does not contain")

    /**
     * Compares a [String] containing JSON to another one, expecting at least a certain set of properties and
     * being lenient on order of the elements inside of arrays.
     * - extensible (the [actual] value can have more fields than the expected value)
     * - lenient array ordering (items have to all be there, but the order does not count)
     */
    infix fun String?.shouldContainJsonLenientArrays(actual: String?) =
        equalJson(this, actual, JSONCompareMode.LENIENT, "does not contain")

    private fun equalJson(actual: String?, expected: String?, mode: JSONCompareMode, verb: String) {
        val result = JSONCompare.compareJSON(expected, actual, mode)
        if (!result.passed()) {
            val msgActual = try {
                parseJSON(actual).toString()
            } catch(e: Exception) {
                actual?.trim()
            }
            val msgExpected = try {
                parseJSON(expected).toString()
            } catch(e: Exception) {
                expected?.trim()
            }
            val msgMessage = result.message.trimStart { it == '\n' }
            throw JsonAssertionError("JSON value\n  $msgActual\n$verb\n  $msgExpected\nbecause:\n$msgMessage")
        }
    }

}

class JsonAssertionError(message: String) : AssertionError(message)
