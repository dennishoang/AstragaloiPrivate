package de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl

import de.htwg.se.astragaloi.model.fieldComponent.FieldInterface


case class Field(playfield: PlayField[Dice], diceslot: DiceSlot[Dice], pointslot: PointSlot[Dice]) extends FieldInterface[Dice]:

  def this(matrix_size: Int, diceslot_size: Int = 2, filling: Dice, fillingpoint: Int) =
    this(new PlayField[Dice](matrix_size, filling), new DiceSlot[Dice](diceslot_size, filling), new PointSlot[Dice](diceslot_size, fillingpoint))

  val mesh = new CompositeShape(
    new Quadrat[Dice](diceslot, 5, 15, 1, 0),
    new CellMatrix[Dice](playfield, 3, 1, 1, 3, 0),
    new PointCell[Dice](pointslot, 3, 1, 0),
    new MatrixSeperator(25),
    new PointCell[Dice](pointslot, 3, 1, 1),
    new CellMatrix[Dice](playfield, 3, 1, 1, 3, 1),
    new Quadrat[Dice](diceslot, 5, 15, 1, 1)
    )

  override def toString = mesh.create()

  def put(number: Dice, matrix: Int, col: Int, undo: Int, oldValues: Vector[Int]) =
    copy(playfield.insertValue(matrix, col, number, Dice.Empty, undo, oldValues), diceslot)

  def putPoint(matrix:Int, col:Int) =
    copy(playfield, diceslot, pointslot.replaceAllPoints(playfield, matrix, col))

  def putSlot(number: Dice, slot: Int) =
    copy(playfield, diceslot.replace(slot, number))

  def colcheck(matrix: Int, col: Int): Int =
    playfield.checkCol(matrix, col)

  def checkFinish(matrix: Int): Boolean =
    playfield.checkFinish(matrix: Int)

  def chooseWinner: Int =
    pointslot.chooseWinner

  def slot(matrix: Int): Dice =
    diceslot.cell(matrix)

  def col(matrix: Int, col: Int): Vector[Dice] =
    playfield.col(matrix, col)

  def points(matrix: Int): Vector[Int] =
    pointslot.slot(matrix)

  def clear: Field =
    copy(playfield.fill(Dice.Empty), diceslot.fill(Dice.Empty), pointslot.fill(0))


