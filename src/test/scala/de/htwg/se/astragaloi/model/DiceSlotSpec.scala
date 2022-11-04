package de.htwg.se.astragaloi.model

import model.DiceSlot
import model.Dice

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class DiceSlotSpec extends AnyWordSpec {
    val diceslot = new DiceSlot(2, Dice.Empty)
    "A DiceSlot" should {
        "be created by using a size and filling" in {
            diceslot.size should be (2)
        }
        "for test purposes" in {
            val diceslot2 = new DiceSlot(2, Dice.ONE)
            diceslot2.cell(0) should be (Dice.ONE)
        }
        "filled should be filling the dice" in {
            val returndiceslot = diceslot.fill(Dice.SIX)
            returndiceslot.cell(0) should be (Dice.SIX)
        }


    }
}