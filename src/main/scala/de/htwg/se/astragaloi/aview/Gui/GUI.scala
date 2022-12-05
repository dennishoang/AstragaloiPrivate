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
    def playfieldHead(player: Int, diceslot: TextField) = new FlowPanel {
        val playerLabel = new Label("Player " + player)
        val dice = new Label("Dice:")
        diceslot1.editable = false
        contents += playerLabel
        contents += dice
        contents += diceslot
    }

    val button1 = new Button("1") {
        //xLayoutAlignment = 0.5
    }
    val button2 = new Button("2") {
        //xLayoutAlignment = 0.5
    }
    val button3 = new Button("3") {
        //xLayoutAlignment = 0.5
    }

    def fieldbutton = new BoxLayout(1,3) {
        contents += button1
        contents += button2
        contents += button3
    }


    val diceLinks: List[String] = List("src/main/resources/DiceEmpty.png", "src/main/resources/DiceOne.png", "src/main/resources/DiceTwo.png", "src/main/resources/DiceThree.png",
        "src/main/resources/DiceFour.png", "src/main/resources/DiceFive.png", "src/main/resources/DiceSix.png")

    val pic = new ImageIcon("src/main/resources/DiceEmpty.png")

    def fieldmatrix = new  GridPanel(3,3):
        val range = Range(0, 9)
        for (i <- range) {
            contents += new Label {
                icon = new ImageIcon(diceLinks(0))
            }
        }

    class Field() extends GridPanel(2,1):
        contents += fieldbutton
        contents += fieldmatrix
    contents = new BorderPanel {
        add(playfieldHead(1, diceslot1), BorderPanel.Position.North)
        //add(fieldbutton, BorderPanel.Position.Center)
        add(new Field(), BorderPanel.Position.Center)
        add(playfieldHead(2, diceslot2), BorderPanel.Position.South)
    }
    visible = true


    def update(e: Event): Unit = e match
        case Event.Quit =>
        case Event.Move =>









