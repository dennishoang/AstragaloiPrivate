package de.htwg.se.astragaloi.model

import de.htwg.se.astragaloi.model.DiceRoll

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class DiceRollSpec extends AnyWordSpec {
    "A Dice" should {
        "have a random value" in {
            val random = DiceRoll.random
            if (random.equals(" ")) {
                random should be (" ")
            } else {
                random.toString.toInt should be <= 6
            }
        }
    }
}
