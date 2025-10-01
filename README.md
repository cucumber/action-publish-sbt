[![Test](https://github.com/cucumber/action-publish-sbt/actions/workflows/test.yml/badge.svg)](https://github.com/cucumber/action-publish-sbt/actions/workflows/test.yml)

# action-publish-sbt

Publishes a Scala module to [Maven Central](https://search.maven.org/)

Needs Java & SBT to be installed first.

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
    branches:
      - "release/*"

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
