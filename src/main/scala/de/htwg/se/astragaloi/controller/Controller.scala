package de.htwg.se.astragaloi
package controller

import model.Field
import model.Dice
import model.Field
import model.Move
import util.Observable

case class Controller(var field: Field) extends Observable:

    def Publish(doThis: Move => Field, move: Move): Unit =
        // doThis is a function which takes a Move and returns a field (put / putSlot)
        field = doThis(move)
        notifyObservers

    def put(move: Move): Field =
        field.put(move.dice, move.matrix, move.x, move.y)

    def putSlot(move: Move): Field =
        field.putSlot(move.dice, move.matrix)

    def changePlayer(playerID: Int): Int = 1 - playerID

    override def toString: String = field.toString
