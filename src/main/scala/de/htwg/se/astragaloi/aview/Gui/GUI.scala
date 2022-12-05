package de.htwg.se.astragaloi //.aview.Gui
package aview.gui


import util.Observer
import controller.Controller
import model.Move
import model.Dice
import java.awt.Dimension
import scala.swing._

class GUI(controller: Controller) extends Frame:

    //controller.add(this)
    size = new Dimension(600,600)
    title = "Astragaloi"
    visible = true




