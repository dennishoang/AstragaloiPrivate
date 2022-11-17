package de.htwg.se.astragaloi.model

import de.htwg.se.astragaloi.model.Field
import de.htwg.se.astragaloi.model.Dice

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class FieldSpec extends AnyWordSpec {

    "A Field" when {
        "empty " should {

            val field = new Field(3, 2, Dice.Empty)
            val field1 = new Field(3, 2, Dice.Empty)
            val field2 = new Field(3, 2, Dice.Empty)
            val field3 = new Field(3,2, Dice.THREE)
            "have a line seperator" in {
                field.eol should equal ("\n")
            }
            "have a bar as a String of a form '+-----+     +-----+     +-----+'" in {
                field.bars(5,5) should be ("+-----+     +-----+     +-----+     " + field.eol)
            }
            "have a scalable bar" in {
                field1.bars(3,3) should be ("+---+   +---+   +---+   " + field.eol)
                field2.bars(1,1) should be ("+-+ +-+ +-+ " + field.eol)
                field2.bars(4,1) should be ("+----+ +----+ +----+ " + field.eol)
            }
            "have a cell as a String of a form '|     |     |     |     |     |'" in {
                field.cells(0, 0, 5, 5) should be ("|     |     |     |     |     |" + field.eol)
                field3.cells(0, 0, 5, 5) should be ("|  3  |     |  3  |     |  3  |" + field.eol)
            }
            "have a scalable cells" in {
                field1.cells(0, 0, 3, 4) should be ("|   |    |   |    |   |" + field.eol)
                field2.cells(0, 0, 5, 0) should be ("|     ||     ||     |" + field.eol)
                field.cells(0, 0, 0, 2) should be ("| |  | |  | |" + field.eol)
            }
            "have scalable playfield in form of" + field.eol +
            "+---+ +---+ +---+" + field.eol +
            "|   | |   | |   |" + field.eol +
            "+---+ +---+ +---+" + field.eol +
            "|   | |   | |   |" + field.eol +
            "+---+ +---+ +---+" + field.eol +
            "|   | |   | |   |" + field.eol +
            "+---+ +---+ +---+" in {
                field.playerfield(0, 3, 1, 1) should be("+---+ +---+ +---+ " + field.eol + "|   | |   | |   |" + field.eol + "+---+ +---+ +---+ "+
             field.eol + "|   | |   | |   |" + field.eol + "+---+ +---+ +---+ " + field.eol + "|   | |   | |   |" + field.eol + "+---+ +---+ +---+ " + field.eol)
            }
            "have a quadbar as a String of a form '+---+'" in {
                field.quadbar(3,0) should be ("+---+" + field.eol)
            }
            "have scalable quadbars" in {
                field.quadbar(5,3) should be ("   +-----+" + field.eol)
                field1.quadbar(1,5) should be ("     +-+" + field.eol)
                field2.quadbar(4,4) should be ("    +----+" + field.eol)
            }
            "have a quadcell as a String of a form '|   |'" in {
                field.quadcell(0, 3, 4) should be ("    |   |" + field.eol)
            }
            "have scalable quadcells" in {
                field.quadcell(0, 5, 0) should be ("|     |" + field.eol)
                field1.quadcell(0, 1, 1) should be (" | |" + field.eol)
                field2.quadcell(0, 7, 2) should be ("  |       |" + field.eol)
            }
            "have a scalable quadrat in form of" + field.eol +
            "+-----+" + field.eol +
            "|     |" + field.eol +
            "|     |" + field.eol +
            "+-----+" in {
                field.quadrat(0, 5, 2, 0) should be ("+-----+" + field.eol + "|     |" + field.eol + "|     |" + field.eol + "+-----+" + field.eol)
            }

            "have as scalable mesh in form of" + field.eol +
            "               +-----+" + field.eol +
            "               |     |" + field.eol +
            "               +-----+" + field.eol + field.eol +
            "+---+ +---+ +---+ " + field.eol +
            "|   | |   | |   |" + field.eol +
            "+---+ +---+ +---+ " + field.eol +
            "|   | |   | |   |" + field.eol +
            "+---+ +---+ +---+ " + field.eol +
            "|   | |   | |   |" + field.eol +
            "+---+ +---+ +---+ " + field.eol + field.eol +
            "-------------------------" + field.eol * 2 +
            "+---+ +---+ +---+ " + field.eol +
            "|   | |   | |   |" + field.eol +
            "+---+ +---+ +---+ " + field.eol +
            "|   | |   | |   |" + field.eol +
            "+---+ +---+ +---+ " + field.eol +
            "|   | |   | |   |" + field.eol +
            "+---+ +---+ +---+ " + field.eol + field.eol +
            "               +-----+" + field.eol +
            "               |     |" + field.eol +
            "               +-----+" + field.eol in {
                field.mesh() should be (
                    "               +-----+" + field.eol +
                    "               |     |" + field.eol +
                    "               +-----+" + field.eol + field.eol +
                    "+---+ +---+ +---+ " + field.eol +
                    "|   | |   | |   |" + field.eol +
                    "+---+ +---+ +---+ " + field.eol +
                    "|   | |   | |   |" + field.eol +
                    "+---+ +---+ +---+ " + field.eol +
                    "|   | |   | |   |" + field.eol +
                    "+---+ +---+ +---+ " + field.eol + field.eol +
                    "-------------------------" + field.eol * 2 +
                    "+---+ +---+ +---+ " + field.eol +
                    "|   | |   | |   |" + field.eol +
                    "+---+ +---+ +---+ " + field.eol +
                    "|   | |   | |   |" + field.eol +
                    "+---+ +---+ +---+ " + field.eol +
                    "|   | |   | |   |" + field.eol +
                    "+---+ +---+ +---+ " + field.eol + field.eol +
                    "               +-----+" + field.eol +
                    "               |     |" + field.eol +
                    "               +-----+" + field.eol)

            }
            "for test purposes toString" in {
                field.bars(3,0).toString should be ("+---++---++---+" + field.eol)
                field.quadbar(5,2).toString should be ("  +-----+" + field.eol)
                field.toString() should be (field.mesh())
            }

            "can put a number in the matrix" in {
                val field8 = new Field(3, 2, Dice.Empty)
                val fieldput = field8.put(Dice.SIX, 0 , 1, 0)
                fieldput.playerfield(0, 3, 1 , 1)should be (
                "+---+ +---+ +---+ " + field.eol +
                "|   | |   | |   |" + field.eol +
                "+---+ +---+ +---+ " + field.eol +
                "| 6 | |   | |   |" + field.eol +
                "+---+ +---+ +---+ " + field.eol +
                "|   | |   | |   |" + field.eol +
                "+---+ +---+ +---+ " + field.eol)
            }

            "can put a number in the quadrat" in {
                val field69 = new Field(3,2, Dice.Empty)
                val quadratput = field69.putSlot(Dice.FOUR, 0)
                val qudratput1 = field69.putSlot(Dice.FIVE, 1)
                quadratput.quadrat(0,5,1,0) should be(
                    "+-----+" + field.eol +
                    "|  4  |" + field.eol +
                    "+-----+" + field.eol
                )
                qudratput1.quadrat(1,5,1,0) should be(
                    "+-----+" + field.eol +
                    "|  5  |" + field.eol +
                    "+-----+" + field.eol
                )



            }


        }
    }
}
