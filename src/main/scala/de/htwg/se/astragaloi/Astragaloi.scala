
package de.htwg.se.astragaloi

import model.Field
import model.Dice
import model.PointSlot


import model.Move
import aview.TUI
import aview.gui.GUI
import util.Observer
import controller.Controller
import scala.io.StdIn.readLine
import scala.util.Random
import scala.util.control._

object astragaloi {
  def main(args: Array[String]): Unit = {

    /*
    val controller = Controller(new Field(3,2,Dice.Empty, 0))
    val move = Move(Dice.ONE, 1,2,2)
    val move1 = Move(Dice.TWO,0,2,2)
    controller.field = controller.putPoints(move)
    controller.field = controller.putPoints(move1)
    println(controller.field.toString)
    */


      val field = new Field(3 ,2, Dice.Empty, 0)
      val playerID = Random.nextInt(2)
      val controller = new Controller(field, playerID)
      val tui = TUI(controller)
      val gui = new GUI(controller)
      tui.run()

  }

}



