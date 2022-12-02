package de.htwg.se.astragaloi
package model

import model.PlayField

// Template Method pattern

abstract class Insert[T] {

    // fun1: PlayField[T] => PlayField[T], fun2: PlayField[T] => PlayField
    def insertValue(playfield: PlayField[T], matrix: Int, x: Int, value: T, clear: T, undo: Int, oldIndexes: Vector[Int]): PlayField[T] =
        val modCol = playfield.col(matrix, x)
        if (undo == 1) {
            val rowIndx = modCol.indexWhere(a => a.equals(value))
            var tmp = undestroyValue(matrix, x, value, oldIndexes)
            tmp.replaceCell(matrix, rowIndx, x, clear) // delete value out of own matrix
        } else {
            val rowIndx = modCol.lastIndexWhere(a => a.equals(clear))
            var tmp = destroyValue(matrix, x, value, clear)
            tmp.replaceCell(matrix, rowIndx, x, value)
        }

    def undestroyValue(matrix: Int, x: Int, value: T, oldIndexes: Vector[Int]): PlayField[T]

    def destroyValue(matrix: Int, x: Int, value: T, clear: T): PlayField[T]

    def replaceCell(matrix: Int, row: Int, x: Int, value: T): PlayField[T]
}
