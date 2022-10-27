package model

class Field {

val eol = sys.props("line.separator");
def bars(width: Int, space: Int) =
  (("+" + "-" * width) + "+" + " " * space) * 3 + eol
def cells(width: Int, space: Int) =
  (("|" + " " * width) + "|" + " " * space) * 3 + eol
def playfield(width: Int = 5, length: Int = 3, space: Int = 5) =
  (bars(width, space) + (cells(width, space) * length)) * 3 + bars(width, space)

def quadbar(width: Int, space: Int) = " " * space + "+" + ("-" * width) + "+" + eol
def quadcell(width: Int, space: Int) = " " * space + "|" + (" " * width) + "|" + eol
def quadrat(width: Int, length: Int, space: Int) = (quadbar(width, space) + (quadcell(width, space) * length)) + quadbar(width, space)

def mesh(seperator: Int = 60, quadwidth: Int = 12, quadlength: Int = 6, quadspace: Int = 40,
  cellWidth: Int = 5, celllength: Int = 3, cellspace: Int = 5) =
  quadrat(quadwidth, quadlength, quadspace) + eol + playfield(cellWidth, celllength, cellspace)
  + eol + "-" * seperator + eol * 2
  + playfield(cellWidth, celllength, cellspace) + eol + quadrat(quadwidth, quadlength, quadspace)

}