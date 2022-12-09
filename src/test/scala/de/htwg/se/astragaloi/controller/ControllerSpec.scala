

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
        val controller = Controller(new Field(3, 2, Dice.Empty, 0), 1)
        "put a number in the Playfielid" in {
            val move = Move(Dice.ONE, 0, 2, Dice.Empty)
            controller.field = controller.put(move)
            controller.field.playfield.cell(0, 2, 2) should be (Dice.ONE)
        }
        "put a number in the qudadrat" in {
            val move = Move(Dice.SIX, 1, 0, Dice.Empty)
            controller.field = controller.putDiceslot(move)
            controller.field.diceslot.cell(1) should be (Dice.SIX)
        }
        "print the Field" in {
            controller.toString should be (controller.field.toString)
        }
        "change the player" in {
            controller.changePlayer
            controller.player should be (0)
            controller.changePlayer
            controller.player should be (1)
        }
        "roll the Dice" in {
            val value = controller.rollDice
            if (value.equals(Dice.Empty))
                value should be (" ")
            else
                value.toString.toInt should be <= 6
        }

        "publish do steps" in {
            val move = Move(Dice.ONE, 0, 0, Dice.Empty)
            controller.Publish(controller.put, move) should be
            (println(controller.field.toString))
        }
        "publish undo steps" in {
            val move = Move(Dice.ONE, 0, 0, Dice.Empty)
            controller.Publish(controller.put, move)
            controller.Publish(controller.undo) should be
            (println(controller.field.toString))
        }
        "publish redo steps" in {
            val move = Move(Dice.ONE, 0, 0, Dice.Empty)
            controller.Publish(controller.put, move)
            controller.Publish(controller.undo)
            controller.Publish(controller.redo) should be
            (println(controller.field.toString))
        }
        "notify observers" in {
            class TestObservable (controller: Controller) extends Observable:
                val tui = new TUI(controller)
                controller.add(tui)
                controller.notifyObservers should be (tui.update)
        }
        "check Col-Publish" in {
            controller.checkColPublish(0, 1) should be (1)
        }
        "get slot" in {
            controller.getSlot(0) should be (Dice.Empty)
        }
        val controller2 = Controller(new Field(3, 2, Dice.SIX, 0), 0)
        "check finish" in {
            controller2.checkFinish(0) should be (true)
        }
        "choose Winner" in {
            controller2.chooseWinner should be (-1)
        }
        "clear the Field" in {
            controller.clear
            controller.field.playfield.cell(0, 0, 0) should be (Dice.Empty)
            controller.player should be >= 0
        }
        "start the game" in {
            controller.startGame(new Move(Dice.ONE, 1, 0, Dice.SIX))
            controller.field.getSlot(1) should be (Dice.ONE)
        }
        "change Player" in {
            controller.changePlayer
            controller.player should be (0)
        }
        "getCol" in {
            val controller1 = Controller(new Field(3, 2, Dice.ONE, 0), 1)
            val dices = controller1.getCol(1,1)
            dices(1) should be (Dice.ONE)
        }
        "putNextDice" in {
            val controller2 = Controller(new Field(3, 2, Dice.Empty, 0), 1)
            controller2.field = controller.putNextDice(new Move(Dice.Empty, 0, 0, Dice.TWO))
            controller2.getSlot(1) should be (Dice.TWO)
        }
        "getPoints" in {
            val controller2 = Controller(new Field(3, 2, Dice.ONE, 3), 1)
            controller2.getPoints(1).sum should be (12)
        }
        "finish game" in {
            controller.finish should be (controller.field)
        }
        "quit game" in {
            controller.quit
        }
    }
}




