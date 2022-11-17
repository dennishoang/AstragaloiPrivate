package de.htwg.se.astragaloi
package aview

import controller.Controller
import model.Dice
import model.DiceRoll
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
        val random = DiceRoll.random
        val roll = Move(random, matrix,  0, 0)
        val clear = Move(Dice.Empty, matrix, 0, 0)
        controller.Publish(controller.putDiceslot, roll)
        if (auto_input == "")
            analyseInput(readLine, random, matrix) match
                case None       =>
                case Some(playerAction) => {
                    controller.Publish(controller.putPlayfield, playerAction)
                    controller.Publish(controller.putDiceslot, clear)
                    getInputAndPrintLoop(controller.changePlayer(playerID), auto_input)
                }
        else
            analyseInput(auto_input, random, matrix)


    def analyseInput(input: String, dice: Dice, matrix: Int): Option[Move] =
        input match
            case "q" => None
            case _ => {
                val chars = input.toCharArray
                val x = chars(0).toString.toInt
                val y = chars(1).toString.toInt
                Some(Move(dice, matrix, x, y))
            }




    /*
    def getInputAndPrintLoop(playerID: Int, value: Int, row: Int, col: Int): Unit =
            val matrix = playerID
            controller.putSlot(random, matrix)
            controller.put(random, matrix, row, col)
            controller.putSlot(Dice.Empty, matrix)
    */