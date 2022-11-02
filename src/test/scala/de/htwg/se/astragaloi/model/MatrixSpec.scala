package de.htwg.se.astragaloi.model

import model.Matrix
import model.Dice

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class MatrixSpec extends AnyWordSpec {
    "A Matrix" should {
        "be created by using a dimention and a cell" in {
            val matrix = new Matrix[String](2, "3")
            matrix.size should be(2)
        }
        "for test purposes" in {
            val matrix2 = new Matrix[Dice](2, Dice.ONE)
            matrix2.cell(0,0) should be(Dice.ONE)
        }
    }
}