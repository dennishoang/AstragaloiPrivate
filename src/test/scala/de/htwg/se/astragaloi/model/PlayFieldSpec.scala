package de.htwg.se.astragaloi.model

import model.PlayField
import model.Dice

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayFieldSpec extends AnyWordSpec {
    val playfield = new PlayField[Dice](2, Dice.ONE)
    "A PlayField" should {
        "be created by using a dimention and a cell" in {
            playfield.size should be(2)
        }
        "for test purposes" in {
            val playfield2 = new PlayField[Dice](2, Dice.ONE)
            playfield2.cell(0, 0, 0) should be(Dice.ONE)
        }
        "replace cells" in {
            val playfield3 = playfield.replaceCell(1, 1, 1, Dice.TWO)
            playfield.cell(1, 1, 1) should be(Dice.ONE)
            playfield3.cell(1, 1, 1) should be(Dice.TWO)
        }
        "be filled" in {
            val playfield4 = playfield.fill(Dice.Empty)
            playfield4.cell(0, 0, 0) should be(Dice.Empty)
        }
    }
}