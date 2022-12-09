
package de.htwg.se.astragaloi
package controller

import model.Field
import model.Move
import model.Dice
import util.Command
import util.UndoManager

import scala.util.Random

class PutCommand(move: Move) extends Command[Field]:

    var delValueIndx = Vector[Int]()

    def increaseDelValue(field: Field): Vector[Int] =
        val enemyCol = field.playfield.col(1 - move.matrix, move.x)
        var range = Range( 0 , enemyCol.size )
        var tmp = Vector[Int]()
        for (i <- range)
            if (enemyCol(i).equals(move.dice))
                tmp = tmp :+ i
        tmp


    override def doStep(field: Field): Field =
        delValueIndx = increaseDelValue(field)
        var tmp = field.put(move.dice, move.matrix, move.x, 0, delValueIndx)
        tmp = tmp.putPoint(move.matrix, move.x)
        tmp = tmp.putSlot(Dice.Empty, move.matrix)
        tmp = tmp.putSlot(move.nextDice, 1 - move.matrix)
        tmp


    override def undoStep(field: Field): Field =
        var tmp = field.put(move.dice, move.matrix, move.x, 1, delValueIndx) // 1 for undo
        tmp = tmp.putPoint(move.matrix, move.x)
        tmp = tmp.putSlot(move.dice, move.matrix)
        tmp = tmp.putSlot(Dice.Empty, 1 - move.matrix)
        delValueIndx = Vector[Int]() // after undo delValue not needed anymore
        tmp

    override def redoStep(field: Field): Field =
        delValueIndx = increaseDelValue(field)
        var tmp = field.put(move.dice, move.matrix, move.x, 0, delValueIndx)
        tmp = tmp.putPoint(move.matrix, move.x)
        tmp = tmp.putSlot(Dice.Empty, move.matrix)
        tmp = tmp.putSlot(move.nextDice, 1 - move.matrix)
        tmp


