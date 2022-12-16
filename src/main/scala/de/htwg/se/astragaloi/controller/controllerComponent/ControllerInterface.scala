package de.htwg.se.astragaloi.controller.controllerComponent

import de.htwg.se.astragaloi.util.Observable
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Move
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Dice
import de.htwg.se.astragaloi.model.fieldComponent.FieldInterface


trait ControllerInterface[T] extends Observable{

    def clear: Unit
    def startGame(move: Move): Unit
    def publish(doThis: Move => FieldInterface[T], move: Move): Unit
    def publish(doThis: => FieldInterface[T]): Unit
    def quit: Unit
    def points(matrix: Int): Vector[Int]
    def col(matrix: Int, col: Int): Vector[Dice]
    def slot(matrix: Int): Dice
    def put(move: Move): FieldInterface[T]
    def undo: FieldInterface[T]
    def redo: FieldInterface[T]
    def finish: FieldInterface[T]
    def putDiceslot(move: Move): FieldInterface[T]
    def putNextDice(move: Move): FieldInterface[T]
    def changePlayer: Unit
    def rollDice: Dice
    def checkColPublish(matrix: Int, col: Int): Int
    def checkFinish(matrix: Int): Boolean
    def chooseWinner: Int
    def toString: String
    def player: Int
}
