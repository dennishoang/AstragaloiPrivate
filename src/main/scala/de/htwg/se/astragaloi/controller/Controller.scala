package de.htwg.se.astragaloi
package controller

import model.Field
import model.Dice
import model.Field
import util.Observable

case class Controller(var field: Field) extends Observable:

    def put(number: Dice, matrix: Int, x: Int, y: Int): Unit =
        field = field.put(number, matrix, x, y)
        notifyObservers

    def putSlot(number: Dice, slot: Int): Unit =
        field = field.putSlot(number, slot)
        notifyObservers

    def changePlayer(playerID: Int): Int = 1 - playerID

    override def toString: String = field.toString

