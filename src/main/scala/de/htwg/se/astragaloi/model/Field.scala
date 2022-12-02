package de.htwg.se.astragaloi
package model

import model.PlayField
import model.DiceSlot
import model.Dice
import model.PointSlot
import model.Shape


case class Field(playfield: PlayField[Dice], diceslot: DiceSlot[Dice], pointslot: PointSlot[Dice]):

  def this(matrix_size: Int, diceslot_size: Int = 2, filling: Dice, fillingpoint: Int) =
    this(new PlayField(matrix_size, filling), new DiceSlot(diceslot_size, filling), new PointSlot(diceslot_size, fillingpoint))

  val mesh = new CompositeShape(
    new Quadrat[Dice](diceslot, 5, 15, 1, 0),
    new CellMatrix[Dice](playfield, 3, 1, 1, 3, 0),
    new PointCell[Dice](pointslot, 3, 1, 0),
    new MatrixSeperator(25),
    new PointCell[Dice](pointslot, 3, 1, 1),
    new CellMatrix[Dice](playfield, 3, 1, 1, 3, 1),
    new Quadrat[Dice](diceslot, 5, 15, 1, 1)
    )

  override def toString = mesh.create()

  def put(number: Dice, matrix: Int, col: Int, undo: Int) =
    copy(playfield.insertValue(playfield, matrix, col, number, Dice.Empty, undo), diceslot)


    /*else {
      temp.copy(temp.playfield, temp.diceslot.replace(matrix, number), temp.pointslot.replaceAllPoints(temp.playfield, matrix, col))
    }*/

  def putPoint(matrix:Int, col:Int) =
    copy(playfield, diceslot, pointslot.replaceAllPoints(playfield, matrix, col))

  def putSlot(number: Dice, slot: Int) =
    copy(playfield, diceslot.replace(slot, number))

  def colcheck(matrix: Int, col: Int): Int =
    playfield.checkCol(matrix, col)
    // checkFinish(matrix)


