import ReleaseTransformations._
import xerial.sbt.Sonatype.sonatypeSettings
import xerial.sbt.Sonatype.sonatypeCentralHost

// Metadata

ThisBuild / organization := "io.cucumber"
ThisBuild / organizationName := "Cucumber"
ThisBuild / organizationHomepage := Some(url("https://github.com/cucumber"))
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/cucumber/action-publish-sbt"),
    "scm:git@github.com:cucumber/action-publish-sbt.git"
  )
)
ThisBuild / developers := List(
  Developer(
    "cucumber",
    "Cucumber Developers",
    "devs@cucumber.io",
    url("https://github.com/cucumber")
  )
)
ThisBuild / licenses := Seq(
  "MIT License" -> url("http://www.opensource.org/licenses/mit-license")
)
ThisBuild / description := "Dummy project to test the action-publish-sbt GitHub Action"
ThisBuild / homepage := Some(
  url("https://github.com/cucumber/action-publish-sbt")
)

// Scala versions

val scala212 = "2.12.20"
val scala213 = "2.13.16"
val scala3 = "3.3.5"

scalaVersion := scala213

// Projects and settings

lazy val root = (projectMatrix in file("."))
  .settings(
    name := "test-release-automation-sbt"
  )
  .jvmPlatform(scalaVersions = Seq(scala3, scala213, scala212))

// Release & Publish

Global / publishMavenStyle := true
Global / publishTo := sonatypePublishToBundle.value
// https://github.com/xerial/sbt-sonatype?tab=readme-ov-file#sonatype-central-host
ThisBuild / sonatypeCredentialHost := sonatypeCentralHost

// https://github.com/xerial/sbt-sonatype#using-with-sbt-release-plugin
releaseCrossBuild := true
releaseVersionBump := sbtrelease.Version.Bump.NextStable // Required since 1.4.0
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  // commitReleaseVersion,
  // tagRelease,
  releaseStepCommandAndRemaining("publishSigned"),
  releaseStepCommand("sonatypeBundleRelease"),
  setNextVersion,
  // commitNextVersion,
  // pushChanges
)
