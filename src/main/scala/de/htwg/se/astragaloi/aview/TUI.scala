package de.htwg.se.astragaloi
package aview

import controller.Controller
import model.Dice
import model.Move
import scala.io.StdIn.readLine
//import scala.io.StdIn.readInt
import util.Observer
import scala.util.Random


case class TUI(controller: Controller) extends Observer:
    controller.add(this)
    def run =
        // println(controller.field.toString)
        val playerID = Random.nextInt(2)
        getInputAndPrintLoop(playerID, "")



    override def update = println(controller.field.toString)

    def getInputAndPrintLoop(playerID: Int, auto_input: String): Unit =

        val matrix = playerID
        val random = controller.rollDice()
        val roll = Move(random, matrix,  0)
        controller.Publish(controller.putDiceslot, roll, 1)

        //var input = ""
        //if (!auto_input.equals(""))
        //    input = auto_input
        //else
        //val input = readLine

        analyseInput(random, matrix) match
            case None       =>
            case Some(playerAction) => {
                controller.Publish(controller.putPlayfield, playerAction, 0)
                controller.Publish(controller.putPoints, playerAction, 0)
                if (auto_input == "")
                    getInputAndPrintLoop(controller.changePlayer(playerID), auto_input)
            }



    def analyseInput(dice: Dice, matrix: Int): Option[Move] =
        val input = readLine()
        input match
            case "q" => None
            case _ => {
                //val chars = input.toCharArray
                //val col = chars(0).toString.toInt
                val col = input.toInt
                if (controller.checkColPublish(matrix, col) == -1)
                    println("Spalte ist voll!")
                    analyseInput(dice, matrix)
                else
                    Some(Move(dice, matrix, col))
            }

