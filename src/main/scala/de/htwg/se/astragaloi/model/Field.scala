package model

class Field {

val eol = sys.props("line.separator");
def bars(width: Int, space: Int) =
  (("+" + "-" * width) + "+" + " " * space) * 3 + eol
def cells(width: Int, space: Int) =
  (("|" + " " * width) + "|" + " " * space) * 3 + eol
def playfield(width: Int, length: Int, space: Int) =
  (bars(width, space) + (cells(width, space) * length)) * 3 + bars(width, space)

def quadbar(width: Int, space: Int) = " " * space + "+" + ("-" * width) + "+" + eol
def quadcell(width: Int, space: Int) = " " * space + "|" + (" " * width) + "|" + eol
def quadrat(width: Int, length: Int, space: Int) = (quadbar(width, space) + (quadcell(width, space) * length)) + quadbar(width, space)

def mesh(seperator: Int = 25, quadwidth: Int = 4, quadlength: Int = 2, quadspace: Int = 15,
  cellWidth: Int = 2, celllength: Int = 1, cellspace: Int = 1) =
  quadrat(quadwidth, quadlength, quadspace) + eol + playfield(cellWidth, celllength, cellspace)
  + eol + "-" * seperator + eol * 2
  + playfield(cellWidth, celllength, cellspace) + eol + quadrat(quadwidth, quadlength, quadspace)




}