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

    val diceslot1 = new TextField("Wert")
    val diceslot2 = new TextField("Wert")


    val buttonsPlayer1: List[Button] = List(new Button("1") { xLayoutAlignment = 2}, new Button("2") { xLayoutAlignment = 2}, new Button("3") { xLayoutAlignment = 2})
    val buttonsPlayer2: List[Button] = List(new Button("1") { xLayoutAlignment = 2}, new Button("2") { xLayoutAlignment = 2}, new Button("3") { xLayoutAlignment = 2})

    val pointsPlayer1: List[TextField] = List(new TextField("0"), new TextField("0") , new TextField("0"), new TextField("0"))
    val pointsPlayer2: List[TextField] = List(new TextField("0"), new TextField("0"), new TextField("0"), new TextField("0"))

    val diceLinks: List[String] = List("src/main/resources/DiceEmpty.png", "src/main/resources/DiceOne.png", "src/main/resources/DiceTwo.png", "src/main/resources/DiceThree.png",
        "src/main/resources/DiceFour.png", "src/main/resources/DiceFive.png", "src/main/resources/DiceSix.png")



    for(i <- pointsPlayer1)
        i.editable = false
    for(i <- pointsPlayer2)
        i.editable = false

    def playfieldHead(player: Int, diceslot: TextField) = new FlowPanel {
        val playerLabel = new Label("Player " + player)
        val dice = new Label("Dice:")
        diceslot1.editable = false
        contents += playerLabel
        contents += dice
        contents += diceslot
    }

    def fieldbuttons(playerButtons: List[Button]) = new BoxPanel(Orientation.Horizontal) {
        contents += playerButtons(0)
        contents += playerButtons(1)
        contents += playerButtons(2)
    }

    def fieldmatrix(value: Int) = new GridPanel(3,3) {
        val range = Range(0, 9)
        for (i <- range) {
            contents += new Label {
                border = new LineBorder(new Color(0, 0, 0, 200), 3)
                icon = new ImageIcon(diceLinks(value))
            }
        }
    }

    def fieldpoints(playerPoints: List[TextField]) = new GridPanel(1,3) {
        contents += playerPoints(0)
        contents += playerPoints(1)
        contents += playerPoints(2)
    }

    def totalPoints(playerPoints1: List[TextField], playerPoints2: List[TextField]) = new GridPanel(2,1) {
        contents += playerPoints1(3)
        contents += playerPoints2(3)
    }

    def field1 = new BoxPanel(Orientation.Vertical) {
        contents += playfieldHead(1, diceslot1)
        contents += fieldbuttons(buttonsPlayer1)
        contents += fieldmatrix(0)
        contents += fieldpoints(pointsPlayer1)
    }

    def field2 = new BoxPanel(Orientation.Vertical) {
        contents += fieldpoints(pointsPlayer2)
        contents += fieldmatrix(0)
        contents += fieldbuttons(buttonsPlayer2)
        contents += playfieldHead(2, diceslot2)
    }

    def playfield = new BoxPanel(Orientation.Vertical) {
        contents += field1
        contents += field2
    }

    contents = new BorderPanel {
        add(playfield, BorderPanel.Position.Center)
        add(totalPoints(pointsPlayer1, pointsPlayer2), BorderPanel.Position.East)
    }
    visible = true


    def update(e: Event): Unit = e match
        case Event.Quit =>
        case Event.Move =>
