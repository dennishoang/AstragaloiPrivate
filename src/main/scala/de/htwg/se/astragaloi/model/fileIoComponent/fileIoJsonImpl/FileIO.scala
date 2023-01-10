package de.htwg.se.astragaloi.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.astragaloi.modules.AstragaloiConfig
import de.htwg.se.astragaloi.model.fileIoComponent.FileIOInterface
import de.htwg.se.astragaloi.model.fieldComponent.FieldInterface
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl._
import play.api.libs.json._

class FileIO extends FileIOInterface {

    def load: FieldInterface =
        new Field(3, 2, Dice.Empty, 0)

    override def save(field: FieldInterface): Unit = {
        import java.io._
        val pw = new PrintWriter(new File("field.json"))
        pw.write(Json.prettyPrint(fieldtoJson(field)))
        pw.close
    }

    def fieldtoJson(field: FieldInterface) = {
        Json.obj(
            "field" -> Json.toJson(
                for {
                    matrix <- 0 until 2
                } yield {
                    Json.obj(
                        "matrix" -> matrix,
                        "Diceslot" -> JsString((field.slot(matrix)).toString),
                        "cols" -> Json.toJson(
                            for {
                                col <- 0 until 3
                                row <- 0 until 3
                            } yield {
                                Json.obj(
                                    "col" -> col,
                                    "row" -> row,
                                    "value" -> field.col(matrix, col)(row).toString
                                )
                            }

                        )
                    )
                }

            )
        )
    }
}