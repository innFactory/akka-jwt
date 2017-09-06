package de.innfactory.akka.jwt

import com.nimbusds.jwt.JWTClaimsSet

/**
  * This class is just for your test cases. You get automatically validated with the token "auto-validated"
  */
class AutoValidator extends JwtValidator {
  override def validate(jwtToken: JwtToken) =
    Right(
      (JwtToken("auto-validated"),
       JWTClaimsSet.parse("{\"sub\" : \"auto-validated\"}")))
}
