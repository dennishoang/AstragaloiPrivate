package de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl


case class PlayField[T](matrixes: Vector[Vector[Vector[T]]]):

    def this(size: Int, filling: T) = this(Vector.tabulate(2, size, size) { (matrix, row, col) => filling })
    val rowsize: Int = matrixes(0).size
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
    def fill(filling: T): PlayField[T] =
        copy(Vector.tabulate(2, colsize, colsize) { (matrix, row, col) => filling })

    def checkCol(matrix: Int, x: Int): Int =
        val modcol = col(matrix,x)
        if (!modcol.contains(Dice.Empty)) { -1 }
        else { x }


    def insertValue(matrix: Int, x: Int, value: T, clear: T, undo: Int, oldIndexes: Vector[Int]): PlayField[T] =
        val modCol = col(matrix, x)
        if (undo == 1) {
            val rowIndx = modCol.indexWhere(a => a.equals(value))
            var tmp = undestroyValue(matrix, x, value, oldIndexes)
            tmp.replaceCell(matrix, rowIndx, x, clear) // delete value out of own matrix
        } else {
            val rowIndx = modCol.lastIndexWhere(a => a.equals(clear))
            var tmp = destroyValue(matrix, x, value, clear)
            tmp.replaceCell(matrix, rowIndx, x, value)
        }

    def replaceCell(matrix: Int, row: Int, col: Int, value: T): PlayField[T] =
        copy(matrixes.updated(matrix, matrixes(matrix).updated(row, matrixes(matrix)(row).updated(col, value))))

    def undestroyValue(matrix: Int, col: Int, value: T, oldIndexes: Vector[Int]): PlayField[T] =
        val enemyMatrix = 1 - matrix
        //enemyCol = field.playfield.col(enemyMatrix, col)
        if (oldIndexes.size > 0)
            var range = Range(0, oldIndexes.size)

            var temp = copy(this.matrixes)
            for(i <- range)
                temp = temp.replaceCell(enemyMatrix, oldIndexes(i), col, value)
            temp
        else
            this

    def destroyValue(matrix: Int, x: Int, value: T, clear: T): PlayField[T] =
        val enemyMatrix = 1 - matrix

        var range = Range( 0 , colsize )
        var temp = copy(this.matrixes)

        for (i <- range)
            if (cell(enemyMatrix, i, x).equals(value))
                temp = temp.replaceCell(enemyMatrix, i, x, clear)

        var enemyCol = temp.col(enemyMatrix, x)
        var emptyVec = Vector[T]()
        var valueVec = Vector[T]()
        for (i <- range)
            if(temp.cell(enemyMatrix,i,x).equals(clear))
                emptyVec = emptyVec :+ clear
            else
                valueVec = valueVec :+ cell(enemyMatrix,i,x)
        val formatVec = emptyVec ++ valueVec
        for (i <- range)
            temp = temp.replaceCell(enemyMatrix, i, x, formatVec(i))
        temp

    def checkFinish(matrix: Int): Boolean =
        val range = Range(0, rowsize)
        var ret = true
        for(i <- range)
            if (checkCol(matrix, i) != -1) // if a col isn't filled in the given matrix
                ret = false
        ret






