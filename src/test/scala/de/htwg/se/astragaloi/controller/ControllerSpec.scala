package de.htwg.se.astragaloi.model



import de.htwg.se.astragaloi.controller.Controller
import model.Field
import model.Dice
import model.Move
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ControllerSpec extends AnyWordSpec {
    "The Controller" should {
        val controller = Controller(new Field(3,2, Dice.Empty))
        "put a number in the Playfielid" in {
            val move = Move(Dice.ONE, 0, 2, 1)
            controller.field = controller.put(move)
            controller.field.playfield.cell(0, 2, 1) should be (Dice.ONE)
        }
        "put a number in the qudadrat" in {
            val move = Move(Dice.SIX, 1, 0, 0)
            controller.field = controller.putSlot(move)
            controller.field.diceslot.cell(1) should be (Dice.SIX)
        }
        "print the Field" in {
            controller.toString should be (controller.field.toString)
        }
        "change the player" in {
            controller.changePlayer(1) should be (0)
            controller.changePlayer(0) should be (1)
        }
        "be published" in {
            val move = Move(Dice.ONE, 0, 0, 0)
            controller.Publish(controller.put, move) should be
            (println(controller.field.toString))
        }
    }
}


