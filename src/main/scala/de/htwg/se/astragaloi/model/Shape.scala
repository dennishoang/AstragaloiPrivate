package de.htwg.se.astragaloi
package model

import model.dataComponent.PlayFieldInterface
import model.dataComponent.DiceSlotInterface
import model.dataComponent.PointSlotInterface

trait Shape:
    val eol = "\n"

    def create(): String


case class CellMatrix[T](playfield: PlayFieldInterface[T], width: Int, space: Int, length: Int, size: Int, matrix: Int) extends Shape:
    override def create(): String =
    	(0 until size).map(cells(_) * length).mkString(bars(),bars(),bars())

    def bars(): String =
        (("+" + "-" * width) + "+" + " " * space) * 3
    def cells(row: Int): String =
        eol + playfield.row(matrix, row).map(_.toString).map("|" + " " * ((width - 1) / 2) + _ + " " * ((width - 1) / 2) + "|").mkString(" " * space) + eol


case class Quadrat[T](diceslot: DiceSlotInterface[T], width: Int, space: Int, length: Int, matrix: Int) extends Shape:
    override def create(): String =
        (bars() + (cells() * length)) + bars()

    def bars(): String =
        " " * space + "+" + ("-" * width) + "+"

    def cells(): String =
        eol + " " * space + "|" + " " * ((width - 1) / 2) + diceslot.cell(matrix).toString + " " * ((width - 1) / 2) + "|" + eol


case class PointCell[T](pointslot: PointSlotInterface[T], width: Int, space: Int, matrix: Int) extends Shape:
    override def create(): String =
        pointslot.slot(matrix).map(_.toString).map("|" + " " * ((width - 1) / 2) + _ + " " * ((width - 1) / 2) + "|").mkString(" " * space)


case class MatrixSeperator(width: Int) extends Shape:
    override def create(): String =
        "=" * width


class CompositeShape(shapes: Shape*) extends Shape:
    override def create(): String =
        val b = new StringBuilder()
        shapes.map(_.create()).mkString(eol * 2).addString(b)
        b.toString()
