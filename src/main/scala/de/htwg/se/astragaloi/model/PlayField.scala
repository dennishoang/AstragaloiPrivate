package model

case class PlayField[T](matrixes: Vector[Vector[Vector[T]]]):
    def this(size: Int, filling: T) = this(Vector.tabulate(2, size, size) { (matrix, row, col) => filling })
    val size: Int = 2*(matrixes.size*matrixes.size)
    def cell(matrix: Int, row: Int, col: Int): T = matrixes(matrix)(row)(col) // z.B: rows(3)(1): Zugriff auf erste Zelle Spalte 3
    def row(matrix: Int, row: Int) = matrixes(matrix)(row)
    def fill(filling: T): PlayField[T] = copy(Vector.tabulate(2, size, size) { (matrix, row, col) => filling })
    def replaceCell(matrix: Int, row: Int, col: Int, cell: T): PlayField[T] = copy(matrixes.updated(matrix, matrixes(matrix).updated(row, matrixes(matrix)(row).updated(col, cell))))