package de.htwg.se.astragaloi.model.dataComponent


trait FieldInterface[T] {
    //Methodenaufrufe von PlayFieldInterface, DiceSlotInterface und PointSlotInterface

    def put(number: T, matrix: Int, col: Int, undo: Int, oldValues: Vector[Int]): FieldInterface[T]

    def putPoint(matrix: Int, col: Int): FieldInterface[T]

    def putSlot(number: T, slot: Int): FieldInterface[T]

    def colcheck(matrix: Int, col: Int): Int

    def checkFinish(matrix: Int): Boolean

    def chooseWinner: Int

    def slot(matrix: Int): T

    def col(matrix: Int, col: Int): Vector[T]

    def points(matrix: Int): Vector[Int]

    def clear: FieldInterface[T]
}


trait PlayFieldInterface[T] {

    def insertValue(matrix: Int, x: Int, value: T, clear: T, undo: Int, oldIndexes: Vector[Int]): PlayFieldInterface[T]

    def checkCol(matrix: Int, x: Int): Int

    def checkFinish(matrix: Int): Boolean

    def col(matrix: Int, col: Int): Vector[T]

    def fill(filling: T): PlayFieldInterface[T]
}

trait DiceSlotInterface[T] {

    def replace(slot: Int, cell: T): DiceSlotInterface[T]

    def cell(slot: Int): T

    def fill(filling: T): DiceSlotInterface[T]
}

trait PointSlotInterface[T] {

    def replaceAllPoints(playfield: PlayFieldInterface[T], matrix: Int, col: Int): PointSlotInterface[T]

    def chooseWinner: Int

    def slot(matrix: Int): Vector[Int]

    def fill(filling: Int): PointSlotInterface[T]
}
