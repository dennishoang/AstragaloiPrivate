package de.htwg.se.astragaloi
package model

case class PointSlot[T](slots: Vector[Vector[T]]):
    def this(size: Int, filling: T) = this(Vector.tabulate(size, 4) { (slot, col) => filling})
    val size = slots.size
    def cell(slot: Int, col:Int): T = slots(slot)(col)
    def slot(slot: Int) = slots(slot)
    def fill(filling: T): PointSlot[T] = copy(Vector.tabulate(size, 4) { (slot, col) => filling })
    def replacePoints(slot: Int, col: Int, cell: T): PointSlot[T] = copy(slots.updated(slot, slots(slot).updated(col,cell)))
