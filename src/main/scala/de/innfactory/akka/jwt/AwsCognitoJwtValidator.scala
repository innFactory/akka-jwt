package de.innfactory.akka.jwt

import java.net.URL

import com.nimbusds.jose.jwk.source.{JWKSource, RemoteJWKSet}
import com.nimbusds.jose.proc.SecurityContext
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.proc.BadJWTException

object AWSRegions extends Enumeration {

  case class Region(name: String, regionCode: String) extends Val(name)

  val NVirginia = Region("NVirginia", "us-east-1")
  val Ohio = Region("Ohio", "us-east-2")
  val NCalifornia = Region("NCalifornia", "us-west-1")
  val Oregon = Region("Oregon", "us-west-2")
  val Canada = Region("Canada", "ca-central-1")
  val Ireland = Region("Ireland", "eu-west-1")
  val Frankfurt = Region("Frankfurt", "eu-central-1")
  val London = Region("London", "eu-west-2")
  val Tokyo = Region("Tokyo", "ap-northeast-1")
  val Seoul = Region("Seoul", "ap-northeast-2")
  val Singapore = Region("Singapore", "ap-southeast-1")
  val Sydney = Region("Sydney", "ap-southeast-2")
  val Mumbai = Region("Mumbai", "ap-south-1")
  val SaoPaulo = Region("Sao Paulo", "sa-east-1")

  implicit def valueToRegion(v: Value): Region = v.asInstanceOf[Region]
}

final case class AWSRegion(region: AWSRegions.Region) extends AnyVal

final case class CognitoUserPoolId(value: String) extends AnyVal

object AwsCognitoJwtValidator {
  def apply(
      awsRegion: AWSRegion,
      cognitoUserPoolId: CognitoUserPoolId
  ): AwsCognitoJwtValidator =
    new AwsCognitoJwtValidator(awsRegion, cognitoUserPoolId)
}

final class AwsCognitoJwtValidator(
    awsRegion: AWSRegion,
    cognitoUserPoolId: CognitoUserPoolId
) extends JwtValidator {

  import ProvidedAdditionalChelcks._

  val cognitoIdpUrl =
    s"https://cognito-idp.${awsRegion.region.regionCode}.amazonaws.com/${cognitoUserPoolId.value}"
  val cognitoIdpJwkUrl = s"$cognitoIdpUrl/.well-known/jwks.json"

  private val jwkSet: JWKSource[SecurityContext] = new RemoteJWKSet(
    new URL(cognitoIdpJwkUrl))

  /**
    * The additional checks come from the AWS Cognito documentation:
    * https://docs.aws.amazon.com/cognito/latest/developerguide/amazon-cognito-user-pools-using-tokens-with-identity-providers.html#amazon-cognito-identity-user-pools-using-id-and-access-tokens-in-web-api
    */
  private val configurableJwtValidator =
    new ConfigurableJwtValidator(
      keySource = jwkSet,
      additionalChecks = List(
        requireExpirationClaim,
        requireTokenUseClaim("access"),
        requiredIssuerClaim(cognitoIdpUrl),
        requiredNonEmptySubject
      )
    )

  override def validate(
      jwtToken: JwtToken): Either[BadJWTException, (JwtToken, JWTClaimsSet)] =
    configurableJwtValidator.validate(jwtToken)
}
