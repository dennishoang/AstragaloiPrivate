package de.htwg.se.astragaloi.controller.controllerModules

import de.htwg.se.astragaloi.controller.controllerComponent.ControllerInterface
import de.htwg.se.astragaloi.controller.controllerComponent.controllerBaseImpl.Controller

object Default {
    given ControllerInterface = Controller()
}
