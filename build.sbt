name := "akka-jwt"
version := "1.0.0"
scalaVersion := Version.Scala

scalafmtOnCompile in ThisBuild := true
crossScalaVersions in ThisBuild := Seq("2.11.8", "2.11.11", scalaVersion.value)
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
  "-Ywarn-unused",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Ywarn-unused-import"
)

libraryDependencies ++= {
  Seq(
    Library.akkaActor,
    Library.akkaHttp,
    Library.akkaStream,
    Library.log4jCore,
    Library.slf4jLog4jBridge,
    Library.akkaLog4j,
    Library.nimbusJwt,
    TestLibrary.akkaTestkit,
    TestLibrary.akkaHttpTestkit,
    TestLibrary.scalaTest,
    TestLibrary.scalaCheck
  )
}

// sbt-bintray options
licenses += ("Apache-2.0", url(
  "http://www.apache.org/licenses/LICENSE-2.0.txt"))
bintrayOrganization := Some("innFactory")
bintrayPackageLabels := Seq("JWT", "Scala")
