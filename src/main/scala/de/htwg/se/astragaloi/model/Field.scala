package de.htwg.se.astragaloi
package model

import model.PlayField
import model.DiceSlot
import model.Dice

case class Field(playfield: PlayField[Dice], diceslot: DiceSlot[Dice]) {

def this(matrix_size: Int, diceslot_size: Int = 2, filling: Dice) =
  this(new PlayField(matrix_size, filling), new DiceSlot(diceslot_size, filling))

val size = playfield.size
val eol = sys.props("line.separator");
def bars(width: Int, space: Int) = (("+" + "-" * width) + "+" + " " * space) * 3 + eol
def cells(matrix: Int, row: Int, width: Int, space: Int) =
  playfield.row(matrix, row).map(_.toString).map("|" + " " * ((width - 1) / 2) + _ + " " * ((width - 1) / 2) + "|").mkString(" " * space) + eol
def playerfield(matrix: Int, width: Int, length: Int, space: Int) =
  (bars(width, space) + (cells(matrix, 0, width, space) * length)) + bars(width, space)
  + (cells(matrix, 1, width, space) * length) + bars(width, space) + cells(matrix, 2, width, space)
  + bars(width, space)

def quadbar(width: Int, space: Int) = " " * space + "+" + ("-" * width) + "+" + eol
def quadcell(slot: Int, width: Int, space: Int) =
  " " * space + "|" + " " * ((width - 1) / 2) + diceslot.cell(slot).toString + " " * ((width - 1) / 2) + "|" + eol
def quadrat(slot: Int, width: Int, length: Int, space: Int) = (quadbar(width, space) + (quadcell(slot, width, space) * length)) + quadbar(width, space)

def mesh(seperator: Int = 25, quadwidth: Int = 5, quadlength: Int = 1, quadspace: Int = 15,
  cellWidth: Int = 3, celllength: Int = 1, cellspace: Int = 1) =
  quadrat(0, quadwidth, quadlength, quadspace) + eol + playerfield(0, cellWidth, celllength, cellspace)
  + eol + "-" * seperator + eol * 2
  + playerfield(1, cellWidth, celllength, cellspace) + eol + quadrat(1, quadwidth, quadlength, quadspace)


override def toString = mesh()
def put(number: Dice, matrix: Int, x: Int, y: Int) =
  copy(playfield.replaceCell(matrix, x, y, number), diceslot)
def putSlot(number: Dice, slot: Int) =
  copy(playfield, diceslot.replace(slot, number))

}
