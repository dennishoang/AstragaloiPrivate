package de.htwg.se.astragaloi.model

import model.Field2
import model.Dice

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class Field2Spec extends AnyWordSpec {

    "A Field" should {
        "contain 2 Matrizes" in {
            val field = new Field2(3, Dice.Empty)
            field.matrix_1.hashCode should equal (field.matrix_2.hashCode)

        }
    }
}
