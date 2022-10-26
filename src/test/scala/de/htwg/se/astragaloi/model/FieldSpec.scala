package de.htwg.se.astragaloi.model

import model.Field

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class FieldSpec extends AnyWordSpec {

    "A Field" when {
        "empty " should {
            "be created by using a dimension and a simple cell" in {
                val field = new Field
                field.playfield(10,10,10) should be(2)
            }
        }
    }
}

