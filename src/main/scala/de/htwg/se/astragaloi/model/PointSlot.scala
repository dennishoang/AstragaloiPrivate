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
        val value = calculatePoints(playfield, matrix, col,0, 0)
        val sumvalue = calculatePoints(playfield,matrix, col, 99, value)
        copy(slots.updated(matrix, slots(matrix).updated(col,value).updated(3,sumvalue)))

        //val total = calculatePoints(playfield, matrix, col, 1)
        //copy(slots.updated(matrix, slots(matrix).updated(3,total)))



    def calculatePoints(playfield: PlayField[T], matrix: Int, col: Int, algorithm: Int, value: Int): Int =

        object Algorithm {

            var strategy = if (algorithm == 0) calcCol(playfield, matrix, col) else rowPoints(matrix,col, value)

            def calcCol(playfield: PlayField[T], matrix: Int, col: Int): Int =

                val values = playfield.col(matrix, col).map(_.toString).filter(_.forall(Character.isDigit)).map(_.toInt)
                var unique = Vector(0)
                var doubles = Vector(0)
                var firstInsert = 1
                for (x <- values) {
                    if (!(unique.contains(x))) {
                        unique = unique :+ x
                    } else {
                        if (firstInsert == 1) {
                            doubles = doubles :+ x
                            doubles = doubles :+ x
                            firstInsert = 0
                        } else {
                            doubles = doubles :+ x
                        }

                    }
                }
                val numOfEqElements = doubles.size - 1
                (doubles.sum * numOfEqElements) + unique.diff(doubles).sum


                // playfield.col(matrix, col).map(_.toString).filter(_.forall(Character.isDigit)).map(_.toInt).sum

            def rowPoints(matrix: Int, col: Int, value: Int): Int  = col match
                case 0 => value + cell(matrix, 1) + cell(matrix, 2)
                case 1 => cell(matrix, 0) + value + cell(matrix, 2)
                case 2 => cell(matrix, 0) + cell(matrix, 1) + value

        }

        Algorithm.strategy





