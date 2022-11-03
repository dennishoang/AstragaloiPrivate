package model

//import model.Matrix
import model.PlayField
import model.Dice

case class Field2(playfield: PlayField[Dice]) {

def this(size: Int, filling: Dice) =
    this(new PlayField(size, filling))

val size = playfield.size
val eol = sys.props("line.separator");
def bars(width: Int, space: Int) =
  (("+" + "-" * width) + "+" + " " * space) * 3 + eol
def cells(matrix: Int, row: Int, width: Int, space: Int) =
  playfield.row(matrix, row).map(_.toString).map("|" + " " * ((width - 1) / 2) + _ + " " * ((width - 1) / 2)).mkString("|" + " " * space) + eol
def playerfield(matrix: Int, width: Int, length: Int, space: Int) =
  (bars(width, space) + (cells(matrix, 0, width, space) * length)) + bars(width, space)
  + (cells(matrix, 1, width, space) * length) + bars(width, space) + cells(matrix, 2, width, space)
  + bars(width, space)

def quadbar(width: Int, space: Int) = " " * space + "+" + ("-" * width) + "+" + eol
def quadcell(width: Int, space: Int) = " " * space + "|" + (" " * width) + "|" + eol
def quadrat(width: Int, length: Int, space: Int) = (quadbar(width, space) + (quadcell(width, space) * length)) + quadbar(width, space)

def mesh(seperator: Int = 25, quadwidth: Int = 4, quadlength: Int = 2, quadspace: Int = 15,
  cellWidth: Int = 2, celllength: Int = 1, cellspace: Int = 1) =
  quadrat(quadwidth, quadlength, quadspace) + eol + playerfield(0, cellWidth, celllength, cellspace)
  + eol + "-" * seperator + eol * 2
  + playerfield(1, cellWidth, celllength, cellspace) + eol + quadrat(quadwidth, quadlength, quadspace)


override def toString = mesh()
def put(number: Dice, matrix: Int, x: Int, y: Int) = copy(playfield.replaceCell(matrix, x, y, number))
}
