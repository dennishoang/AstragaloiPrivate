package de.htwg.se.astragaloi
package controller

import model.Field
import model.Dice
import model.Field
import model.Move
import util.Observable
import util.UndoManager
import util.Event

import model.PointSlot

import scala.util.Random
import scala.io.StdIn.readLine


case class Controller(var field: Field, var player: Int) extends Observable:

    val undoManager = new UndoManager[Field]

    def clear =
        field = field.clear
        player = Random.nextInt(2)

    def startGame(move: Move) =
        field = putDiceslot(move)
        notifyObservers(Event.Move)

    def Publish(doThis: Move => Field, move: Move) =
        // doThis is a function which takes a Move and returns a field (put / putSlot)
        field = doThis(move)
        val slot = new Move(rollDice, 1 - move.matrix, 0, 0)
        field = putDiceslot(slot)
        changePlayer
        notifyObservers(Event.Move)

    def Publish(doThis: => Field, last: Int): Unit = // for undo and redo
        field = doThis
        if (last == 1)
            notifyObservers(Event.Move)

    def quit: Unit = notifyObservers(Event.Quit)

    def getPoints(matrix: Int): Vector[Int] =
        field.getPoints(matrix)

    def getCol(matrix: Int, col: Int): Vector[Dice] =
        field.getCol(matrix, col)

    def getSlot(matrix: Int): Dice = field.getSlot(matrix)

    def put(move: Move): Field = undoManager.doStep(field, PutCommand(move))

    def undo: Field = undoManager.undoStep(field)

    def redo: Field = undoManager.redoStep(field)

    def finish: Field = field

    def putDiceslot(move: Move): Field =
        field.putSlot(move.dice, move.matrix)

    def changePlayer =
        player = 1 - player

    def rollDice: Dice = Dice.values(Random.nextInt(Dice.values.size - 1))

    def checkColPublish(matrix: Int, col: Int): Int =
        field.colcheck(matrix, col)

    def checkFinish(matrix: Int): Boolean =
        field.checkFinish(matrix)

    def chooseWinner: Int =
        field.chooseWinner

    override def toString: String = field.toString