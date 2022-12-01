package de.htwg.se.astragaloi
package controller

import model.Field
import model.Dice
import model.Field
import model.Move
import util.Observable
import util.UndoManager

import model.PointSlot

import scala.util.Random


case class Controller(var field: Field) extends Observable:

    val undoManager = new UndoManager[Field]

    def Publish(doThis: Move => Field, move: Move, last: Int) =
        // doThis is a function which takes a Move and returns a field (put / putSlot)
        field = doThis(move)
        if (last == 1)
            notifyObservers
    def Publish(doThis: => Field) =
        field = doThis

    def put(move: Move): Field = undoManager.doStep(field, PutCommand(move))
        //field.put(move.dice, move.matrix, move.x)
    def undo: Field = undoManager.undoStep(field)
    def redo: Field = undoManager.redoStep(field)

    def putDiceslot(move: Move): Field =
        field.putSlot(move.dice, move.matrix)

    def changePlayer(playerID: Int): Int = 1 - playerID

    def rollDice(): Dice = Dice.values(Random.nextInt(Dice.values.size - 1))

    def checkColPublish(matrix: Int, col: Int): Int =
        field.colcheck(matrix, col)



    override def toString: String = field.toString
