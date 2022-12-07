
package de.htwg.se.astragaloi.model

import de.htwg.se.astragaloi.model.PlayField
import de.htwg.se.astragaloi.model.Dice

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayFieldSpec extends AnyWordSpec {
    val playfield = new PlayField[Dice](2, Dice.ONE)
    "A PlayField" should {
        "have a colsize" in {
            playfield.colsize should be (2)
        }
        "for test purposes" in {
            val playfield2 = new PlayField[Dice](2, Dice.ONE)
            playfield2.cell(0, 0, 0) should be(Dice.ONE)
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
        "check a col" in {
            val playfield6 = new PlayField[Dice](3, Dice.SIX)
            playfield6.checkCol(0, 0) should be (-1)
        }
        "be filled" in {
            val playfield5 = playfield.fill(Dice.Empty)
            playfield5.cell(0, 0, 0) should be(Dice.Empty)
        }
        "replace cells" in {
            val playfield3 = playfield.replaceCell(1, 1, 1, Dice.TWO)
            playfield.cell(1, 1, 1) should be(Dice.ONE)
            playfield3.cell(1, 1, 1) should be(Dice.TWO)
        }
        "destroy values" in {
            val playfield7 = new PlayField[Dice](3, Dice.SIX)
            val playfield8 = playfield7.destroyValue(0, 0, Dice.SIX, Dice.Empty)
            val sum = playfield8.col(1, 0).map(_.toString).filter(_.equals(" ")).map(_ => 0).sum
            sum should be (0)
        }
        "insert values" in {
            val playfield9 = new PlayField[Dice](3, Dice.Empty)
            val oldIndexes = Vector[Int]()
            val playfield10 = playfield9.insertValue(playfield9, 0, 0, Dice.TWO, Dice.Empty, 0, oldIndexes)
            playfield10.col(0, 0).map(_.toString).filter(!_.equals(" ")).map(_.toInt).sum should be (2)
        }
        "undestroy values" in {
            var playfield9 = new PlayField[Dice](3, Dice.ONE)
            playfield9 = playfield9.destroyValue(1, 0, Dice.ONE, Dice.Empty)
            val oldIndexes = Vector(0, 1, 2)
            playfield9 = playfield9.undestroyValue(1, 0, Dice.ONE, oldIndexes)
            playfield9.cell(0, 0, 0) should be (Dice.ONE)
        }
    }
}

