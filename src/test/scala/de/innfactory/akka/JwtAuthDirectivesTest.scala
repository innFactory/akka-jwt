package de.innfactory.akka

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import de.innfactory.akka.jwt.AutoValidator
import org.scalatest.{Matchers, WordSpec}

class JwtAuthDirectivesTest extends WordSpec with Matchers with JwtAuthDirectives with ScalatestRouteTest {

  val jwtValidator = new AutoValidator
  val authService = new AuthService(jwtValidator)

  "JwtAuthDirective" should {
    "be response the token" in {
      //like the runtime, instantiate route once
      val authRoute =  get { authenticate { token => complete(token._1.content) } }

      Get() ~> addHeader("Authorization", "any") ~> authRoute ~> check  {
        responseAs[String] shouldBe("any")
      }
    }
    "extract token from 'Bearer' type authorization header" in {
      //like the runtime, instantiate route once
      val authRoute =  get { authenticate { token => complete(token._1.content) } }

      Get() ~> addHeader("Authorization", "Bearer mytoken") ~> authRoute ~> check  {
        responseAs[String] shouldBe("mytoken")
      }
    }
  }

}
