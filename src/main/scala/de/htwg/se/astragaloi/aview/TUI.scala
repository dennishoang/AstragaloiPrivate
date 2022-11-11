package de.htwg.se.astragaloi
package aview

import controller.Controller
import model.Dice
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
        val random = Dice.random
        controller.putSlot(random, matrix) // dadurch wird das geprinted
        if (auto_input != "") {
            auto_input match
            case "q" => None
            case _ => {
                val chars = auto_input.toCharArray
                val x = chars(0).toString.toInt
                val y = chars(1).toString.toInt
                controller.put(random, matrix, x, y)
                controller.putSlot(Dice.Empty, matrix)
            }
        } else {
            val input = readLine
            input match
            case "q" => None
            case _ => {
                val chars = input.toCharArray
                val x = chars(0).toString.toInt
                val y = chars(1).toString.toInt
                controller.put(random, matrix, x, y)
                controller.putSlot(Dice.Empty, matrix)
                getInputAndPrintLoop(controller.changePlayer(playerID), "") // change_player:  1 - playerID to switch player
            }
        }


    /*
    def getInputAndPrintLoop(playerID: Int, value: Int, row: Int, col: Int): Unit =
            val matrix = playerID
            controller.putSlot(random, matrix)
            controller.put(random, matrix, row, col)
            controller.putSlot(Dice.Empty, matrix)
    */