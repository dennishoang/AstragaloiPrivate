/*
package de.htwg.se.astragaloi.model

import de.htwg.se.astragaloi.model.PlayField
import de.htwg.se.astragaloi.model.Dice

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class InsertSpec extends AnyWordSpec {

    "An Insert" should {
        class InsertTest[T] extends Insert[T]:
            override def destroyValue(matrix: Int, x: Int, value: T, clear: T): PlayField[T] =


        val insert = new InsertTest
        "should insert a Value" in {
            val playfield = new PlayField[Dice](3, Dice.Empty)
            val modPlayfield = insert.insertValue(playfield, 0, 0, Dice.SIX, Dice.Empty)
            modPlayfield.col(0, 0).map(_.toString).filter(!_.equals(" ")).map(_.toInt).sum should be (6)
        }
    }
}
*/