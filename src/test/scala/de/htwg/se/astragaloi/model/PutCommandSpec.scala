
package de.htwg.se.astragaloi.model

import de.htwg.se.astragaloi.model.Field
import de.htwg.se.astragaloi.model.PlayField
import de.htwg.se.astragaloi.model.Dice
import de.htwg.se.astragaloi.controller.PutCommand

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class PutCommandSpec extends AnyWordSpec {

    "A PutCommand" should {
        "undo Steps" in {
            var playfield = new PlayField[Dice](3, Dice.Empty)
            var diceslot = new DiceSlot[Dice](2, Dice.Empty)
            var pointslot = new PointSlot[Dice](2, 0)
            var field = new Field(playfield, diceslot, pointslot)
            val putCommand = new PutCommand(new Move(Dice.SIX, 0, 0, 1))
            field = putCommand.doStep(field)
            field = putCommand.undoStep(field)
            field.playfield.cell(0, 0, 0) should be (Dice.Empty)
        }
        "redo Steps" in {
            var playfield = new PlayField[Dice](3, Dice.Empty)
            var diceslot = new DiceSlot[Dice](2, Dice.Empty)
            var pointslot = new PointSlot[Dice](2, 0)
            var field = new Field(playfield, diceslot, pointslot)
            val putCommand = new PutCommand(new Move(Dice.SIX, 0, 0, 1))
            field = putCommand.doStep(field)
            field = putCommand.undoStep(field)
            field = putCommand.redoStep(field)
        }

    }

}