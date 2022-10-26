package model

class Field {

val eol = sys.props("line.separator");
def bars(cellWidth: Int, cellspace: Int, space: Int) =
  " " * space + (("+" + "-" * cellWidth) + "+" + " " * cellspace) * 3 + eol
def cells(cellWidth: Int, cellspace: Int, space: Int) =
  " " * space + (("|" + " " * cellWidth) + "|" + " " * cellspace) * 3 + eol
def playfield(cellWidth: Int = 5, cellspace: Int = 5, space: Int = 20) =
  (bars(cellWidth, cellspace, space) + (cells(cellWidth, cellspace, space) * 3)) * 3 + bars(cellWidth, cellspace, space)

def quadbar(width: Int, space: Int) = " " * space + "+" + ("-" * width) + "+" + eol
def quadcell(width: Int, space: Int) = " " * space + "|" + (" " * width) + "|" + eol
def quadrat(width: Int, length: Int, space: Int) = (quadbar(width, space) + (quadcell(width, space) * length)) + quadbar(width, space)

val mesh = quadrat(12, 6, 60) + eol + playfield() + eol + "-" * 80 + eol * 2 + playfield() + quadrat(12, 6, 0)

}
