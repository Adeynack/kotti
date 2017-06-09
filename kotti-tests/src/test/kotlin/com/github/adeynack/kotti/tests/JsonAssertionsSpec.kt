package com.github.adeynack.kotti.tests

import com.github.adeynack.kotti.tests.JsonAssertions.shouldContainJson
import com.github.adeynack.kotti.tests.JsonAssertions.shouldContainJsonLenientArrays
import com.github.adeynack.kotti.tests.JsonAssertions.shouldEqualJson
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object JsonKluentSpec : Spek({

    fun expectJsonAssertionError(f: () -> Unit): JsonAssertionError {
        try {
            f()
            throw AssertionError("Expected `JsonAssertionError` was not thrown")
        } catch(e: JsonAssertionError) {
            return e
        }
    }

    infix fun JsonAssertionError.withMessage(expectedMessage: String) {
        this.message shouldEqual expectedMessage.trimMargin()
    }

    describe("Asserting with `shouldEqualJson`") {

        it("should be positive when JSON objects are the same") {
            val a = """{   "a": true,   "b": 42,   "c": null   }"""
            val b = """{   "a": true,   "b": 42,   "c": null   }"""
            a shouldEqualJson b
        }

        it("should be positive when JSON objects are the same, but properties are not in the same order") {
            val a = """{   "a": true,   "b": 42,   "c": null   }"""
            val b = """{   "a": true,   "c": null, "b": 42     }"""
            a shouldEqualJson b
        }

        it("should be negative when a same property has different value") {
            expectJsonAssertionError {
                val a = """{   "a": true,   "b": 42,   "c": null   }"""
                val b = """{   "a": true,   "b": 42,   "c": "asd"  }"""
                a shouldEqualJson b
            } withMessage """
                |JSON value
                |  {"a":true,"b":42,"c":null}
                |is not equal to
                |  {"a":true,"b":42,"c":"asd"}
                |because:
                |c
                |Expected: asd
                |     got: null
                |"""
        }

        it("should be negative when the tested value has more properties than the expected") {
            expectJsonAssertionError {
                val a = """{   "a": true,   "b": 42,   "c": null, "d": true   }"""
                val b = """{   "a": true,   "b": 42,   "c": null  }"""
                a shouldEqualJson b
            } withMessage """
                |JSON value
                |  {"a":true,"b":42,"c":null,"d":true}
                |is not equal to
                |  {"a":true,"b":42,"c":null}
                |because:
                |Unexpected: d
                |"""
        }

        it("should be negative when the tested value a different array order") {
            expectJsonAssertionError {
                val a = """{   "a": true,   "b": [1,2,3],   "c": null  }"""
                val b = """{   "a": true,   "b": [1,3,2],   "c": null  }"""
                a shouldEqualJson b
            } withMessage """
                |JSON value
                |  {"a":true,"b":[1,2,3],"c":null}
                |is not equal to
                |  {"a":true,"b":[1,3,2],"c":null}
                |because:
                |b[1]
                |Expected: 3
                |     got: 2
                | ; b[2]
                |Expected: 2
                |     got: 3
                |"""
        }

        it("should be negative when the tested value a different array values") {
            expectJsonAssertionError {
                val a = """{   "a": true,   "b": [1,2,3],   "c": null  }"""
                val b = """{   "a": true,   "b": [1,2,4],   "c": null  }"""
                a shouldEqualJson b
            } withMessage """
                |JSON value
                |  {"a":true,"b":[1,2,3],"c":null}
                |is not equal to
                |  {"a":true,"b":[1,2,4],"c":null}
                |because:
                |b[2]
                |Expected: 4
                |     got: 3
                |"""
        }

    }

    describe("Asserting with `shouldContainJson`") {

        it("should be positive when JSON objects are the same") {
            val a = """{   "a": true,   "b": 42,   "c": null   }"""
            val b = """{   "a": true,   "b": 42,   "c": null   }"""
            a shouldContainJson b
        }

        it("should be positive when JSON objects are the same, but properties are not in the same order") {
            val a = """{   "a": true,   "b": 42,   "c": null   }"""
            val b = """{   "a": true,   "c": null, "b": 42     }"""
            a shouldContainJson b
        }

        it("should be negative when a same property has different value") {
            expectJsonAssertionError {
                val a = """{   "a": true,   "b": 42,   "c": null   }"""
                val b = """{   "a": true,   "b": 42,   "c": "asd"  }"""
                a shouldContainJson b
            } withMessage """
                |JSON value
                |  {"a":true,"b":42,"c":null}
                |does not contain
                |  {"a":true,"b":42,"c":"asd"}
                |because:
                |c
                |Expected: asd
                |     got: null
                |"""
        }

        it("should be positive when the tested value has more properties than the expected") {
            val a = """{   "a": true,   "b": 42,   "c": null, "d": true   }"""
            val b = """{   "a": true,   "b": 42,   "c": null  }"""
            a shouldContainJson b
        }

        it("should be negative when the tested value a different array order") {
            expectJsonAssertionError {
                val a = """{   "a": true,   "b": [1,2,3],   "c": null  }"""
                val b = """{   "a": true,   "b": [1,3,2],   "c": null  }"""
                a shouldContainJson b
            } withMessage """
                |JSON value
                |  {"a":true,"b":[1,2,3],"c":null}
                |does not contain
                |  {"a":true,"b":[1,3,2],"c":null}
                |because:
                |b[1]
                |Expected: 3
                |     got: 2
                | ; b[2]
                |Expected: 2
                |     got: 3
                |"""
        }

        it("should be negative when the tested value a different array values") {
            expectJsonAssertionError {
                val a = """{   "a": true,   "b": [1,2,3],   "c": null  }"""
                val b = """{   "a": true,   "b": [1,2,4],   "c": null  }"""
                a shouldContainJson b
            } withMessage """
                |JSON value
                |  {"a":true,"b":[1,2,3],"c":null}
                |does not contain
                |  {"a":true,"b":[1,2,4],"c":null}
                |because:
                |b[2]
                |Expected: 4
                |     got: 3
                |"""
        }

    }

    describe("Asserting with `shouldContainJsonLenientArrays`") {

        it("should be positive when JSON objects are the same") {
            val a = """{   "a": true,   "b": 42,   "c": null   }"""
            val b = """{   "a": true,   "b": 42,   "c": null   }"""
            a shouldContainJsonLenientArrays b
        }

        it("should be positive when JSON objects are the same, but properties are not in the same order") {
            val a = """{   "a": true,   "b": 42,   "c": null   }"""
            val b = """{   "a": true,   "c": null, "b": 42     }"""
            a shouldContainJsonLenientArrays b
        }

        it("should be negative when a same property has different value") {
            expectJsonAssertionError {
                val a = """{   "a": true,   "b": 42,   "c": null   }"""
                val b = """{   "a": true,   "b": 42,   "c": "asd"  }"""
                a shouldContainJsonLenientArrays b
            } withMessage """
                |JSON value
                |  {"a":true,"b":42,"c":null}
                |does not contain
                |  {"a":true,"b":42,"c":"asd"}
                |because:
                |c
                |Expected: asd
                |     got: null
                |"""
        }

        it("should be positive when the tested value has more properties than the expected") {
            val a = """{   "a": true,   "b": 42,   "c": null, "d": true   }"""
            val b = """{   "a": true,   "b": 42,   "c": null  }"""
            a shouldContainJsonLenientArrays b
        }

        it("should be positive when the tested value a different array order") {
            val a = """{   "a": true,   "b": [1,2,3],   "c": null  }"""
            val b = """{   "a": true,   "b": [1,3,2],   "c": null  }"""
            a shouldContainJsonLenientArrays b
        }

        it("should be negative when the tested value a different array values") {
            expectJsonAssertionError {
                val a = """{   "a": true,   "b": [1,2,3],   "c": null  }"""
                val b = """{   "a": true,   "b": [1,2,4],   "c": null  }"""
                a shouldContainJsonLenientArrays b
            } withMessage """
                |JSON value
                |  {"a":true,"b":[1,2,3],"c":null}
                |does not contain
                |  {"a":true,"b":[1,2,4],"c":null}
                |because:
                |b[]
                |Expected: 4
                |     but none found
                | ; b[]
                |Unexpected: 3
                |"""
        }

    }

})
