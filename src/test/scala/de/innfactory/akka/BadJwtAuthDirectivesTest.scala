package de.innfactory.akka

import akka.http.scaladsl.model.headers.HttpChallenge
import akka.http.scaladsl.server.AuthenticationFailedRejection
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import de.innfactory.akka.jwt.AutoInvalidator
import org.scalatest.{Matchers, WordSpec}

class BadJwtAuthDirectivesTest
    extends WordSpec
    with Matchers
    with JwtAuthDirectives
    with ScalatestRouteTest {

  val jwtValidator = new AutoInvalidator
  val authService = new AuthService(jwtValidator)

  "JwtAuthDirective" should {
    "reject the request" in {
      //like the runtime, instantiate route once
      val authRoute = get {
        authenticate { token =>
          complete(token._1.content)
        }
      }

      Get() ~> addHeader("Authorization", "any") ~> authRoute ~> check {
        rejection shouldEqual AuthenticationFailedRejection(
          AuthenticationFailedRejection.CredentialsRejected,
          HttpChallenge("JWT", None))
      }
    }
  }

}
