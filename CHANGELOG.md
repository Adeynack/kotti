# CHANGELOG

## 0.1 - 2017-06-09

### core

* NEW: Extensions to all types:
  * [`orElse`](kotti-core/src/main/kotlin/com/github/adeynack/kotti/AnyExtensions.kt) to allow the "elvis operator"
    logic with a block of code in the "if it was null" part.
  * [`orElseWith`](kotti-core/src/main/kotlin/com/github/adeynack/kotti/AnyExtensions.kt) to allow the same as `orElse`,
    but returning a potentially nullable result (some kind of `flatMap` on an `Option[T]`, to compare with Scala).
* NEW: Extensions to collection types:
  * [`filterByOneOf`](kotti-core/src/main/kotlin/com/github/adeynack/kotti/collections/FilterByOneOf.kt) to quickly
    declare a composite filter where any of the provided predicate being positive will accept the current element. 
  * [`toMap`](kotti-core/src/main/kotlin/com/github/adeynack/kotti/collections/ToMap.kt) to quickly declare a `Map`,
    providing a key extractor or both the key and the value extractors.

### swing

* NEW: [`FlowPanel`](kotti-swing/src/main/kotlin/com/github/adeynack/kotti/swing/FlowPanel.kt), allowing quick creation of `JPanel` with
  `FlowLayout` and its content.
  
### tests

* NEW: [`JsonAssertion`](kotti-tests/src/main/kotlin/com/github/adeynack/kotti/tests/JsonAssertions.kt) to help
  comparing strings comparing JSON and have a comprehensive error message when they are different.
