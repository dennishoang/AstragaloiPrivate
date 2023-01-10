package de.htwg.se.astragaloi.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.astragaloi.modules.AstragaloiConfig
import de.htwg.se.astragaloi.model.fileIoComponent.FileIOInterface
import de.htwg.se.astragaloi.model.fieldComponent.FieldInterface
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl._
import play.api.libs.json._

import scala.io.Source

class FileIO extends FileIOInterface {

    def load: FieldInterface = {
        var field: FieldInterface = new Field(3, 2, Dice.Empty, 0)
        val file: String = Source.fromFile("field.json").getLines.mkString
        val json: JsValue = Json.parse(file)
        //val matrixes = (json \\ "field")
        for (i <- Range(0, 2)) {
            val number: Int = (json \ "matrix")(i).as[Int]

            val diceslot: String = (json \ "Diceslot")(i).as[String]
            //print("Dice: " + dice)
            diceslot match {
                case " " => field = field.putSlot(Dice.Empty, number)
                case "1" => field = field.putSlot(Dice.ONE, number)
                case "2" => field = field.putSlot(Dice.TWO, number)
                case "3" => field = field.putSlot(Dice.THREE, number)
                case "4" => field = field.putSlot(Dice.FOUR, number)
                case "5" => field = field.putSlot(Dice.FIVE, number)
                case "6" => field = field.putSlot(Dice.SIX, number)
            }


            val cells = (json \\ "cells")(i)
            for (j <- Range(0, 9)) {
                val colnumber: Int = (cells \ "col")(j).as[Int]
                //val row: Int = (cell \ "row").as[Int]
                val value: String = (cells \ "value")(j).as[String]
                //print("Value: " + value)
                value match {
                    case " " => field = field.put(Dice.Empty, number, colnumber, 0, Vector[Int]())
                    case "1" => field = field.put(Dice.ONE, number, colnumber, 0, Vector[Int]())
                    case "2" => field = field.put(Dice.TWO, number, colnumber, 0, Vector[Int]())
                    case "3" => field = field.put(Dice.THREE, number, colnumber, 0, Vector[Int]())
                    case "4" => field = field.put(Dice.FOUR, number, colnumber, 0, Vector[Int]())
                    case "5" => field = field.put(Dice.FIVE, number, colnumber, 0, Vector[Int]())
                    case "6" => field = field.put(Dice.SIX, number, colnumber, 0, Vector[Int]())
                }

            }

            for (k <- Range(0, 3)) {
                field = field.putPoint(number, k)
            }
        }
        field
    }

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
                        "cells" -> Json.toJson(
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