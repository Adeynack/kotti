# kotti, aka _Kotlin: The Missing Bits_

[![Build Status](https://travis-ci.org/Adeynack/kotti.svg?branch=master)](https://travis-ci.org/Adeynack/kotti)
[![Download](https://api.bintray.com/packages/adeynack/kotti/kotti/images/download.svg)](https://bintray.com/adeynack/kotti/kotti/_latestVersion)

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

Deploying to the local Maven repository (on the development machine) allows other projects to use the library in its
development state. It is suggested, to avoid confusion, to append `-SNAPSHOT` to `PUBLISH_VERSION` in the
root [build.gradle](build.gradle) file.

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

## Public version publication checklist

**DISCLAIMER**: This is a first iteration of the process. Not the best way. Not bulletproof. But it is a start. This will
evolve over time.

1. Create an issue in [GitHub](https://github.com/Adeynack/kotti/issues/new) with the name `Publish version 0.1` (where
   `0.1` is the version you are about to publish).
1. Check out a clean version of `master`
    ```bash
    git checkout master
    git pull
    ```
1. Create a branch with your issue number (ie: `19-publish-0.1` is the issue number is `19`)
   ```bash
   git checkout -b 19-publish-version-0.1
   ```
1. Check what the current version being worked on is in the [Gradle project file](build.gradle). Per instance:
   ```groovy
   def PUBLISH_VERSION = 0.1
   ```
   That means the current version that is about to be published is `0.1`. Make sure no `-SNAPSHOT` suffix is present.
1. Add [release notes](CHANGELOG.md) for the current version.
  * Add a new version at the BEGINNING of the list (so they are sorted by reverse date order)
  * A new version starts with the version number and the date. ie: `## 0.1 - 2017-06-09`
  * Group changes by modules (`core`, `swing`, `tests`, etc.)
  * Describe briefly what was added, modified or deleted.
1. Commit in that branch
   ```bash
   git add CHANGELOG.md && git commit -m "#19 Release notes for version 0.1"
   ```
1. Tag the current commit as the end of the current version.
   ```bash
   git tag 0.1 && git push --tags
   ```
1. Publish to _BinTray_ using the instructions in the [Manual publishing to BinTray](#manual-publishing-to-bintray)
   section of this document.
1. Bump up the version number (usually, the 2nd digit) in the [Gradle file](build.gradle) for having in the `master`
   branch afterwards the next version to be published (useful when developing and publishing locally, to keep
   dependencies in client projects working).
1. Commit in that branch
   ```bash
   git add build.gradle && git commit -m "#19 Published version 0.1"
   ```
1. Create a pull request for this branch. Be careful in doing that during a "relative code-freeze" (make sure noone
   merges between the moment you tag and the moment you merge this pull request).
   
The end result is:
* there is a GIT tag pointing at the exact status when published, with the right version number and the release notes.
* the binaries, docs and sources are published to _BinTray_.
* the master branch now points at the next version to be published.

## Manual publishing to BinTray

1. Make sure you are exporting the following environment variables.
    1. `BINTRAY_USER` containing your user name on _BinTray_
    1. `BINTRAY_API_KEY` containing your API key for _BinTray_. Go get your API key:
        1. Go to [_Edit your profile_](https://bintray.com/profile/edit) on BinTray
        1. Click _API Key_
        1. Click _Copy to clipboard_ (an icon with 2 overlapping squares)
1. Make sure the version number (variable `PUBLISH_VERSION` in [kotti.gradle](kotti.gradle)) is set to the exact version
   you want to publish.
    ```groovy
    def PUBLISH_VERSION = 0.1
    ```
1. Manually execute the _Gradle_ task for publishing to _BinTray_.
    ```bash
    ./gradlew clean bintrayUpload
    ```
1. Log to _BinTray_ and go to [the _Kotti_ repository](https://bintray.com/adeynack/kotti/kotti)
1. Go to the _Versions_ section and click on the version number you just published.
1. Make sure all expected files in all components are present.
   Example with `kotti-core`:
    1. `kotti-core-0.2-javadoc.jar`
    1. `kotti-core-0.2-sources.jar`
    1. `kotti-core-0.2.jar`
    1. `kotti-core-0.2.pom`
1. In the [the _Kotti_ repository](https://bintray.com/adeynack/kotti/kotti), in the _Notice_ (look for a gray bell),
   click _Publish_
