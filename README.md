[![Test](https://github.com/cucumber/action-publish-sbt/actions/workflows/test.yml/badge.svg)](https://github.com/cucumber/action-publish-sbt/actions/workflows/test.yml)

# action-publish-sbt

Publishes a Scala module to [Maven Central](https://central.sonatype.com/) using [sbt-ci-release](https://github.com/sbt/sbt-ci-release).

Needs Java & SBT to be installed first, and the `sbt-ci-release` plugin
added to your project's `project/plugins.sbt`.

## Inputs

* `gpg-private-key`
* `gpg-passphrase`
* `nexus-username`
* `nexus-password`
* `working-directory` (default `.`)

## Example

```yaml
name: Publish

on:
  push:
    tags:
      - 'v[0-9]+.[0-9]+.[0-9]+'

jobs:
  publish-sbt:
    name: Publish SBT package to Maven Central
    runs-on: ubuntu-latest
    environment: Release
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v1
        with:
          java-version: '17'
      - uses: sbt/setup-sbt@v1
      - name: Publish
        uses: cucumber/action-publish-sbt@v1.0.0
        with:
          gpg-private-key: ${{ secrets.GPG_PRIVATE_KEY }}
          gpg-passphrase: ${{ secrets.GPG_PASSPHRASE }}
          nexus-username: ${{ secrets.SONATYPE_USERNAME }}
          nexus-password: ${{ secrets.SONATYPE_PASSWORD }}
          working-directory: "."
```
