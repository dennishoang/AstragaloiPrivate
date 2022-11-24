package de.htwg.se.astragaloi
package model

import model.PlayField

// Template Method pattern

abstract class Insert[T] {

    // fun1: PlayField[T] => PlayField[T], fun2: PlayField[T] => PlayField
    def insertValue(playfield: PlayField[T], matrix: Int, x: Int, value: T, clear: T): PlayField[T] =
        val modCol = playfield.col(matrix, x)
        val rowIndx = modCol.lastIndexWhere(a => a.equals(clear))
        var tmp = destroyValue(matrix, x, value, clear)
        tmp.replaceCell(matrix, rowIndx, x, value)

    def destroyValue(matrix: Int, x: Int, value: T, clear: T): PlayField[T]

    def replaceCell(matrix: Int, row: Int, x: Int, value: T): PlayField[T]
}
