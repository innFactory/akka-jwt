package de.innfactory.akka

import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.proc.BadJWTException
import de.innfactory.akka.jwt.{JwtToken, JwtValidator}

import scala.concurrent.{ExecutionContext, Future, blocking}

class AuthService(jwtValidator: JwtValidator)(
    implicit executionContext: ExecutionContext) {

  def authenticate(accessToken: String)
    : Future[Either[BadJWTException, (JwtToken, JWTClaimsSet)]] = Future {
    blocking {
      jwtValidator.validate(JwtToken(accessToken))
    }
  }

}
