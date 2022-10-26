package model

class Field {

val eol = sys.props("line.separator");
def bars(cellWidth: Int, cellspace: Int, space: Int) =
  " " * space + (("+" + "-" * cellWidth) + "+" + " " * cellspace) * 3 + eol
def cells(cellWidth: Int, cellspace: Int, space: Int) =
  " " * space + (("|" + " " * cellWidth) + "|" + " " * cellspace) * 3 + eol
def playfield(cellWidth: Int = 5, cellspace: Int = 5, space: Int = 20, celllength: Int = 3) =
  (bars(cellWidth, cellspace, space) + (cells(cellWidth, cellspace, space) * celllength)) * 3 + bars(cellWidth, cellspace, space)

def quadbar(width: Int, space: Int) = " " * space + "+" + ("-" * width) + "+" + eol
def quadcell(width: Int, space: Int) = " " * space + "|" + (" " * width) + "|" + eol
def quadrat(width: Int, length: Int, space: Int) = (quadbar(width, space) + (quadcell(width, space) * length)) + quadbar(width, space)

def spacer(space: Int, quadwidth: Int, cellspace: Int, cellwidth: Int) =
  " " * ((space - quadwidth + 2) + space + 2 * cellspace + 3 * cellwidth + 6)

def mesh(separator: Int = 80, quadwidth: Int = 12, quadlength: Int = 6, quadspace_1: Int = 60, quadspace_2: Int = 0) =
  quadrat(quadwidth, quadlength, quadspace_1) + eol + playfield() + eol + "-" * separator + eol * 2 + playfield() + eol + quadrat(quadwidth, quadlength, quadspace_2)

def mesh2(separator: Int = 80, quadwidth: Int = 12, quadlength: Int = 6, quadspace_1: Int = 60, quadspace_2: Int = 0) =
  quadrat(quadwidth, quadlength, 0) + spacer() + eol + playfield() + eol + "-" * separator + eol * 2 + playfield() + eol + quadrat(quadwidth, quadlength, quadspace_2)

//val mesh = mesh()

}
