package de.htwg.se.astragaloi
package aview

import controller.Controller
import model.Dice
import model.Move
import scala.io.StdIn.readLine
//import scala.io.StdIn.readInt
import util.Observer
import util.Event
import scala.util.Random
import scala.util.{Try, Success, Failure}


case class TUI(controller: Controller) extends Observer:

    controller.add(this)

    var undoCounter = 0
    var doCounter = 0
    var continue = true

    def run =
        val playerID = Random.nextInt(2)
        val move = new Move(controller.rollDice, playerID, 0, 0)
        controller.startGame(move)
        printLoop(playerID, false)

    def finish(player: Int) =
        if (player == -1)
            println("unentschieden!")
        else
            println("Player " + player + " wins!")

    override def update(e: Event) =
        e match
            case Event.Quit => continue = false
            case Event.Move => println(controller.field.toString)


    def printLoop(matrix: Int, undoDone: Boolean): Unit =

        var move = Move(Dice.Empty, 0, 0, 0)

        if (undoDone) {
            var value = controller.getSlot(matrix)
            move = Move(value, matrix, 0, 0)
        } //else {
            //val random = controller.rollDice()
            //move = Move(random, matrix,  0, 0)
            //controller.Publish(controller.putDiceslot, move, 1)
        //}


        analyseInput(move) match
            case None       =>
            case Some(playerAction) => {
                var undo = false
                if (playerAction.mode == 1) { // on undo
                    if (doCounter > 0)
                        doCounter -= 1
                    undoCounter += 1
                    undo = true
                }
                else if (playerAction.mode == 2) { // on redo
                    if (undoCounter > 0)
                        undoCounter -= 1
                    doCounter += 1
                }
                else if (playerAction.mode == 0) { // on do
                    if (undoCounter > 0)
                        undoCounter -= 1
                    doCounter += 1
                    controller.Publish(controller.put, playerAction)
                    // checkfinish
                    if (controller.checkFinish(matrix))
                        controller.Publish(controller.finish, 1)
                        finish(controller.chooseWinner)
                        continue = false
                }
                if continue then printLoop(controller.changePlayer(matrix), undo)
            }

    def analyseInput(move: Move): Option[Move] =
        val input = readLine("Column: \n")
        input match
            case "q" => None
            case "u" => {
                if (doCounter == 0)
                    println("kein Undo moeglich")
                    analyseInput(move)
                else
                    controller.Publish(controller.undo, 1)
                    Some(move.copy(move.dice, move.matrix, move.x, 1)) // set mode to "undo"
            }
            case "r" => {
                if (undoCounter == 0)
                    println("kein Redo moeglich")
                    analyseInput(move)
                else
                    controller.Publish(controller.redo, 0)
                    Some(move.copy(move.dice, move.matrix, move.x, 2)) // set mode to "redo"
            }
            case _ => {
                readCol(input) match
                    case Success(v) =>
                        val col = input.toInt
                        if (controller.checkColPublish(move.matrix, col) == -1)
                            println("Spalte ist voll!")
                            analyseInput(move)
                        else
                            Some(Move(move.dice, move.matrix, col, 0)) // return new move, set mode to "do"
                    case Failure(i) =>
                        println("Falsche Eingabe!")
                        analyseInput(move)
            }

    def readCol(input: String): Try[String] = {
        val reading = Try(input.flatMap(x => input.map(x => x.toChar)))
        reading.filter(x => (x.equals("0")) || (x.equals("1")) || (x.equals("2")))
    }
