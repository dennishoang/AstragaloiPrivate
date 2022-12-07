package de.htwg.se.astragaloi //.aview.Gui
package aview.gui


import util.Observer
import util.Event
import controller.Controller
import aview.TUI
import model.Move
import model.Dice
import java.awt.Dimension
import scala.swing._
import scala.swing.event.ButtonClicked
import java.awt.GridLayout
import javax.swing.BoxLayout
import scala.swing.event._
import javax.swing.border.LineBorder
import java.awt.ComponentOrientation
import javax.swing.ImageIcon
import javax.swing.UIManager
import javax.swing.UIDefaults
import java.util.Scanner


import scala.io.StdIn.readLine
import java.io.InputStream
import java.io.ByteArrayInputStream


class GUI(controller: Controller) extends Frame with Observer:
    //listenTo(controller)
    controller.add(this)
    size = new Dimension(1000,1000)
    title = "Astragaloi"

    menuBar = new MenuBar {
        contents += new Menu("File") {
            contents += MenuItem("undo")
            contents += MenuItem("redo")
            contents += MenuItem(Action("Exit") {
            sys.exit(0)
            })
        }
    }


    val diceLinks: List[String] = List("src/main/resources/DiceEmpty.png", "src/main/resources/DiceOne.png", "src/main/resources/DiceTwo.png", "src/main/resources/DiceThree.png",
        "src/main/resources/DiceFour.png", "src/main/resources/DiceFive.png", "src/main/resources/DiceSix.png")

    val diceToImg: Map[Dice, ImageIcon] = Map(Dice.Empty -> new ImageIcon(diceLinks(0)), Dice.ONE -> new ImageIcon(diceLinks(1)), Dice.TWO -> new ImageIcon(diceLinks(2)),
     Dice.THREE -> new ImageIcon(diceLinks(3)), Dice.FOUR -> new ImageIcon(diceLinks(4)), Dice.FIVE -> new ImageIcon(diceLinks(5)), Dice.SIX -> new ImageIcon(diceLinks(6)))

    val imgToDice: Map[String, Dice] = Map(diceLinks(0) -> Dice.Empty, diceLinks(1) -> Dice.ONE, diceLinks(2) -> Dice.TWO, diceLinks(3) -> Dice.THREE,
     diceLinks(4) -> Dice.FOUR, diceLinks(5) -> Dice.FIVE, diceLinks(6) -> Dice.SIX)

    class FieldHead(matrix: Int) extends FlowPanel {
        val player = matrix + 1
        val playerLabel = new Label("Player " + player)
        val dice = new Label("Dice:")
        val diceslot = new Label { icon = new ImageIcon(diceLinks(0))}
        contents += playerLabel
        contents += dice
        contents += diceslot

        def changeSlot(dice: Dice) =
            diceslot.icon = diceToImg.get(dice).get
    }


    class FieldButtons(matrix: Int) extends BoxPanel(Orientation.Horizontal) {
        val button1 = new Button("1")
        val button2 = new Button("2")
        val button3 = new Button("3")

        listenTo(button1)
        reactions += {
            case ButtonClicked(`button1`) =>
                val dice = controller.getSlot(matrix)
                val move = new Move(dice, matrix, 0, 0)
                controller.Publish(controller.put, move)
            }
        listenTo(button2)
        reactions += {
            case ButtonClicked(`button2`) =>
                val dice = controller.getSlot(matrix)
                val move = new Move(dice, matrix, 1, 0)
                controller.Publish(controller.put, move)
        }
        listenTo(button3)
        reactions += {
            case ButtonClicked(`button3`) =>
                val dice = controller.getSlot(matrix)
                val move = new Move(dice, matrix, 2, 0)
                controller.Publish(controller.put, move)
        }
        contents += button1
        contents += button2
        contents += button3

        def disableButtons =
            button1.enabled = false
            button2.enabled = false
            button3.enabled = false
        def enableButtons =
            button1.enabled = true
            button2.enabled = true
            button3.enabled = true
    }


    case class FieldMatrix(matrix: Int) extends GridPanel(1,3) {
        val cellsPlayer: List[List[Label]] = List(
            List(new Label { icon = new ImageIcon(diceLinks(0)) }, new Label { icon = new ImageIcon(diceLinks(0)) }, new Label { icon = new ImageIcon(diceLinks(0)) }),
            List(new Label { icon = new ImageIcon(diceLinks(0)) }, new Label { icon = new ImageIcon(diceLinks(0)) }, new Label { icon = new ImageIcon(diceLinks(0)) }),
            List(new Label { icon = new ImageIcon(diceLinks(0)) }, new Label { icon = new ImageIcon(diceLinks(0)) }, new Label { icon = new ImageIcon(diceLinks(0)) })
        )
        val range = Range(0, 3)
        for (i <- range)

            contents += new GridPanel(3,1) {
                for (j <- range)
                    border = LineBorder(new Color(0, 0, 0, 255), 3)
                    contents += cellsPlayer(i)(j)
            }

        def changeCells(values: Vector[Dice], col: Int) =
            for (i <- Range(0, 3))
            cellsPlayer(col)(i).icon = diceToImg.get(values(i)).get
    }

    class FieldPoints(matrix: Int) extends GridPanel(1,3) {
        val playerPoints: List[TextField] = List(new TextField("0"), new TextField("0"), new TextField("0"))
        for(i <- playerPoints)
            i.editable = false
        contents += playerPoints(0)
        contents += playerPoints(1)
        contents += playerPoints(2)
        def changePoints(points: Vector[Int]) =
            for (i <- Range(0, points.size - 1))
                playerPoints(i).text = points(i).toString
    }

    class TotalPoints() extends GridPanel(2,1) {
        val totalPointsPlayer1 = new TextField("0")
        val totalPointsPlayer2 = new TextField("0")
        totalPointsPlayer1.editable = false
        totalPointsPlayer2.editable = false
        contents += totalPointsPlayer1
        contents += totalPointsPlayer2
        def changePoints(points1: Vector[Int], points2: Vector[Int]) =
            totalPointsPlayer1.text = points1(points1.size - 1).toString
            totalPointsPlayer2.text = points2(points2.size - 1).toString
    }

    case class Field1(var head: FieldHead, var buttons: FieldButtons, var matrix: FieldMatrix, var points: FieldPoints) extends BoxPanel(Orientation.Vertical) {
        contents += head
        contents += buttons
        contents += matrix
        contents += points
        def changeSlot(dice: Dice) =
            head.changeSlot(dice)
        def changeCells(values: Vector[Dice], col: Int) =
            matrix.changeCells(values, col)
        def changePoints(values: Vector[Int]) =
            points.changePoints(values)
    }

    class Field2(var head: FieldHead, var buttons: FieldButtons, var matrix: FieldMatrix, var points: FieldPoints) extends BoxPanel(Orientation.Vertical) {
        contents += points
        contents += matrix
        contents += buttons
        contents += head

        def changeSlot(dice: Dice) =
            head.changeSlot(dice)
        def changeCells(values: Vector[Dice], col: Int) =
            matrix.changeCells(values, col)
        def changePoints(values: Vector[Int]) =
            points.changePoints(values)
    }

    class Playfield(var field1: Field1, var field2: Field2) extends BoxPanel(Orientation.Vertical) {
        contents += field1
        contents += field2

        def changeSlot(move: Move) =
            move.matrix match
                case 0 => field1.changeSlot(move.dice)
                case 1 => field2.changeSlot(move.dice)
    }

    class Finalfield(var playfield: Playfield, var totalPoints: TotalPoints) extends FlowPanel {
        contents += playfield
        contents += totalPoints
        def changePoints(points1: Vector[Int], points2: Vector[Int]) =
            totalPoints.changePoints(points1, points2)
    }


    val field1 = new Field1(new FieldHead(0), new FieldButtons(0), new FieldMatrix(0), new FieldPoints(0))
    val field2 = new Field2(new FieldHead(1), new FieldButtons(1), new FieldMatrix(1), new FieldPoints(1))
    val playfield = new Playfield(field1, field2)
    val finalfield = new Finalfield(playfield, new TotalPoints)


    // finalfield.playfield.changeSlot(new Move(Dice.THREE, 0, 0, 0)) zum testen
    contents = new BorderPanel {
        add(finalfield, BorderPanel.Position.Center)
    }
    //visible = true
    pack()
    centerOnScreen()
    open()



    // update GUI when notifyObervers is called
    def redraw: Unit =
        // update diceslots
        val nextMatrix = controller.player
        val prevMatrix = 1 - controller.player

        val dice1 = controller.getSlot(0)
        val dice2 = controller.getSlot(1)
        field1.head.changeSlot(dice1)
        field2.head.changeSlot(dice2)

        // disable and enable buttons
        nextMatrix match
            case 0 => {
                field1.buttons.enableButtons
                field2.buttons.disableButtons
            }
            case 1 => {
                field2.buttons.enableButtons
                field1.buttons.disableButtons
            }
        // update matrix
        for (i <- Range(0, 3))
            field1.matrix.changeCells(controller.getCol(0, i), i)
            field2.matrix.changeCells(controller.getCol(1, i), i)
        // update points
        val points1 = controller.getPoints(0)
        val points2 = controller.getPoints(1)
        field1.points.changePoints(points1)
        field2.points.changePoints(points2)
        finalfield.totalPoints.changePoints(points1, points2)

        repaint() // at the end repaint the GUI

        if (controller.checkFinish(prevMatrix))
            //repaint()
            finish(controller.chooseWinner)


    def finish(winner: Int) =
        val str = "Player " + winner + " wins!\n" + "Do you want to Quit?"
        UIManager.put("OptionPane.noButtonText", "Restart");
        UIManager.put("OptionPane.yesButtonText", "Quit");
        val res = Dialog.showConfirmation(contents.head, str, optionType=Dialog.Options.YesNo, title=title)
        res match
            case Dialog.Result.Yes => {
                sys.exit(0)
            }
            case Dialog.Result.No => {
                controller.clear
                val move = new Move(controller.rollDice, controller.player, 0, 0)
                controller.startGame(move)
                redraw
            }


    def update(e: Event): Unit =
        e match
            case Event.Quit => // continue = false
            case Event.Move => redraw
