package de.htwg.se.astragaloi.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.PlayField
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Dice
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.DiceSlot
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.PointSlot
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Move

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class PutCommandSpec extends AnyWordSpec {

    "A PutCommand" should {
        "undo Steps" in {
            var playfield = new PlayField[Dice](3, Dice.Empty)
            var diceslot = new DiceSlot[Dice](2, Dice.Empty)
            var pointslot = new PointSlot[Dice](2, 0)
            var field = new Field(playfield, diceslot, pointslot)
            val putCommand = new PutCommand(new Move(Dice.SIX, 0, 0, Dice.Empty))
            val field2 = putCommand.doStep(field)
            val field3 = putCommand.undoStep(field2)
            (field3.col(0, 0))(0) should be (Dice.Empty)
        }
        "redo Steps" in {
            var playfield = new PlayField[Dice](3, Dice.Empty)
            var diceslot = new DiceSlot[Dice](2, Dice.Empty)
            var pointslot = new PointSlot[Dice](2, 0)
            var field = new Field(playfield, diceslot, pointslot)
            val putCommand = new PutCommand(new Move(Dice.SIX, 0, 0, Dice.Empty))
            val field2 = putCommand.doStep(field)
            val field3 = putCommand.undoStep(field2)
            val field4 = putCommand.redoStep(field3)
        }

    }

}