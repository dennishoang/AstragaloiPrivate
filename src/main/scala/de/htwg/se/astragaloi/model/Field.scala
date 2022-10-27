package model

class Field {

val eol = sys.props("line.separator");
def bars(cellWidth: Int, cellspace: Int) =
  (("+" + "-" * cellWidth) + "+" + " " * cellspace) * 3 + eol
def cells(cellWidth: Int, cellspace: Int) =
  (("|" + " " * cellWidth) + "|" + " " * cellspace) * 3 + eol
def playfield(cellWidth: Int = 5, cellspace: Int = 5, celllength: Int = 3) =
  (bars(cellWidth, cellspace) + (cells(cellWidth, cellspace) * celllength)) * 3 + bars(cellWidth, cellspace)

def quadbar(width: Int, space: Int) = " " * space + "+" + ("-" * width) + "+" + eol
def quadcell(width: Int, space: Int) = " " * space + "|" + (" " * width) + "|" + eol
def quadrat(width: Int, length: Int, space: Int) = (quadbar(width, space) + (quadcell(width, space) * length)) + quadbar(width, space)

def mesh(seperator: Int = 60, quadwidth: Int = 12, quadlength: Int = 6, quadspace: Int = 40) =
  quadrat(quadwidth, quadlength, quadspace) + eol + playfield() + eol + "-" * seperator + eol * 2 + playfield() + eol + quadrat(quadwidth, quadlength, quadspace)

}