package de.htwg.se.astragaloi
package aview

import controller.Controller
import model.Dice
import model.Move
import scala.io.StdIn.readLine
import util.Observer
import scala.util.Random


case class TUI(controller: Controller) extends Observer:
    controller.add(this)
    def run =
        println(controller.field.toString)
        val playerID = Random.nextInt(2)
        getInputAndPrintLoop(playerID, "")



    override def update = println(controller.field.toString)

    def getInputAndPrintLoop(playerID: Int, auto_input: String): Unit =

        val matrix = playerID
        val random = controller.rollDice()
        val roll = Move(random, matrix,  0, 0)
        val clear = Move(Dice.Empty, matrix, 0, 0)
        controller.Publish(controller.putDiceslot, roll)

        var input = ""
        if (auto_input != "")
            input = auto_input
        else
            input = readLine

        analyseInput(input, random, matrix) match
            case None       =>
            case Some(playerAction) => {
                controller.Publish(controller.putPlayfield, playerAction)
                controller.Publish(controller.putDiceslot, clear)
                if (auto_input == "")
                    getInputAndPrintLoop(controller.changePlayer(playerID), auto_input)
            }



    def analyseInput(input: String, dice: Dice, matrix: Int): Option[Move] =
        input match
            case "q" => None
            case _ => {
                val chars = input.toCharArray
                val x = chars(0).toString.toInt
                val y = chars(1).toString.toInt
                Some(Move(dice, matrix, x, y))
            }

