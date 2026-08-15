// Cross compilation matrix
addSbtPlugin("com.eed3si9n" % "sbt-projectmatrix" % "0.10.1")

// Versioning, signing and publishing to Maven Central.
// Bundles sbt-dynver (versioning from git) and sbt-pgp (signing).
addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.11.0")
