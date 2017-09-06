name := "akka-jwt"
version := "1.0.5"
organization := "de.innfactory"
description := "akka-http jwt auth directive"
scalaVersion := Version.Scala

scalafmtVersion := "1.2.0"

scalacOptions ++= Seq(
  "-deprecation",
  "-target:jvm-1.8",
  "-encoding",
  "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-unchecked",
  "-Xlint",
  "-Xlint:missing-interpolator",
  "-Yno-adapted-args",
  //"-Ywarn-unused",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Ywarn-unused-import"
)

libraryDependencies ++= {
  Seq(
    Library.akkaHttp,
    Library.nimbusJwt,
    TestLibrary.scalaTest,
    TestLibrary.scalaCheck
  )
}

scalafmtOnCompile := true
crossScalaVersions := Seq("2.11.11", scalaVersion.value)

// sbt-bintray options
licenses += ("Apache-2.0", url(
  "http://www.apache.org/licenses/LICENSE-2.0.txt"))
bintrayOrganization := Some("innfactory")
bintrayRepository := "sbt-plugins"
bintrayPackageLabels := Seq("JWT", "Scala", "akka-http", "cognito")
bintrayVcsUrl := Some("https://github.com/innFactory/akka-jwt")
publishMavenStyle := true
