package de.htwg.se.astragaloi
package model

import model.Dice
import scala.util.Random

object DiceRoll:
    def random: Dice = Dice.values(Random.nextInt(Dice.values.size - 1))
