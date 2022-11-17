package de.htwg.se.astragaloi

import de.htwg.se.astragaloi.controller.Controller
import de.htwg.se.astragaloi.model.Field
import de.htwg.se.astragaloi.model.Dice
import de.htwg.se.astragaloi.model.Move
import de.htwg.se.astragaloi.aview.TUI
import de.htwg.se.astragaloi.util.Observable
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class ControllerSpec extends AnyWordSpec {
    "The Controller" should {
        val controller = Controller(new Field(3,2, Dice.Empty))
        "put a number in the Playfielid" in {
            val move = Move(Dice.ONE, 0, 2, 1)
            controller.field = controller.putPlayfield(move)
            controller.field.playfield.cell(0, 2, 1) should be (Dice.ONE)
        }
        "put a number in the qudadrat" in {
            val move = Move(Dice.SIX, 1, 0, 0)
            controller.field = controller.putDiceslot(move)
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
            controller.Publish(controller.putPlayfield, move) should be
            (println(controller.field.toString))
        }
        "notify observers" in {
            class TestObservable (controller: Controller) extends Observable:
                val tui = new TUI(controller)
                controller.add(tui)
                controller.notifyObservers should be (tui.update)
        }
    }
}
