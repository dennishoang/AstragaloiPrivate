package de.htwg.se.astragaloi
package model

case class DiceSlot[T](slots: Vector[T]):

    def this(size: Int, filling: T) = this(Vector.tabulate(size) { (slot) => filling })
    val size: Int = slots.size
    def cell(slot: Int): T = slots(slot)
    def fill(filling: T): DiceSlot[T] = copy(Vector.tabulate(size) { (slot) => filling })
    def replace(slot: Int, cell: T): DiceSlot[T] = copy(slots.updated(slot, cell))