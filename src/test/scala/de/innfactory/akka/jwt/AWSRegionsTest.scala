package de.innfactory.akka.jwt

import org.scalatest.{Matchers, WordSpec}

class AWSRegionsTest extends WordSpec with Matchers {
  "regions" should {
    "be available" in {
      val region = AWSRegions.Frankfurt
      region.regionCode shouldBe ("eu-central-1")
      region.name shouldBe ("Frankfurt")
      import AWSRegions._
      val region2 = NVirginia
      region2.regionCode shouldBe ("us-east-1")
      region2.name shouldBe ("NVirginia")
    }

    "have 14 regions as a region" in {
      AWSRegions.values.map(_.regionCode).toList.length shouldBe 14
    }
  }
}
