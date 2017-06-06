package com.github.adeynack.kotti.collections

import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.util.stream.IntStream
import kotlin.streams.asSequence

object FilterByOneOfSpec : Spek({

    describe("filterByOneOf") {

        fun isMultipleOf2(i: Int): Boolean = i % 2 == 0
        fun isMultipleOf3(i: Int): Boolean = i % 3 == 0
        fun isMultipleOf5(i: Int): Boolean = i % 5 == 0

        context("return the right result") {
            it("should on an Iterable") {
                (1..30)
                    .filterByOneOf(::isMultipleOf2, ::isMultipleOf3, ::isMultipleOf5)
                    .shouldEqual(listOf(
                        2, 3, 4, 5, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30
                    ))
            }
            it("should on a Sequence") {
                (1..30).asSequence()
                    .filterByOneOf(::isMultipleOf2, ::isMultipleOf3, ::isMultipleOf5)
                    .toList()
                    .shouldEqual(listOf(
                        2, 3, 4, 5, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30
                    ))
            }
            it("should on a Sequence created from a Stream") {
                IntStream.rangeClosed(1, 30)
                    .asSequence()
                    .filterByOneOf(::isMultipleOf2, ::isMultipleOf3, ::isMultipleOf5)
                    .toList()
                    .shouldEqual(listOf(
                        2, 3, 4, 5, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30
                    ))
            }
        }

        context("should use the filters appropriately") {
            context("on an Iterable") {
                var used2 = 0
                var used3 = 0
                var used5 = 0
                val result = (1..50)
                    .filterByOneOf(
                        { used2++; isMultipleOf2(it) },
                        { used3++; isMultipleOf3(it) },
                        { used5++; isMultipleOf5(it) }
                    )
                    .take(22)
                it("should return the right result") {
                    result shouldEqual listOf(
                        2, 3, 4, 5, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30
                    )
                }
                it("should have called the `isMultipleOf2` predicate only for every element of the initial iterable") { used2 shouldEqual 50 }
                it("should have called the `isMultipleOf3` predicate only just as it needs it (non-lazy)") { used3 shouldEqual 25 }
                it("should have called the `isMultipleOf5` predicate only just as it needs it (non-lazy)") { used5 shouldEqual 17 }
            }
            context("on a Sequence") {
                var used2 = 0
                var used3 = 0
                var used5 = 0
                val result = (1..50)
                    .asSequence()
                    .filterByOneOf(
                        { used2++; isMultipleOf2(it) },
                        { used3++; isMultipleOf3(it) },
                        { used5++; isMultipleOf5(it) }
                    )
                    .take(22)
                    .toList()
                it("should return the right result") {
                    result shouldEqual listOf(
                        2, 3, 4, 5, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30
                    )
                }
                it("should have called the `isMultipleOf2` predicate just as it needs it") { used2 shouldEqual 30 }
                it("should have called the `isMultipleOf3` predicate just as it needs it") { used3 shouldEqual 15 }
                it("should have called the `isMultipleOf5` predicate just as it needs it") { used5 shouldEqual 10 }
            }
            context("on a Sequence created from a Stream") {
                var used2 = 0
                var used3 = 0
                var used5 = 0
                val result =
                    IntStream.rangeClosed(1, 50)
                        .asSequence()
                        .filterByOneOf(
                            { used2++; isMultipleOf2(it) },
                            { used3++; isMultipleOf3(it) },
                            { used5++; isMultipleOf5(it) }
                        )
                        .take(22)
                        .toList()
                it("should return the right result") {
                    result shouldEqual listOf(
                        2, 3, 4, 5, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30
                    )
                }
                it("should have called the `isMultipleOf2` predicate just as it needs it") { used2 shouldEqual 30 }
                it("should have called the `isMultipleOf3` predicate just as it needs it") { used3 shouldEqual 15 }
                it("should have called the `isMultipleOf5` predicate just as it needs it") { used5 shouldEqual 10 }
            }
        }

        context("return an empty list when none of the filter is matched") {
            it("should on an Iterable") {
                listOf(1, 7, 11, 13, 17, 19, 23, 29)
                    .filterByOneOf(::isMultipleOf2, ::isMultipleOf3, ::isMultipleOf5)
                    .shouldEqual(emptyList<Int>())
            }
            it("should on a Sequence") {
                listOf(1, 7, 11, 13, 17, 19, 23, 29)
                    .asSequence()
                    .filterByOneOf(::isMultipleOf2, ::isMultipleOf3, ::isMultipleOf5)
                    .toList()
                    .shouldEqual(emptyList<Int>())
            }
            it("should on a Stream") {
                IntStream.of(1, 7, 11, 13, 17, 19, 23, 29)
                    .asSequence()
                    .filterByOneOf(::isMultipleOf2, ::isMultipleOf3, ::isMultipleOf5)
                    .toList()
                    .shouldEqual(emptyList<Int>())
            }
        }

    }

})
