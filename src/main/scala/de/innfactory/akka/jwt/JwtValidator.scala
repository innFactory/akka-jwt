package de.innfactory.akka.jwt

import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.proc.BadJWTException

final case class JwtToken(content: String) extends AnyVal

sealed abstract class ValidationError(message: String)
    extends BadJWTException(message)
case object EmptyJwtTokenContent extends ValidationError("Empty JWT token")
case object InvalidJwtToken extends ValidationError("Invalid JWT token")
case object MissingExpirationClaim
    extends ValidationError("Missing `exp` claim")
case object InvalidTokenUseClaim
    extends ValidationError("Invalid `token_use` claim")
case object InvalidTokenIssuerClaim
    extends ValidationError("Invalid `iss` claim")
case object InvalidTokenSubject extends ValidationError("Invalid `sub` claim")
case class UnknownException(exception: Exception)
    extends ValidationError("Unknown JWT validation error")

trait JwtValidator {
  def validate(
      jwtToken: JwtToken): Either[BadJWTException, (JwtToken, JWTClaimsSet)]
}
