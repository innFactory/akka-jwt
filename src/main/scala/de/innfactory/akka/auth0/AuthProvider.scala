package de.innfactory.akka.auth0
import scalacache._
import guava._
import com.google.common.cache.CacheBuilder

class AuthProvider {
  val underlyingGuavaCache = CacheBuilder.newBuilder().maximumSize(10000L).build[String, Object]
  implicit val scalaCache = ScalaCache(GuavaCache(underlyingGuavaCache))

  val cache = typed[String, NoSerialization]

}
