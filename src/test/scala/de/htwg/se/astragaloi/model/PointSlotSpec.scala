package de.htwg.se.astragaloi.model

import de.htwg.se.astragaloi.model.PlayField
import de.htwg.se.astragaloi.model.Dice

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PointSlotSpec extends AnyWordSpec {
    val pointslot = new PointSlot[Dice](2,0)
    "A PointSlot" should {
        "be created by using a dimentationand a cell" in {
            pointslot.size should be (2)
        }
    }
    "for test purposes" in {
        val pointslot1 = new PointSlot[Dice](2,1)
        pointslot.cell(0,0) should be (0)
        pointslot1.cell(0,1) should be (1)
    }
    "be filled" in {
        val pointslot2 = pointslot.fill(3)
        pointslot2.cell(0,0) should be (3)
    }
    "replace cells" in {
        val pointslot3 = pointslot.replacePoints(new PlayField[Dice](3,Dice.ONE), 0, 0)
        pointslot3.cell(0,0) should be (9)
    }
    "replace all points" in {
        val playfield = new PlayField[Dice](3,Dice.TWO)
        val pointslot4 = pointslot.replaceAllPoints(playfield,0,0)
        pointslot4.cell(0,0) should be (18)
        pointslot4.cell(1,0) should be (18)
    }
    "calculate points" in {
        val pointslotrow = pointslot.calculatePoints(new PlayField[Dice](3, Dice.ONE), 0, 1, 0, 0)
        pointslotrow should be (9)
    }
    "calculate col" in {
        val pointslot9 = new PointSlot[Dice](2,5)
        val pointslotrow = pointslot9.calculatePoints(new PlayField[Dice](3, Dice.Empty), 0, 1, 99, 5)
        pointslotrow should be (15)
    }
    "choose the Winner" in {
        var playfield = new PlayField[Dice](3, Dice.Empty)
        val oldIndexes = Vector[Int]()
        playfield = playfield.insertValue(playfield, 1, 0, Dice.SIX, Dice.Empty, 0, oldIndexes)
        var pointslot4 = pointslot.replaceAllPoints(playfield, 1, 0)
        pointslot4.chooseWinner should be(2)

        playfield = playfield.insertValue(playfield, 0, 0, Dice.SIX, Dice.Empty, 0, oldIndexes)
        var pointslot5 = pointslot.replaceAllPoints(playfield, 0, 0)
        pointslot5.chooseWinner should be(1)
    }
    "choose a draw" in {
        var playfield = new PlayField[Dice](3, Dice.Empty)
        var pointslot6 = pointslot.replaceAllPoints(playfield, 1, 0)
        pointslot6.chooseWinner should be(-1)
    }
}