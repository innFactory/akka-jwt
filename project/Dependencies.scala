import sbt._

object Version {
  final val akkaHttp = "10.0.10"
  final val Scala = "2.12.3"
  final val nimbusJwt = "5.1"
}

object Library {
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % Version.akkaHttp
  val nimbusJwt  = "com.nimbusds"   % "nimbus-jose-jwt" % Version.nimbusJwt
}

object TestVersion {
  final val scalaTest = "3.0.1"
  final val scalaCheck = "1.13.5"
}

object TestLibrary {
  val scalaTest = "org.scalatest" %% "scalatest" % TestVersion.scalaTest % "test"
  val scalaCheck = "org.scalacheck" %% "scalacheck"     % TestVersion.scalaCheck % "test"
}
