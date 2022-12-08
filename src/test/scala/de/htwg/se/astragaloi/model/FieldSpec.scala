
package de.htwg.se.astragaloi.model

import de.htwg.se.astragaloi.model.Field
import de.htwg.se.astragaloi.model.Dice

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class FieldSpec extends AnyWordSpec {

    "A Field" when {
        "empty " should {

            val eol = ("\n")
            "have a constructor" in {
                val field = new Field(new PlayField[Dice](3, Dice.Empty), new DiceSlot[Dice](2, Dice.Empty), new PointSlot[Dice](2, 0))
                val field2 = new Field(3, 2, Dice.Empty, 0)
                field should be (field)
                field2 should be (field2)
            }
            "have a mesh in form of field" in {
                val field = new Field(new PlayField[Dice](3, Dice.Empty), new DiceSlot[Dice](2, Dice.Empty), new PointSlot[Dice](2,0))
                field.toString() should be (
                    "               +-----+" + eol +
                    "               |     |" + eol +
                    "               +-----+" + eol + eol +
                    "+---+ +---+ +---+ " + eol +
                    "|   | |   | |   |" + eol +
                    "+---+ +---+ +---+ " + eol +
                    "|   | |   | |   |" + eol +
                    "+---+ +---+ +---+ " + eol +
                    "|   | |   | |   |" + eol +
                    "+---+ +---+ +---+ " + eol + eol +

                    "| 0 | | 0 | | 0 | | 0 |" + eol + eol +

                    "=========================" + eol + eol +

                    "| 0 | | 0 | | 0 | | 0 |" + eol + eol +

                    "+---+ +---+ +---+ " + eol +
                    "|   | |   | |   |" + eol +
                    "+---+ +---+ +---+ " + eol +
                    "|   | |   | |   |" + eol +
                    "+---+ +---+ +---+ " + eol +
                    "|   | |   | |   |" + eol +
                    "+---+ +---+ +---+ " + eol + eol +

                    "               +-----+" + eol +
                    "               |     |" + eol +
                    "               +-----+"

                )
            }

            "put Dice, Number, Points in Field" in {
                val fieldtest = new Field(new PlayField[Dice](3, Dice.Empty), new DiceSlot[Dice](2, Dice.Empty), new PointSlot[Dice](2,0))
                val putDice = fieldtest.putSlot(Dice.SIX, 1)
                val oldValues = Vector[Int]()
                val putNumber = putDice.put(Dice.FOUR, 0, 1, 0, oldValues)
                val putpoints = putNumber.putPoint(0, 1)

                putpoints.toString() should be(
                    "               +-----+" + eol +
                    "               |     |" + eol +
                    "               +-----+" + eol + eol +

                    "+---+ +---+ +---+ " + eol +
                    "|   | |   | |   |" + eol +
                    "+---+ +---+ +---+ " + eol +
                    "|   | |   | |   |" + eol +
                    "+---+ +---+ +---+ " + eol +
                    "|   | | 4 | |   |" + eol +
                    "+---+ +---+ +---+ " + eol + eol +

                    "| 0 | | 4 | | 0 | | 4 |" + eol + eol +

                    "=========================" + eol + eol +

                    "| 0 | | 0 | | 0 | | 0 |" + eol + eol +

                    "+---+ +---+ +---+ " + eol +
                    "|   | |   | |   |" + eol +
                    "+---+ +---+ +---+ " + eol +
                    "|   | |   | |   |" + eol +
                    "+---+ +---+ +---+ " + eol +
                    "|   | |   | |   |" + eol +
                    "+---+ +---+ +---+ " + eol + eol +

                    "               +-----+" + eol +
                    "               |  6  |" + eol +
                    "               +-----+"
                )
            }
            "check if col is full" in {
                val fieldtest1 = new Field(new PlayField[Dice](3, Dice.ONE), new DiceSlot[Dice](2, Dice.Empty), new PointSlot[Dice](2,0))
                val colcheck = fieldtest1.colcheck(0,0)
                colcheck should be (-1)
            }
            "check if game is finished" in {
                val field = new Field(3, 2, Dice.SIX, 0)
                field.checkFinish(0) should be (true)
            }
            "choose a Winner" in {
                val field = new Field(3, 2, Dice.SIX, 0)
                field.chooseWinner should be (-1)
            }
        }
    }
}
