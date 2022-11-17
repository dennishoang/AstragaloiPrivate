package de.htwg.se.astragaloi.model

import de.htwg.se.astragaloi.model.Dice

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class DiceSpec extends AnyWordSpec {
    "A Dice" should {
        "have a random value" in {
            val random = Dice.random
            if (random.equals(" ")) {
                random should be (" ")
            } else {
                random.toString.toInt should be <= 6
            }
        }
    }
}
