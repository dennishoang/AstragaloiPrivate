
package de.htwg.se.astragaloi
package controller

import model.Field
import model.Move
import model.Dice
import util.Command
import util.UndoManager

class PutCommand(move: Move) extends Command[Field]:
    override def doStep(field: Field): Field =
        var tmp = field.put(move.dice, move.matrix, move.x, 0)
        tmp = tmp.putPoint(move.matrix, move.x)
        tmp.putSlot(Dice.Empty, move.matrix)


    override def undoStep(field: Field): Field =
        var tmp = field.put(move.dice, move.matrix, move.x, 1) // 1 for undo
        tmp = tmp.putPoint(move.matrix, move.x)
        tmp = tmp.putSlot(move.dice, move.matrix)
        tmp.putSlot(Dice.Empty, 1 - move.matrix)

    override def redoStep(field: Field): Field =
        field.put(move.dice, move.matrix, move.x, 0)

