package model

case class Matrix[T](rows: Vector[Vector[T]], player: Int):
    def this(size: Int, filling: T, player: Int) = this(Vector.tabulate(size, size) { (row, col) => filling }, player)
    val size: Int = rows.size
    def cell(row: Int, col: Int): T = rows(row)(col) // z.B: rows(3)(1): Zugriff auf erste Zelle Spalte 3
    def row(row: Int) = rows(row)
    def fill(filling: T): Matrix[T] = copy(Vector.tabulate(size, size) { (row, col) => filling })
    def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))