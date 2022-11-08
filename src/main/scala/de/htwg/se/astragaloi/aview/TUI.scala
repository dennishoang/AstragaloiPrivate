package de.htwg.se.astragaloi
package aview

import controller.Controller
import model.Dice
import scala.io.StdIn.readLine
import util.Observer


case class TUI(controller: Controller) extends Observer:
    controller.add(this)
    def run =
        println(controller.field.toString)
        getInputAndPrintLoop()

    override def update = println(controller.field.toString)

    def getInputAndPrintLoop(): Unit =
        val input = readLine
        input match
            case "q" => None
            case _ => {
                val chars = input.toCharArray
                val dice = chars(0) match
                    case '1' => Dice.ONE
                    case '2' => Dice.TWO
                    case '3' => Dice.THREE
                    case '4' => Dice.FOUR
                    case '5' => Dice.FIVE
                    case '6' => Dice.SIX
                    case _   => Dice.Empty
                val matrix = chars(1).toString.toInt
                val x = chars(2).toString.toInt
                val y = chars(3).toString.toInt
                controller.put(dice, matrix, x, y)
                controller.putSlot(dice, matrix)
                getInputAndPrintLoop()
            }
