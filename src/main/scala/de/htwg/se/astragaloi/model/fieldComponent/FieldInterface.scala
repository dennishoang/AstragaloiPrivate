package de.htwg.se.astragaloi.model.fieldComponent


trait FieldInterface[T] {

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
