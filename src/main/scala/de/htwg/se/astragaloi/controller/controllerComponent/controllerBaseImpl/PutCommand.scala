package de.htwg.se.astragaloi.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.astragaloi.model.fieldComponent.FieldInterface
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Move
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Dice
import de.htwg.se.astragaloi.util.Command
import de.htwg.se.astragaloi.util.UndoManager


class PutCommand(move: Move) extends Command[FieldInterface]:

    var delValueIndx = Vector[Int]()

    def increaseDelValue(field: FieldInterface): Vector[Int] =
        val enemyCol = field.col(1 - move.matrix, move.x)
        var range = Range( 0 , enemyCol.size )
        var tmp = Vector[Int]()
        for (i <- range)
            if (enemyCol(i).equals(move.dice))
                tmp = tmp :+ i
        tmp


    override def doStep(field: FieldInterface): FieldInterface =
        delValueIndx = increaseDelValue(field)
        var tmp = field.put(move.dice, move.matrix, move.x, 0, delValueIndx)
        tmp = tmp.putPoint(move.matrix, move.x)
        tmp = tmp.putSlot(Dice.Empty, move.matrix)
        tmp = tmp.putSlot(move.nextDice, 1 - move.matrix)
        tmp


    override def undoStep(field: FieldInterface): FieldInterface =
        var tmp = field.put(move.dice, move.matrix, move.x, 1, delValueIndx) // 1 for undo
        tmp = tmp.putPoint(move.matrix, move.x)
        tmp = tmp.putSlot(move.dice, move.matrix)
        tmp = tmp.putSlot(Dice.Empty, 1 - move.matrix)
        delValueIndx = Vector[Int]() // after undo delValue not needed anymore
        tmp

    override def redoStep(field: FieldInterface): FieldInterface =
        delValueIndx = increaseDelValue(field)
        var tmp = field.put(move.dice, move.matrix, move.x, 0, delValueIndx)
        tmp = tmp.putPoint(move.matrix, move.x)
        tmp = tmp.putSlot(Dice.Empty, move.matrix)
        tmp = tmp.putSlot(move.nextDice, 1 - move.matrix)
        tmp


