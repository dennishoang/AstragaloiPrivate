package de.htwg.se.astragaloi.modules

import de.htwg.se.astragaloi.model.fieldComponent.FieldInterface
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Dice
import de.htwg.se.astragaloi.controller.controllerComponent.ControllerInterface
import de.htwg.se.astragaloi.controller.controllerComponent.controllerBaseImpl.Controller

import de.htwg.se.astragaloi.model.fileIoComponent.FileIOInterface
//import de.htwg.se.astragaloi.model.fileIoComponent.fileIoXmlImpl.FileIO
import de.htwg.se.astragaloi.model.fileIoComponent.fileIoJsonImpl.FileIO

import scala.util.Random
import javax.imageio.stream.FileImageOutputStream

object AstragaloiConfig {
    val field = new Field(3, 2, Dice.Empty, 0)
    given FieldInterface = field
    given ControllerInterface = Controller()

    val fileIO = new FileIO()
    given FileIOInterface = fileIO
}

