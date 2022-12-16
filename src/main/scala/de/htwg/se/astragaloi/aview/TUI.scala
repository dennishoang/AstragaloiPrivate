package de.htwg.se.astragaloi
package aview

import controller.controllerComponent.ControllerInterface
import model.fieldComponent.fieldBaseImpl.Dice
import model.fieldComponent.fieldBaseImpl.Move
import scala.io.StdIn.readLine
import util.Observer
import util.Event
import scala.util.Random
import scala.util.{Try, Success, Failure}

import de.htwg.se.astragaloi.modules.AstragaloiConfig

class TUI(using controller: ControllerInterface) extends Observer:

    controller.add(this)

    def run(): Unit =
        val move = new Move(controller.rollDice, controller.player, 0, Dice.Empty)
        controller.startGame(move)
        printLoop()


    def finish(player: Int) =
        if (player == -1)
            println("unentschieden!")
        else
            println("Player " + player + " wins!")
        val input = readLine("(1): Restart, (2): Quit\n")
        input match
            case "1" => {
                controller.clear
                run()
            }
            case "2" => sys.exit(0) // sys.exit nicht noetig
            case _ => sys.exit(0) // sys.exit nicht noetig


    def update(e: Event) =
        e match
            case Event.Quit => sys.exit(0)
            case Event.Move =>
                println(controller.toString)
                println("Column:")

    def printLoop(): Unit =

        val input = readLine()

        val matrix = controller.player // matrix
        var value = controller.slot(matrix)
        var move = new Move(value, matrix, 0, Dice.Empty)

        analyseInput(move,input) match
            case None       =>
            case Some(playerAction) => {
                controller.publish(controller.put, playerAction)
                // checkfinish
                if (controller.checkFinish(matrix))
                    println(controller.toString)
                    finish(controller.chooseWinner)
            }
            printLoop()


    def analyseInput(move: Move, input: String): Option[Move] =
        input match
            case "q" => controller.quit; None
            case "r" => controller.publish(controller.redo); None
            case "u" => controller.publish(controller.undo); None
            case _ => {
                readCol(input) match
                    case Success(v) =>
                        val col = input.toInt
                        if (controller.checkColPublish(move.matrix, col) == -1)
                            println("Spalte ist voll!")
                            val newInput = readLine()
                            analyseInput(move, newInput)
                        else
                            Some(Move(move.dice, move.matrix, col, Dice.Empty)) // return new move, set mode to "do"
                    case Failure(i) =>
                        println("Falsche Eingabe!")
                        val newInput = readLine()
                        analyseInput(move, newInput)
            }

    def readCol(input: String): Try[String] = {
        val reading = Try(input.flatMap(x => input.map(x => x.toChar)))
        reading.filter(x => (x.equals("0")) || (x.equals("1")) || (x.equals("2")))
    }
