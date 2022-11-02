package model

import model.Matrix
import model.Dice

case class Field2(matrix_1: Matrix[Dice], matrix_2: Matrix[Dice]) {

def this(size: Int, filling: Dice) =
    this(new Matrix(size, filling, 1), new Matrix(size, filling, 2))

val size = matrix_1.size + matrix_2.size
val eol = sys.props("line.separator");
def bars(width: Int, space: Int) =
  (("+" + "-" * width) + "+" + " " * space) * 3 + eol
def cells(row: Int, width: Int, space: Int, matrix: Matrix[Dice]) =
    matrix.row(row).map(_.toString).map("|" + " " * ((width - 1) / 2) + _ + " " * ((width - 1) / 2)).mkString("|" + " " * space) + eol
def playfield(width: Int, length: Int, space: Int, matrix: Matrix[Dice]) =
  (bars(width, space) + (cells(0, width, space, matrix) * length)) + bars(width, space)
  + (cells(1, width, space, matrix) * length) + bars(width, space) + cells(2, width, space, matrix)
  + bars(width, space)

def quadbar(width: Int, space: Int) = " " * space + "+" + ("-" * width) + "+" + eol
def quadcell(width: Int, space: Int) = " " * space + "|" + (" " * width) + "|" + eol
def quadrat(width: Int, length: Int, space: Int) = (quadbar(width, space) + (quadcell(width, space) * length)) + quadbar(width, space)

def mesh(seperator: Int = 25, quadwidth: Int = 4, quadlength: Int = 2, quadspace: Int = 15,
  cellWidth: Int = 2, celllength: Int = 1, cellspace: Int = 1) =
  quadrat(quadwidth, quadlength, quadspace) + eol + playfield(cellWidth, celllength, cellspace, matrix_1)
  + eol + "-" * seperator + eol * 2
  + playfield(cellWidth, celllength, cellspace, matrix_2) + eol + quadrat(quadwidth, quadlength, quadspace)


override def toString = mesh()
def put(number: Dice, y: Int, x: Int, matrix: Matrix[Dice]) = copy(matrix.replaceCell(y, x, number))
}
