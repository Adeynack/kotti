# kotti, aka _Kotlin: The Missing Bits_

# The Project

I thought of naming it `Kotlinist`. But then, living in Berlin and realizing how much the short name of the infamous
_Kottbusser Tor_ can sound like a play on word with _Kotlin_, I decided to call the project: `Kotti`.

This is basically a library I am creating to assemble all the little utility classes, extension methods and DSLs I do
find useful when developing in _Kotlin_. Instead of copying parts of code from one project to the other, I decided to
group them here and have my applicative project depend on it.

In time, I am open to other contributors and maintainers, especially to have discussion about what to include and what
not to. It's easier to achieve a better designed library when not only one set of opinion is involved.

-- _Adeynack_

# Components

### [kotti-core](kotti-core)

General extensions to the _Kotlin_ base library.

### [kotti-tests](kotti-tests)

Tools and assertion utilities useful for testing.

### [kotti-swing](kotti-swing)

Extensions and DSL for developing _Swing_ GUI applications.

# Dev Ops

## Tests

To execute the tests from all components:

```bash
./gradlew test
```
