package de.htwg.se.astragaloi.model.fileIoComponent

import de.htwg.se.astragaloi.model.fieldComponent.FieldInterface

trait FileIOInterface {
    def load: FieldInterface
    def save(field: FieldInterface): Unit
}
