package de.htwg.se.astragaloi
package model

import model.PlayField

case class PointSlot[T](slots: Vector[Vector[Int]]):
    def this(size: Int, filling: Int) = this(Vector.tabulate(size, 4) { (slot, col) => filling})
    val size = slots.size
    def cell(matrix: Int, col:Int): Int = slots(matrix)(col)
    def slot(matrix: Int) = slots(matrix)
    def fill(filling: Int): PointSlot[T] = copy(Vector.tabulate(size, 4) { (slot, col) => filling })
    def replacePoints(playfield: PlayField[T], matrix: Int, col: Int): PointSlot[T] =
        val value = calculatePoints(playfield, matrix, col)
        copy(slots.updated(matrix, slots(matrix).updated(col,value)))
    def calculatePoints(playfield: PlayField[T], matrix: Int, col: Int): Int =
        // Algorithm:  Pasch: (Anzahl gleiche Zahlen in einer Spalte) * (Summe gleicher Zahlen) ==> z.B. 2 Zweier ==> 2 * ( 2 + 2 ) = 8

        val orig = playfield.col(matrix, col).map(_.toString).filter(_.forall(Character.isDigit)).map(_.toInt).toSet

        //val dist: PartialFunction[Int, Int] = { case a if a == b => a}
        val distincted = playfield.col(matrix, col).map(_.toString).filter(_.forall(Character.isDigit)).map(_.toInt).distinct.toSet

        val equalElements = orig -- distincted
        //val equalElements = orig.diff(distincted)
        val numOfEqElements = equalElements.size + 1 // (+ 1) because it just includes the duplicates
        (equalElements.sum * numOfEqElements) + orig.diff(equalElements).sum

        /*
        val orig = playfield.col(matrix, col).map(_.toString).filter(_.forall(Character.isDigit)).map(_.toInt)
        val distincted = playfield.col(matrix, col).map(_.toString).filter(_.forall(Character.isDigit)).map(_.toInt).distinct
        val equalElements = orig.diff(distincted)
        val numOfEqElements = equalElements.size // (+ 1) because it just includes the duplicates
        (equalElements.sum * numOfEqElements) + orig.diff(equalElements).sum
        */

        // Vorherige (vereinfachte) Rechnung
        // playfield.col(matrix, col).map(_.toString).filter(_.forall(Character.isDigit)).map(_.toInt).sum



