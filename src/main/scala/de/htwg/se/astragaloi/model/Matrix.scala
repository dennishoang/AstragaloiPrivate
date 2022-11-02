package model

case class Matrix[T](cols: Vector[Vector[T]]):
    def this(size: Int, filling: T) = this(Vector.tabulate(size, size) { (col, row) => filling })
    val size: Int = cols.size
    def cell(col: Int, row: Int): T = cols(col)(row) // z.B: rows(3)(1): Zugriff auf erste Zelle Spalte 3
    def col(col: Int) = cols(col)
    def fill(filling: T): Matrix[T] = copy(Vector.tabulate(size, size) { (col, row) => filling })
    def replaceCell(col: Int, row: Int, cell: T): Matrix[T] = copy(cols.updated(col, cols(col).updated(row, cell)))