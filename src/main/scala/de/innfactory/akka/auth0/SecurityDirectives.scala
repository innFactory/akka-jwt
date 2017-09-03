package de.innfactory.akka.auth0

import akka.http.scaladsl.server.Directive1
import akka.http.scaladsl.server.directives.{BasicDirectives, FutureDirectives, HeaderDirectives, RouteDirectives}
import scala.concurrent.Future

trait SecurityDirectives {

  import BasicDirectives._
  import FutureDirectives._
  import HeaderDirectives._
  import RouteDirectives._

  def authenticate: Directive1[Map[String, AnyRef]] = {

      optionalHeaderValueByName("Authorization").flatMap { token =>
        onSuccess(authenticate(token.getOrElse(""))).flatMap {
          case Some(user) => provide(user)
          case None => reject
        }
      }

  }

  def authenticate(accessToken: String): Future[Option[Map[String, AnyRef]]]

}
