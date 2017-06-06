package com.github.adeynack.kotti.collections

/**
 * Returns a [List] containing only elements matching one of the given [predicates].
 *
 *     (1..30).filterByOneOf(
 *         { it % 2 == 0 }, // is a multiple of 2 or
 *         { it % 3 == 0 }, // is a multiple of 3 or
 *         { it % 5 == 0 } //  is a multiple of 5
 *     )
 *     // yields: 2, 3, 4, 5, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30
 *
 */
inline fun <reified T> Iterable<T>.filterByOneOf(vararg predicates: (T) -> Boolean): List<T> =
    filter { element: T -> predicates.any { pred -> pred(element) } }

/**
 * Returns a [Sequence] containing only elements matching one of the given [predicates].
 *
 *     (1..30).asSequence.filterByOneOf(
 *         { it % 2 == 0 }, // is a multiple of 2 or
 *         { it % 3 == 0 }, // is a multiple of 3 or
 *         { it % 5 == 0 } //  is a multiple of 5
 *     )
 *     // yields: 2, 3, 4, 5, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30
 *
 */
inline fun <reified T> Sequence<T>.filterByOneOf(vararg predicates: (T) -> Boolean): Sequence<T> =
    filter { element: T -> predicates.any { pred -> pred(element) } }
