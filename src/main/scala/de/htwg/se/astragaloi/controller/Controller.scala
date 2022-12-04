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

    def Publish(doThis: => Field, undo: Int) = // for undo and redo
        field = doThis
        if (undo == 1)
            notifyObservers

    def getSlot(matrix: Int): Dice = field.getSlot(matrix)

    def put(move: Move): Field = undoManager.doStep(field, PutCommand(move))

    def undo: Field = undoManager.undoStep(field)

    def redo: Field = undoManager.redoStep(field)

    def putDiceslot(move: Move): Field =
        field.putSlot(move.dice, move.matrix)

    def changePlayer(playerID: Int): Int = 1 - playerID

    def rollDice(): Dice = Dice.values(Random.nextInt(Dice.values.size - 1))

    def checkColPublish(matrix: Int, col: Int): Int =
        field.colcheck(matrix, col)

    // checks finish
    def checkFinish(matrix: Int): Boolean =
        field.checkFinish(matrix)

    // returns winner
    def chooseWinner: Int =
        field.chooseWinner

    override def toString: String = field.toString
