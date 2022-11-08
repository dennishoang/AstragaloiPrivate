package de.htwg.se.astragaloi.model



import de.htwg.se.astragaloi.controller.Controller
import model.Field
import model.Dice
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ControllerSpec extends AnyWordSpec {
    "The Controller" should {
        val controller = Controller(new Field(3,2, Dice.Empty))
        "put a number in the Playfielid" in {
            val putnumber = controller.put(Dice.ONE, 0, 2, 1)
            controller.field.playfield.cell(0, 2, 1) should be (Dice.ONE)
        }
        "put a number in the qudadrat" in {
            val putslot = controller.putSlot(Dice.SIX, 1)
            controller.field.diceslot.cell(1) should be (Dice.SIX)
        }
        "print the Field" in {
            controller.toString should be (controller.field.toString)
        }
    }
}


