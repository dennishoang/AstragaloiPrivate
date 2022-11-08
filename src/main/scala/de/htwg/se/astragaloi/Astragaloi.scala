package de.htwg.se.astragaloi

import model.Field
import model.Dice
import aview.TUI
import controller.Controller
import scala.io.StdIn.readLine


@main def game: Unit =

  val field = new Field(3, 2, Dice.random)
  println(field.toString)
/*
  val field2 = field.put(Dice.ONE, 0, 0, 0)
  val field3 = field.put(Dice.TWO, 1, 0, 1)
  println(field2.toString)
  println(field3.toString)

  val field4 = field.putSlot(Dice.random, 0)
  println(field4.toString)

  println("Welcome to Astragaloi")
  val field = new Field(3, 2, Dice.Empty)
  println(field.toString)
  getInputAndPrintLoop(field)

def getInputAndPrintLoop(field: Field): Unit =
  val input = readLine
  parseInput(input) match
    case None => field
    case Some(newfield) =>
      println(newfield)
      getInputAndPrintLoop(newfield)

  def parseInput(input: String): Option[Field] =
    input match
      case "q" => None
      case _ => {
        val chars = input.toCharArray
        val dice = chars(0) match
          case '1' => Dice.ONE
          case '2' => Dice.TWO
          case '3' => Dice.THREE
          case '4' => Dice.FOUR
          case '5' => Dice.FIVE
          case '6' => Dice.SIX
          case _   => Dice.Empty
        val matrix = chars(1).toString.toInt
        val x = chars(2).toString.toInt
        val y = chars(3).toString.toInt
        Some(field.put(dice, matrix, x, y))

      }
      */
      /*
      val field = new Field(3 ,2, Dice.Empty)
      val controller = Controller(field)
      val tui = TUI(controller)
      tui.run
      */





