package de.htwg.se.astragaloi
package model

import scala.util.Random

enum Dice(stringRepresentation: String):
    override def toString = stringRepresentation
    case ONE extends Dice("1")
    case TWO extends Dice("2")
    case THREE extends Dice("3")
    case FOUR extends Dice("4")
    case FIVE extends Dice("5")
    case SIX extends Dice("6")
    case Empty extends Dice(" ")

object Dice:
    def random: Dice = Dice.values(Random.nextInt(Dice.values.size - 1))

