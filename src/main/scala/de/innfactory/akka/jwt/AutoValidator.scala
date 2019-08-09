package de.innfactory.akka.jwt

import com.nimbusds.jwt.JWTClaimsSet

/**
  * This class is just for your test cases. You get automatically validated with the token "auto-validated"
  */
class AutoValidator extends JwtValidator {
  override def validate(jwtToken: JwtToken) =
    Right((jwtToken, JWTClaimsSet.parse("{\"sub\" : \"auto-validated\"}")))
}
