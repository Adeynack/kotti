# kotti, aka _Kotlin: The Missing Bits_

[![Build Status](https://travis-ci.org/Adeynack/kotti.svg?branch=master)](https://travis-ci.org/Adeynack/kotti)

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

## Local deployment

To deploy to the local Maven repository (on the development machine) to allow other project to use the library:

```bash
./gradlew publishToMavenLocal
```

That also allows quick development of production software while adding and debugging features to `Kotti` locally.

Project using this library from local publishing need to include this in their Gradle file:

```groovy
repositories {
    mavenLocal()
}
dependencies {
    compile "com.github.adeynack:kotti-core:0.1-SNAPSHOT"
}
```

## Manual publishing to BinTray

1. Update the version number by changing variable `PUBLISH_VERSION` in [kotti.gradle](kotti.gradle).
    ```groovy
    def PUBLISH_VERSION = 0.2
    ```
1. Manually execute the _Gradle_ task for publishing to _BinTray_.
    ```bash
    ./gradlew bintrayUpload
    ```
1. Log to _BinTray_ and go to [the _Kotti_ repository](https://bintray.com/adeynack/kotti/kotti)
1. Go to the _Versions_ section and click on the version number you just published.
1. Make sure all expected files in all components are present.
   Example with `kotti-core`:
    - `kotti-core-0.2-javadoc.jar`
    - `kotti-core-0.2-sources.jar`
    - `kotti-core-0.2.jar`
    - `kotti-core-0.2.pom`
1. In the [the _Kotti_ repository](https://bintray.com/adeynack/kotti/kotti), in the _Notice_ (look for a gray bell),
   click _Publish_
