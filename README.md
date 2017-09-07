# akka-jwt
[![travis-ci.org](https://travis-ci.org/innFactory/akka-jwt.svg?branch=master)](https://travis-ci.org/innFactory/akka-jwt)
[![codecov.io](https://img.shields.io/codecov/c/github/innFactory/akka-jwt/master.svg?style=flat)](https://codecov.io/github/innFactory/akka-jwt)
[![shields.io](http://img.shields.io/badge/license-Apache2-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)
[ ![Download](https://api.bintray.com/packages/innfactory/sbt-plugins/akka-jwt/images/download.svg) ](https://bintray.com/innfactory/sbt-plugins/akka-jwt/_latestVersion)

Library for jwt authentication with akka


## Information
This library provides you an akka directive for your route to authenticate your user with jwt. the jwt implementation adapts nimbus JOSE + JWT.

### Setup
```scala
resolvers += Resolver.bintrayRepo("innfactory", "sbt-plugins") // or via maven central
libraryDependencies += "de.innFactory" %% "akka-jwt" % "x.x.x"
```

After that you must extend your akka-http Route with ```JwtAuthDirectives```. Then just implement a AuthService ```protected val authService: AuthService```

After that you can build your route like this: 

```scala
val route: Route =
    (post & path("graphql")) {
      authenticate { credentials =>
        entity(as[JsValue]) { requestJson ⇒
```

you see, that you got a new authenticate directive for your route. It extracts the Authentication value from your header and checks it against your jwt validator.

## Validator API

The Validator API has just one method ```validate```, so you can implement your own Validators and use it for your akka Directive. AWS and the generic one were made by guizmaii. Thanks for that!

```
final case class JwtToken(content: String) extends AnyVal

trait JwtValidator {
  def validate(jwtToken: JwtToken): Either[BadJWTException, (JwtToken, JWTClaimsSet)]
}
```

### Available `JwtValidator` implementations

#### 1. ConfigurableJwtValidator

The more flexible implementation of the `JwtValidator` interface.

It only requires a `JWKSource` instance.    
For more information on the different `JWKSource` implementations Nimbus provides, look at the classes in the `com.nimbusds.jose.jwk.source` package here: https://www.javadoc.io/doc/com.nimbusds/nimbus-jose-jwt

Example of use:
```scala
val token: JwtToken = JwtToken(content = "...")

val jwkSet: JWKSource[SecurityContext] = new RemoteJWKSet(new URL(s"https://your.jwks.prodvider.example.com/.well-known/jwks.json"))
val validator =  ConfigurableJwtValidator(jwkSet)
```

For more information on JWKs, you could read:   
  - Auth0 doc: https://auth0.com/docs/jwks    
  - Nimbus doc: https://connect2id.com/products/server/docs/api/jwk-set       
  - AWS Cognito doc: https://docs.aws.amazon.com/cognito/latest/developerguide/amazon-cognito-user-pools-using-tokens-with-identity-providers.html#amazon-cognito-identity-user-pools-using-id-and-access-tokens-in-web-api

Other constructor parameters are:

  - `maybeCtx: Option[SecurityContext] = None`   
    (Optional) Security context.    
    Default is `null` (no Security Context).
    
  - `additionalChecks: List[(JWTClaimsSet, SecurityContext) => Option[BadJWTException]] = List.empty`   
    (Optional) List of additional checks that will be executed on the JWT token passed.    
    Default is an empty List.
    
    Some "additional checks" are already implemented in the object `ProvidedAdditionalChelcks`.

#### 2. AwsCognitoJwtValidator

Example of use:
```scala
val awsRegion = AWSRegion(AWSRegions.Frankfurt)
val cognitoUserPoolId = CognitoUserPoolId(value = "...")

val awsCognitoJwtValidator = AwsCognitoJwtValidator(awsRegion, cognitoUserPoolId)
```


## Copyright & Contributers
Thanks to guizmaii's template for nimbus integration in scala.
Tobias Jonas

Copyright (C) 2017 [innFactory Cloud- & DataEngineering](https://innFactory.de)

Published under the Apache 2 License.
