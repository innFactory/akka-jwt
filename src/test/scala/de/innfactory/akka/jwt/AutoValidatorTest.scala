package de.innfactory.akka.jwt

import org.scalatest.{Matchers, WordSpec}

class AutoValidatorTest extends WordSpec with Matchers {
  "AutoValidator" should {
    "be sucessfull" in {
      val validator = new AutoValidator()
      validator.validate(JwtToken("any")).value._1.content shouldBe ("any")
      validator
        .validate(JwtToken("any"))
        .value
        ._2
        .getSubject shouldBe ("auto-validated")
    }
  }
}
