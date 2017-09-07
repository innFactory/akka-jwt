package de.innfactory.akka.jwt

/**
  * This class is just for your test cases. You get automatically invalidated
  */
class AutoInvalidator extends JwtValidator {
  override def validate(jwtToken: JwtToken) =
    Left(AutoInvalidByValidator)
}
