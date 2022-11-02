/* package de.htwg.se.astragaloi.model

import model.Matrix
import model.Dice

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class MatrixSpec extends AnyWordSpec {
    val matrix = new Matrix[Dice](2, Dice.ONE)
    "A Matrix" should {
        "be created by using a dimention and a cell" in {
            matrix.size should be(2)
        }
        "for test purposes" in {
            val matrix2 = new Matrix[Dice](2, Dice.ONE)
            matrix2.cell(0,0) should be(Dice.ONE)
        }
        "replace cells" in {
            val matrix3 = matrix.replaceCell(0, 0, Dice.TWO)
            matrix.cell(0,0) should be(Dice.ONE)
            matrix3.cell(0,0) should be(Dice.TWO)
        }
        "be filled" in {
            val matrix4 = matrix.fill(Dice.Empty)
            matrix4.cell(0,0) should be(Dice.Empty)
        }
    }
} */