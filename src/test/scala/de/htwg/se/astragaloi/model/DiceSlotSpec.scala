package de.htwg.se.astragaloi.model

import de.htwg.se.astragaloi.model.DiceSlot
import de.htwg.se.astragaloi.model.Dice

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
            diceslot.cell(0) should be (Dice.Empty)
            diceslot2.cell(0) should be (Dice.ONE)
        }

        "replace cells" in {
            val diceslot3 = new DiceSlot(2,Dice.Empty)
            diceslot3.cell(0) should be (Dice.Empty)
            diceslot3.cell(1) should be (Dice.Empty)
            val replacediceslot3 = diceslot3.replace(0, Dice.FIVE)
            val replacediceslot4 = diceslot3.replace(1, Dice.TWO)
            replacediceslot3.cell(0) should be (Dice.FIVE)
            replacediceslot4.cell(1) should be (Dice.TWO)
        }

        "filled should be filling the dice" in {
            val returndiceslot = diceslot.fill(Dice.SIX)
            returndiceslot.cell(0) should be (Dice.SIX)
        }

    }
}