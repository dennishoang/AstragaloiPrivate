package de.htwg.se.astragaloi //.aview.Gui
package aview.gui


import util.Observer
import util.Event
import controller.Controller
import model.Move
import model.Dice
import java.awt.Dimension
import scala.swing._
import scala.swing.event.ButtonClicked
import java.awt.GridLayout
import javax.swing.BoxLayout
import javax.swing.border.LineBorder
import java.awt.ComponentOrientation
import javax.swing.ImageIcon
import scala.io.StdIn.readLine

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

    case class FieldHead(matrix: Int) extends FlowPanel {
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

    case class FieldButtons(matrix: Int) extends BoxPanel(Orientation.Horizontal) {
        val playerButtons: List[Button] = List(new Button("1") { xLayoutAlignment = 2}, new Button("2") { xLayoutAlignment = 2}, new Button("3") { xLayoutAlignment = 2})

        val button1 = playerButtons(0)
        val button2 = playerButtons(1)
        val button3 = playerButtons(2)
        listenTo(button1)
        listenTo(button2)
        listenTo(button3)
        reactions += {
            case ButtonClicked(`button1`) => {
                //getSlot
                val move = new Move(Dice.SIX, matrix, 0, 0)
                controller.Publish(controller.put, move ,1)
            }
            case ButtonClicked(`button2`) => {
                //getSlot
                val move = new Move(Dice.SIX, matrix, 1, 0)
                controller.Publish(controller.put, move ,1)
            }
            case ButtonClicked(`button3`) => {
                //getSlot
                val move = new Move(Dice.SIX, matrix, 2, 0)
                controller.Publish(controller.put, move ,1)
            }
        }

        contents += playerButtons(0)
        contents += playerButtons(1)
        contents += playerButtons(2)
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
    }

    case class FieldPoints(matrix: Int) extends GridPanel(1,3) {
        val playerPoints: List[TextField] = List(new TextField("0"), new TextField("0"), new TextField("0"))
        for(i <- playerPoints)
            i.editable = false
        contents += playerPoints(0)
        contents += playerPoints(1)
        contents += playerPoints(2)
    }

    case class TotalPoints() extends GridPanel(2,1) {
        val totalPointsPlayer1 = new TextField("0")
        val totalPointsPlayer2 = new TextField("0")
        totalPointsPlayer1.editable = false
        totalPointsPlayer2.editable = false
        contents += totalPointsPlayer1
        contents += totalPointsPlayer2
    }

    case class Field1(var head: FieldHead, var buttons: FieldButtons, var matrix: FieldMatrix, var points: FieldPoints) extends BoxPanel(Orientation.Vertical) {
        contents += head
        contents += buttons
        contents += matrix
        contents += points
        def changeSlot(dice: Dice) =
            head.changeSlot(dice)
    }

    case class Field2(var head: FieldHead, var buttons: FieldButtons, var matrix: FieldMatrix, var points: FieldPoints) extends BoxPanel(Orientation.Vertical) {
        contents += points
        contents += matrix
        contents += buttons
        contents += head
        def changeSlot(dice: Dice) =
            head.changeSlot(dice)
    }

    case class Playfield(var field1: Field1, var field2: Field2) extends BoxPanel(Orientation.Vertical) {
        contents += field1
        contents += field2
        def changeSlot(move: Move) =
            move.matrix match
                case 0 => field1.changeSlot(move.dice)
                case 1 => field2.changeSlot(move.dice)
    }

    case class Finalfield(var playfield: Playfield, var totalPoints: TotalPoints) extends FlowPanel {
        contents += playfield
        contents += totalPoints
    }


    val field1 = new Field1(new FieldHead(0), new FieldButtons(0), new FieldMatrix(0), new FieldPoints(0))
    val field2 = new Field2(new FieldHead(1), new FieldButtons(1), new FieldMatrix(1), new FieldPoints(1))
    val playfield = new Playfield(field1, field2)
    val finalfield = new Finalfield(playfield, new TotalPoints)



    // finalfield.playfield.changeSlot(new Move(Dice.THREE, 0, 0, 0)) zum testen
    contents = new BorderPanel {
        add(finalfield, BorderPanel.Position.Center)
    }
    visible = true



    // update GUI when notifyObervers is called
    def redraw =
        // update diceslots
        val dice1 = controller.getSlot(0)
        val dice2 = controller.getSlot(1)
        field1.head.changeSlot(dice1)
        field2.head.changeSlot(dice2)
        // update matrix
        

        repaint() // at the end repaint the GUI


    override def update(e: Event): Unit =
        e match
            case Event.Quit => // continue = false
            case Event.Move => redraw
