package de.htwg.se.astragaloi
package model

case class PlayField[T](matrixes: Vector[Vector[Vector[T]]]):

    def this(size: Int, filling: T) = this(Vector.tabulate(2, size, size) { (matrix, row, col) => filling })
    //val size: Int = 2*(matrixes.size*matrixes.size)
    val colsize: Int = matrixes(0)(0).size
    def cell(matrix: Int, row: Int, col: Int): T = matrixes(matrix)(row)(col) // z.B: cell(0)(1)(2) ==> Zugriff auf Zelle in Matrix 1, Zeile 2 Spalte 3
    def row(matrix: Int, row: Int) = matrixes(matrix)(row)
    def col(matrix: Int, col: Int): Vector[T] =
        val range = Range( 0 , colsize )
        var ret = Vector[T]()
        for (i <- range) {
            ret = ret :+ cell(matrix, i, col)
        }
        ret
        // Vector(matrixes(matrix)(0)(col), matrixes(matrix)(1)(col), matrixes(matrix)(2)(col))
    def fill(filling: T): PlayField[T] = copy(Vector.tabulate(2, colsize, colsize) { (matrix, row, col) => filling })
    def replaceCell(matrix: Int, row: Int, col: Int, value: T): PlayField[T] = copy(matrixes.updated(matrix, matrixes(matrix).updated(row, matrixes(matrix)(row).updated(col, value))))
    def insertValue(matrix: Int, x: Int, value: T): PlayField[T] =
        val modCol = col(matrix, x)
        val rowIndx = modCol.lastIndexWhere(a => a.equals(Dice.Empty))
        if (rowIndx == - 1)  { // no empty cell in col
            println("Insert somewhere else!")
            this
        } else {
            replaceCell(matrix, rowIndx, x, value)
        }
    //def formatCol(matrix: Int, x: Int): PlayField[T] =
    /*
    def destroyValue(matrix: Int, x: Int, value: T): PlayField[T] =
        val enemyMatrix = 1 - matrix
        var enemyCol = col(enemyMatrix, x)
        var range = Range( 0 , colsize )
        if (enemyCol.contains(value)) {
            enemyCol = enemyCol :- value
        }

        var temp = Vector[PlayField[T]]()
        for (i <- range)
            if (cell(enemyMatrix, i, enemyCol).equals(value))
                temp = temp :+ replaceCell(enemyMatrix, i, enemyCol, Dice.Empty)

        temp.replaceCell()
        */
