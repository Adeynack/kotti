package com.github.adeynack.kotti.collections

import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object ToMapSpec : Spek({

    describe("toMap") {

        data class Model(val k: String, val v: Int)

        val model1 = Model("one", 1)
        val model2 = Model("two", 2)
        val models = listOf(model1, model2)
        val modelSeq = sequenceOf(model1, model2)

        context("with key-extractor") {
            context("on an Iterable") {
                it("returns a Map with the proper extracted keys") {
                    models.toMap { it.k } shouldEqual mapOf(
                        "one" to model1,
                        "two" to model2
                    )
                }
            }
            context("on a Sequence") {
                it("returns a Map with the proper extracted keys") {
                    modelSeq.asIterable().toMap { it.k } shouldEqual mapOf(
                        "one" to model1,
                        "two" to model2
                    )
                }
            }
        }

        context("with key and value extractors") {
            context("on an Iterable") {
                it("returns a Map with the proper extracted keys and values") {
                    models.toMap({ it.k }, { it.v }) shouldEqual mapOf(
                        "one" to 1,
                        "two" to 2
                    )
                }
            }
            context("on a Sequence") {
                it("returns a Map with the proper extracted keys and values") {
                    modelSeq.asIterable().toMap({ it.k }, { it.v }) shouldEqual mapOf(
                        "one" to 1,
                        "two" to 2
                    )
                }
            }
        }
    }

})
