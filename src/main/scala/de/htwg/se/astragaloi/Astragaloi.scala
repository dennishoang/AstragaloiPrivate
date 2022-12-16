package de.htwg.se.astragaloi


import de.htwg.se.astragaloi.aview.TUI
import de.htwg.se.astragaloi.aview.GUI
import de.htwg.se.astragaloi.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Dice

import de.htwg.se.astragaloi.modules.AstragaloiConfig.given_FieldInterface
import de.htwg.se.astragaloi.modules.AstragaloiConfig.given_ControllerInterface

import scala.io.StdIn.readLine
import scala.util.Random
import scala.util.control._

object astragaloi {

  def main(args: Array[String]): Unit = {

    val tui = new TUI()
    val gui = new GUI()
    tui.run()

  }

}



