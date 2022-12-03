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
        getInputAndPrintLoop(playerID, 1, Move(Dice.Empty, 0, 0, 0))



    override def update = println(controller.field.toString)

    def getInputAndPrintLoop(playerID: Int, continue: Int, oldMove: Move): Unit =

        val matrix = playerID
        var move = oldMove


        if (continue == 1) {
            var random = controller.rollDice()
            move = Move(random, matrix,  0, 0)
            controller.Publish(controller.putDiceslot, move, 1)
        }


        //var input = ""
        //if (!auto_input.equals(""))
        //    input = auto_input
        //else
        //val input = readLine

        analyseInput(move) match
            case None       =>
            case Some(playerAction) => {
                if (playerAction.mode == 1) { // on undo
                    getInputAndPrintLoop(controller.changePlayer(playerID), 0, oldMove)
                }
                else if (playerAction.mode == 2) { // on redo
                    //controller.Publish(controller.put, oldMove, 0)
                    getInputAndPrintLoop(controller.changePlayer(playerID), 1, oldMove)
                }
                else if (playerAction.mode == 0) { // on do
                    controller.Publish(controller.put, playerAction, 0)
                    getInputAndPrintLoop(controller.changePlayer(playerID), 1, playerAction)
                }



                /*
                controller.Publish(controller.putPlayfield, playerAction, 0)
                controller.Publish(controller.putPoints, playerAction, 0)
                */

            }





    def analyseInput(move: Move): Option[Move] =
        val input = readLine()
        input match
            case "q" => None
            case "u" => {
                controller.Publish(controller.undo, 1)
                Some(move.copy(move.dice, move.matrix, move.x, 1)) // set mode to "undo"
            }
            case "r" => {
                controller.Publish(controller.redo, 0)
                Some(move.copy(move.dice, move.matrix, move.x, 2)) // set mode to "redo"
            }
            case _ => {
                val col = input.toInt
                if (controller.checkColPublish(move.matrix, col) == -1)
                    println("Spalte ist voll!")
                    analyseInput(move)
                else
                    Some(Move(move.dice, move.matrix, col, 0)) // return new move, set mode to "do"
            }

