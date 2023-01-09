package de.htwg.se.astragaloi.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.astragaloi.controller.controllerComponent.ControllerInterface
import de.htwg.se.astragaloi.model.fieldComponent.FieldInterface
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Dice
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Move
import de.htwg.se.astragaloi.util.Observable
import de.htwg.se.astragaloi.util.UndoManager
import de.htwg.se.astragaloi.util.Event

import de.htwg.se.astragaloi.modules.AstragaloiConfig

import de.htwg.se.astragaloi.model.fileIoComponent.FileIOInterface
import de.htwg.se.astragaloi.model.fileIoComponent.fileIoXmlImpl.FileIO


import scala.util.Random
import scala.io.StdIn.readLine


class Controller(using var field: FieldInterface) extends ControllerInterface:

    val undoManager = new UndoManager[FieldInterface]
    var playerID = Random.nextInt(2)

    val file: FileIOInterface = new FileIO

    def clear: Unit =
        field = field.clear
        playerID = Random.nextInt(2)

    def startGame(move: Move): Unit =
        field = putDiceslot(move)
        notifyObservers(Event.Move)

    def publish(doThis: Move => FieldInterface, move: Move): Unit =
        // doThis is a function which takes a Move and returns a field (put / putSlot)
        field = doThis(move)
        changePlayer
        notifyObservers(Event.Move)

    def publish(doThis: => FieldInterface): Unit = // for undo and redo
        field = doThis
        changePlayer
        notifyObservers(Event.Move)

    def quit: Unit = notifyObservers(Event.Quit)

    def points(matrix: Int): Vector[Int] =
        field.points(matrix)

    def col(matrix: Int, col: Int): Vector[Dice] =
        field.col(matrix, col)

    def slot(matrix: Int): Dice = field.slot(matrix)

    def put(move: Move): FieldInterface =
        val step = new Move(move.dice, move.matrix, move.x, rollDice)
        undoManager.doStep(field, PutCommand(step))

    def undo: FieldInterface = undoManager.undoStep(field)

    def redo: FieldInterface = undoManager.redoStep(field)

    def finish: FieldInterface = field

    def putDiceslot(move: Move): FieldInterface =
        field.putSlot(move.dice, move.matrix)

    def putNextDice(move: Move): FieldInterface =
        field.putSlot(move.nextDice, 1 - move.matrix)

    def player: Int =
        playerID

    def changePlayer: Unit =
        playerID = 1 - playerID

    def rollDice: Dice = Dice.values(Random.nextInt(Dice.values.size - 1))

    def checkColPublish(matrix: Int, col: Int): Int =
        field.colcheck(matrix, col)

    def checkFinish(matrix: Int): Boolean =
        field.checkFinish(matrix)

    def chooseWinner: Int =
        field.chooseWinner

    override def toString: String = field.toString

    def save: Unit = {
        file.save(field)
    }

    def load: Unit = {
        file.load
    }

