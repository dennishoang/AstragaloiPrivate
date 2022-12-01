
package de.htwg.se.astragaloi
package controller

import model.Field
import model.Move
import model.Dice
import util.Command
import util.UndoManager

class PutCommand(move: Move) extends Command[Field]:
    override def doStep(field: Field): Field = field.put(move.dice, move.matrix, move.x)
    override def undoStep(field: Field): Field = field.put(Dice.Empty, move.matrix, move.x)
    override def redoStep(field: Field): Field = field.put(move.dice, move.matrix, move.x)

