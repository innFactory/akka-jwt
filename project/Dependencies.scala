import sbt._

object Version {
  final val akkaHttp = "10.1.9"
  final val akkaStreams = "2.5.23"
  final val Scala = "2.13.0"
  final val nimbusJwt = "7.7"
}

object Library {
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % Version.akkaHttp
  val akkaStreams = "com.typesafe.akka" %% "akka-stream" % Version.akkaStreams
  val nimbusJwt  = "com.nimbusds"   % "nimbus-jose-jwt" % Version.nimbusJwt
}

object TestVersion {
  final val scalaTest = "3.0.8"
  final val scalaCheck = "1.14.0"
}

object TestLibrary {
  val akkaStreams = "com.typesafe.akka" %% "akka-stream-testkit"  % Version.akkaStreams % "test"
  val akkaHttp = "com.typesafe.akka" %% "akka-http-testkit"  % Version.akkaHttp % "test"
  val scalaTest = "org.scalatest" %% "scalatest" % TestVersion.scalaTest % "test"
  val scalaCheck = "org.scalacheck" %% "scalacheck"     % TestVersion.scalaCheck % "test"
}
