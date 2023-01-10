package de.htwg.se.astragaloi.model.fileIoComponent.fileIoXmlImpl

import de.htwg.se.astragaloi.modules.AstragaloiConfig
import de.htwg.se.astragaloi.model.fileIoComponent.FileIOInterface
import de.htwg.se.astragaloi.model.fieldComponent.FieldInterface
import de.htwg.se.astragaloi.model.fieldComponent.fieldBaseImpl._
import scala.xml.{ NodeSeq, PrettyPrinter }

class FileIO extends FileIOInterface {

    override def load: FieldInterface = {
        var field: FieldInterface = new Field(3, 2, Dice.Empty, 0)
        val file = scala.xml.XML.loadFile("field.xml")
        val matrixes = (file \\ "matrix")
        for (matrix <- matrixes) {
            val number: Int = (matrix \ "number").text.toInt

            val diceslot = (matrix \\ "diceslot")
            val dice: String = (diceslot \ "dice").text.toString
            //print("Dice: " + dice)
            dice match {
                case " " => field = field.putSlot(Dice.Empty, number)
                case "1" => field = field.putSlot(Dice.ONE, number)
                case "2" => field = field.putSlot(Dice.TWO, number)
                case "3" => field = field.putSlot(Dice.THREE, number)
                case "4" => field = field.putSlot(Dice.FOUR, number)
                case "5" => field = field.putSlot(Dice.FIVE, number)
                case "6" => field = field.putSlot(Dice.SIX, number)
            }


            val cols = (matrix \\ "col")
            for (col <- cols) {
                //val col = (c \\ "col")
                val colnumber: Int = (col \ "number").text.toInt
                val cells = (col \\ "cell")
                for (cell <- cells) {
                    val value: String = (cell \ "value").text.toString
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
            }

            for (i <- Range(0, 3)) {
                field = field.putPoint(number, i)
            }
        }
        field
    }

    def save(field: FieldInterface): Unit = saveString(field)

    def saveXML(field: FieldInterface): Unit = {
        scala.xml.XML.save("field.xml", fieldToXml(field))
    }

    def saveString(field: FieldInterface): Unit = {
        import java.io._
        val pw = new PrintWriter(new File("field.xml"))
        val prettyPrinter = new PrettyPrinter(120, 4)
        val xml = prettyPrinter.format(fieldToXml(field))
        pw.write(xml)
        pw.close
    }

    def fieldToXml(field: FieldInterface) = {
        <field>
            {
                for {
                    matrix <- 0 until 2
                }
                yield matrixToXml(field, matrix)
            }
        </field>
    }

    def matrixToXml(field: FieldInterface, matrix: Int) = {
        <matrix>
            <number>{ matrix.toString }</number>
            { diceslotToXml(field, matrix) }
            {
                for {
                    col <- 0 until 3
                } yield colToXml(field, matrix, col)
            }
        </matrix>
    }

    def colToXml(field: FieldInterface, matrix: Int, col: Int) = {
        <col>
            <number>{ col.toString }</number>
            {
                for {
                    x <- 0 until 3
                } yield cellToXml(field, matrix, col, x)
            }
        </col>
    }

    def cellToXml(field: FieldInterface, matrix: Int, col: Int, row: Int) = {
        <cell>
            <value>{ field.col(matrix, col)(row).toString }</value>
        </cell>
    }

    def diceslotToXml(field: FieldInterface, matrix: Int) = {
        <diceslot>
            <dice>{ field.slot(matrix).toString }</dice>
        </diceslot>
    }

}
