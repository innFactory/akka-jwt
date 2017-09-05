import sbt._

object Version {
  final val akka = "2.5.3"
  final val akkaHttp = "10.0.8"
  final val akkaHttpSprayJson =  "10.0.9"
  final val Scala = "2.11.8"
  final val AkkaLog4j = "1.4.0"
  final val Log4j = "2.8.2"
  final val nimbusJwt = "5.1"
}

object Library {

  val akkaHttp = "com.typesafe.akka" %% "akka-http" % Version.akkaHttp
  val akkaActor = "com.typesafe.akka" %% "akka-actor" % Version.akka
  val akkaStream = "com.typesafe.akka" %% "akka-stream" % Version.akka

  val log4jCore = "org.apache.logging.log4j" % "log4j-core" % Version.Log4j
  val slf4jLog4jBridge = "org.apache.logging.log4j" % "log4j-slf4j-impl" % Version.Log4j
  val akkaLog4j = "de.heikoseeberger" %% "akka-log4j" % Version.AkkaLog4j

  val nimbusJwt  = "com.nimbusds"   % "nimbus-jose-jwt" % Version.nimbusJwt
}

object TestVersion {
  final val akkaTestkit = "2.5.3"
  final val akkaHttpTestkit =  "10.0.9"
  final val scalaTest = "3.0.1"
  final val scalaCheck = "1.13.5"
}

object TestLibrary {
  val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % TestVersion.akkaTestkit % "test"
  val akkaHttpTestkit = "com.typesafe.akka" %% "akka-http-testkit" % TestVersion.akkaHttpTestkit % "test"
  val scalaTest = "org.scalatest" %% "scalatest" % TestVersion.scalaTest % "test"
  val scalaCheck = "org.scalacheck" %% "scalacheck"     % TestVersion.scalaCheck % "test"
}
