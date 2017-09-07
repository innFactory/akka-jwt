package de.innfactory.akka

import akka.http.javadsl.server.Rejections
import akka.http.scaladsl.server.Directive1
import akka.http.scaladsl.server.directives.{
  BasicDirectives,
  FutureDirectives,
  HeaderDirectives,
  RouteDirectives
}
import com.nimbusds.jwt.JWTClaimsSet
import de.innfactory.akka.jwt._

trait JwtAuthDirectives {

  import BasicDirectives._
  import FutureDirectives._
  import HeaderDirectives._
  import RouteDirectives._

  def authenticate: Directive1[(JwtToken, JWTClaimsSet)] = {
    headerValueByName("Authorization").flatMap { token =>
      onSuccess(authService.authenticate(token)).flatMap {
        case Right(claims) => provide(claims)
        case Left(error)   => reject(Rejections.authorizationFailed)
      }
    }
  }

  protected val authService: AuthService
}
