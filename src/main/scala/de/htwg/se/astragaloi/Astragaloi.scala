package de.htwg.se.astragaloi


import de.htwg.se.astragaloi.aview.TUI
import de.htwg.se.astragaloi.aview.GUI
import de.htwg.se.astragaloi.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Dice


import de.htwg.se.astragaloi.model.fieldModules.Default.{given}
import de.htwg.se.astragaloi.controller.controllerModules.Default.{given}

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


      val playerID = Random.nextInt(2)
      val field = new Field(3 , 2, Dice.Empty, 0)
      val controller = new Controller(field, playerID)
      val tui = new TUI(controller)
      val gui = new GUI(controller)
      tui.run()

  }

}



