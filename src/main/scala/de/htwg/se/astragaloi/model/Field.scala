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
  val eol = sys.props("line.separator");


  /*
  def bars(width: Int, space: Int) = (("+" + "-" * width) + "+" + " " * space) * 3 + eol
  def cells(matrix: Int, row: Int, width: Int, space: Int) =
    playfield.row(matrix, row).map(_.toString).map("|" + " " * ((width - 1) / 2) + _ + " " * ((width - 1) / 2) + "|").mkString(" " * space) + eol
  def playerfield(matrix: Int, width: Int, length: Int, space: Int) =
    (0 until size).map(cells(matrix,_, width, space) * length).mkString(bars(width, space),bars(width, space),bars(width, space))

  def pointcell(slot: Int, width: Int, space: Int) = pointslot.slot(slot).map(_.toString).map("|" + " " * ((width - 1) / 2) + _ + " " * ((width - 1) / 2) + "|").mkString(" " * space) + eol

  def quadbar(width: Int, space: Int) = " " * space + "+" + ("-" * width) + "+" + eol
  def quadcell(slot: Int, width: Int, space: Int) =
    " " * space + "|" + " " * ((width - 1) / 2) + diceslot.cell(slot).toString + " " * ((width - 1) / 2) + "|" + eol
  def quadrat(slot: Int, width: Int, length: Int, space: Int) =
    (quadbar(width, space) + (quadcell(slot, width, space) * length)) + quadbar(width, space)

  def mesh(seperator: Int = 25, quadwidth: Int = 5, quadlength: Int = 1, quadspace: Int = 15,
    cellWidth: Int = 3, celllength: Int = 1, cellspace: Int = 1) =
    quadrat(0, quadwidth, quadlength, quadspace) + eol + playerfield(0, cellWidth, celllength, cellspace)
    + eol + pointcell(0, cellWidth, cellspace) + "-" * seperator + eol + pointcell(1, cellWidth, cellspace) + eol
    + playerfield(1, cellWidth, celllength, cellspace) + eol + quadrat(1, quadwidth, quadlength, quadspace)
  */
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

  def put(number: Dice, matrix: Int, col: Int) =
    copy(playfield.insertValue(playfield, matrix, col, number, Dice.Empty), diceslot)
  def putSlot(number: Dice, slot: Int) =
    copy(playfield, diceslot.replace(slot, number))
  def putPoint(matrix: Int, col: Int) =
    copy(playfield, diceslot.replace(matrix, Dice.Empty), pointslot.replaceAllPoints(playfield, matrix, col)) // + pointslot.replacePoints(playfield, 1 - matrix, col)
  def colcheck(matrix: Int, col: Int): Int =
    playfield.checkCol(matrix, col)
    // checkFinish(matrix)





