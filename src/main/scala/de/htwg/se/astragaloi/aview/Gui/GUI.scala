package de.htwg.se.astragaloi //.aview.Gui
package aview.gui


import util.Observer
import util.Event
import controller.Controller
import model.Move
import model.Dice
import java.awt.Dimension
import scala.swing._
import java.awt.GridLayout
import javax.swing.BoxLayout
import javax.swing.border.LineBorder
import java.awt.ComponentOrientation
import javax.swing.ImageIcon

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

    case class FieldHead(player: Int) extends FlowPanel {
        val playerLabel = new Label("Player " + player)
        val dice = new Label("Dice:")
        val diceslot = new Label { icon = new ImageIcon(diceLinks(0))}
        contents += playerLabel
        contents += dice
        contents += diceslot
    }

    case class FieldButtons() extends BoxPanel(Orientation.Horizontal) {
        val playerButtons: List[Button] = List(new Button("1") { xLayoutAlignment = 2}, new Button("2") { xLayoutAlignment = 2}, new Button("3") { xLayoutAlignment = 2})
        contents += playerButtons(0)
        contents += playerButtons(1)
        contents += playerButtons(2)
    }

    case class FieldMatrix() extends GridPanel(1,3) {
        val cellsPlayer: List[List[Label]] = List(
            List(new Label { icon = new ImageIcon(diceLinks(1)) }, new Label { icon = new ImageIcon(diceLinks(2)) }, new Label { icon = new ImageIcon(diceLinks(6)) }),
            List(new Label { icon = new ImageIcon(diceLinks(0)) }, new Label { icon = new ImageIcon(diceLinks(0)) }, new Label { icon = new ImageIcon(diceLinks(3)) }),
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

    case class FieldPoints() extends GridPanel(1,3) {
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
    }

    case class Field2(var head: FieldHead, var buttons: FieldButtons, var matrix: FieldMatrix, var points: FieldPoints) extends BoxPanel(Orientation.Vertical) {
        contents += points
        contents += matrix
        contents += buttons
        contents += head
    }

    case class Playfield(var field1: Field1, var field2: Field2) extends BoxPanel(Orientation.Vertical) {
        contents += field1
        contents += field2
    }

    case class Finalfield(var playfield: Playfield, var totalPoints: TotalPoints) extends FlowPanel {
        contents += playfield
        contents += totalPoints
    }


    val field1 = new Field1(new FieldHead(1), new FieldButtons, new FieldMatrix, new FieldPoints)
    val field2 = new Field2(new FieldHead(2), new FieldButtons, new FieldMatrix, new FieldPoints)
    val playfield = new Playfield(field1, field2)
    val finalfield = new Finalfield(playfield, new TotalPoints)
    contents = new BorderPanel {
        add(finalfield, BorderPanel.Position.Center)
    }
    visible = true


    def update(e: Event): Unit = e match
        case Event.Quit =>
        case Event.Move =>
