package de.htwg.se.astragaloi.model.fieldComponent

import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Dice


trait FieldInterface {

    def put(number: Dice, matrix: Int, col: Int, undo: Int, oldValues: Vector[Int]): FieldInterface
    def putPoint(matrix: Int, col: Int): FieldInterface
    def putSlot(number: Dice, slot: Int): FieldInterface
    def colcheck(matrix: Int, col: Int): Int
    def checkFinish(matrix: Int): Boolean
    def chooseWinner: Int
    def slot(matrix: Int): Dice
    def col(matrix: Int, col: Int): Vector[Dice]
    def points(matrix: Int): Vector[Int]
    def clear: FieldInterface
}
