
package de.htwg.se.astragaloi

import model.Field
import model.Dice
import model.PointSlot

/*
import model.Move
import aview.TUI
import controller.Controller
import scala.io.StdIn.readLine
import scala.util.Random
import scala.util.control._


@main def game: Unit =

  /*
  val controller = Controller(new Field(3,2,Dice.Empty, 0))
  val move = Move(Dice.ONE, 1,2,2)
  val move1 = Move(Dice.TWO,0,2,2)
  controller.field = controller.putPoints(move)
  controller.field = controller.putPoints(move1)
  println(controller.field.toString)
  */


  val field = new Field(3 ,2, Dice.Empty, 0)
  val controller = Controller(field)
  val tui = TUI(controller)
  tui.run

  */

