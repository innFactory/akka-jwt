package de.innfactory.akka.jwt

import org.scalatest.{Matchers, WordSpec}

class AwsCognitoJwtValidatorTest extends WordSpec with Matchers {
  "AwsCognitoJwtValidator" should {
    "have the right idp url" in {

      val awsRegion = AWSRegion(AWSRegions.Frankfurt)
      val cognitoUserPoolId = CognitoUserPoolId(value = "innFactoryPool123")
      val tokenUseClaim = TokenUseClaim("id")

      val awsCognitoJwtValidator =
        AwsCognitoJwtValidator(awsRegion, cognitoUserPoolId, tokenUseClaim)

      awsCognitoJwtValidator.cognitoIdpUrl shouldBe s"https://cognito-idp.eu-central-1.amazonaws.com/innFactoryPool123"
      awsCognitoJwtValidator.cognitoIdpJwkUrl shouldBe s"https://cognito-idp.eu-central-1.amazonaws.com/innFactoryPool123/.well-known/jwks.json"
    }

  }

}
