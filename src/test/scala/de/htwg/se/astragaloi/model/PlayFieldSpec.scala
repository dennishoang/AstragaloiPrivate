/*
package de.htwg.se.astragaloi.model

import de.htwg.se.astragaloi.model.PlayField
import de.htwg.se.astragaloi.model.Dice

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayFieldSpec extends AnyWordSpec {
    val playfield = new PlayField[Dice](2, Dice.ONE)
    "A PlayField" should {
        "be created by using a dimention and a cell" in {
            playfield.size should be(8)
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
        "return rows" in {
            val playfield4 = new PlayField[Dice](3, Dice.SIX)
            val sum = playfield4.row(1,0).map(_.toString).map(_.toInt).sum
            sum should be (18)
        }
        "returns cols" in {
            val playfield5 = new PlayField[Dice](3, Dice.SIX)
            val sum = playfield5.col(1,0).map(_.toString).map(_.toInt).sum
            sum should be (18)
        }
        "be filled" in {
            val playfield5 = playfield.fill(Dice.Empty)
            playfield5.cell(0, 0, 0) should be(Dice.Empty)
        }
    }
}

*/