package de.htwg.se.astragaloi.model.fieldModules

import de.htwg.se.astragaloi.model.fieldComponent.FieldInterface
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl.Dice

object Default {
    given FieldInterface = Field()
}

