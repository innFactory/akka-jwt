name := "akka-auth0"

version := "1.0.0"
scalaVersion := Version.Scala

libraryDependencies ++= {
  Seq(
    Library.akkaActor,
    Library.akkaHttp,
    Library.akkaStream,
    Library.log4jCore,
    Library.slf4jLog4jBridge,
    Library.akkaLog4j,
    TestLibrary.akkaTestkit,
    TestLibrary.akkaHttpTestkit,
    TestLibrary.scalaTest
  )
}