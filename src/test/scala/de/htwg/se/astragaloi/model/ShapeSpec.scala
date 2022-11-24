package de.htwg.se.astragaloi.model

import de.htwg.se.astragaloi.model.Shape
import de.htwg.se.astragaloi.model.PlayField
import de.htwg.se.astragaloi.model.Dice
import de.htwg.se.astragaloi.model.DiceSlot
import de.htwg.se.astragaloi.model.PointSlot

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ShapeSpec extends AnyWordSpec {

    "A CellMatrix" should {
        val playfield = new PlayField[Dice](3, Dice.Empty)
        val cellMatrix = new CellMatrix[Dice](playfield, 5, 5, 5, 3, 0)
        "have scalable bars" in {
            cellMatrix.bars() should be ("+-----+     +-----+     +-----+     ")
        }
        "have scalable cells" in {
            cellMatrix.cells(0) should be (cellMatrix.eol + "|     |     |     |     |     |" + cellMatrix.eol)
        }
        val cellMatrix2 = new CellMatrix[Dice](playfield, 3, 1, 1, 3, 0)
        "have a method create" in {
            cellMatrix2.create() should be ("+---+ +---+ +---+ " + cellMatrix2.eol + "|   | |   | |   |" + cellMatrix2.eol + "+---+ +---+ +---+ " +
            cellMatrix2.eol + "|   | |   | |   |" + cellMatrix2.eol + "+---+ +---+ +---+ " + cellMatrix2.eol + "|   | |   | |   |" + cellMatrix2.eol + "+---+ +---+ +---+ ")
        }
    }

    "A Quadrat" should {
        val diceslot = new DiceSlot[Dice](2, Dice.Empty)
        val quadrat = new Quadrat[Dice](diceslot, 5, 3, 1, 0)
        "have scalable bars" in {
            quadrat.bars() should be ("   +-----+")
        }
        "have scalable cells" in {
            quadrat.cells() should be (quadrat.eol + "   |     |" + quadrat.eol)
        }
        "have a method create" in {
            quadrat.create() should be ("   +-----+" + quadrat.eol + "   |     |" + quadrat.eol +
            "   +-----+")
        }
    }

    "A PointCell" should {
        val pointslot = new PointSlot[Dice](2, 5)
        val pointcell = new PointCell[Dice](pointslot, 3, 1, 0)
        "have a method create" in {
            pointcell.create() should be ("| 5 | | 5 | | 5 | | 5 |")
        }
    }

    "A MatrixSeperator" should {
        val matrixSeperator = new MatrixSeperator(20)
        "have a method create" in {
            matrixSeperator.create() should be ("====================")
        }
    }

    "A CompositeShape" should {
        val playfield = new PlayField[Dice](3, Dice.Empty)
        val diceslot = new DiceSlot[Dice](2, Dice.Empty)
        val pointslot = new PointSlot[Dice](2, 0)

        val compositeShape = new CompositeShape(
            new Quadrat[Dice](diceslot, 5, 15, 1, 0),
            new CellMatrix[Dice](playfield, 3, 1, 1, 3, 0),
            new PointCell[Dice](pointslot, 3, 1, 0),
            new MatrixSeperator(25),
            new PointCell[Dice](pointslot, 3, 1, 1),
            new CellMatrix[Dice](playfield, 3, 1, 1, 3, 1),
            new Quadrat[Dice](diceslot, 5, 15, 1, 1)
        )

        "have a method create" in {
            compositeShape.create() should be ("               +-----+" + compositeShape.eol +
            "               |     |" + compositeShape.eol +
            "               +-----+" + compositeShape.eol + compositeShape.eol +
            "+---+ +---+ +---+ " + compositeShape.eol +
            "|   | |   | |   |" + compositeShape.eol +
            "+---+ +---+ +---+ " + compositeShape.eol +
            "|   | |   | |   |" + compositeShape.eol +
            "+---+ +---+ +---+ " + compositeShape.eol +
            "|   | |   | |   |" + compositeShape.eol +
            "+---+ +---+ +---+ " + compositeShape.eol + compositeShape.eol +

            "| 0 | | 0 | | 0 | | 0 |" + compositeShape.eol + compositeShape.eol +

            "=========================" + compositeShape.eol + compositeShape.eol +

            "| 0 | | 0 | | 0 | | 0 |" + compositeShape.eol + compositeShape.eol +

            "+---+ +---+ +---+ " + compositeShape.eol +
            "|   | |   | |   |" + compositeShape.eol +
            "+---+ +---+ +---+ " + compositeShape.eol +
            "|   | |   | |   |" + compositeShape.eol +
            "+---+ +---+ +---+ " + compositeShape.eol +
            "|   | |   | |   |" + compositeShape.eol +
            "+---+ +---+ +---+ " + compositeShape.eol + compositeShape.eol +

            "               +-----+" + compositeShape.eol +
            "               |     |" + compositeShape.eol +
            "               +-----+")
        }

    }
}