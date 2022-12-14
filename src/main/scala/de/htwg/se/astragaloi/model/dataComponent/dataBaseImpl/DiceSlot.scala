package de.htwg.se.astragaloi.model.dataComponent.dataBaseImpl



import de.htwg.se.astragaloi.model.dataComponent.DiceSlotInterface

case class DiceSlot[T](slots: Vector[T]) extends DiceSlotInterface[T]:

    def this(size: Int, filling: T) = this(Vector.tabulate(size) { (slot) => filling })
    val size: Int = slots.size
    def cell(slot: Int): T = slots(slot)
    def fill(filling: T): DiceSlot[T] = copy(Vector.tabulate(size) { (slot) => filling })
    def replace(slot: Int, cell: T): DiceSlot[T] = copy(slots.updated(slot, cell))