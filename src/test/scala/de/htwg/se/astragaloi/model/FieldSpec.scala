package de.htwg.se.astragaloi.model

import model.Field

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class FieldSpec extends AnyWordSpec {

    "A Field" when {
        "empty " should {

            val field = new Field
            val field1 = new Field
            val field2 = new Field
            val eol = sys.props("line.separator")
            "have a bar as a String of a form '+-----+     +-----+     +-----+'" in {
                field.bars(5,5) should be ("+-----+     +-----+     +-----+     " + eol)
            }
            "have a scalable bar" in {
                field1.bars(3,3) should be ("+---+   +---+   +---+   " + eol)
                field2.bars(1,1) should be ("+-+ +-+ +-+ " + eol)
                field2.bars(4,1) should be ("+----+ +----+ +----+ " + eol)
            }
            "have a cell as a String of a form '|     |     |     |     |     |'" in {
                field.cells(5,5) should be ("|     |     |     |     |     |     " + eol)
            }
            "have a scalable cells" in {
                field1.cells(2,4) should be ("|  |    |  |    |  |    " + eol)
                field2.cells(5,0) should be ("|     ||     ||     |" + eol)
                field.cells(0,2) should be ("||  ||  ||  " + eol)
            }
            "have scalable playfield in form of" + eol +
            "+--+ +--+ +--+" + eol +
            "|  | |  | |  |" + eol +
            "+--+ +--+ +--+" + eol +
            "|  | |  | |  |" + eol +
            "+--+ +--+ +--+" + eol +
            "|  | |  | |  |" + eol +
            "+--+ +--+ +--+" in {
                field.playfield(2,1,1) should be("+--+ +--+ +--+ " + eol + "|  | |  | |  | " + eol + "+--+ +--+ +--+ "+
                eol + "|  | |  | |  | " + eol + "+--+ +--+ +--+ " + eol + "|  | |  | |  | " + eol + "+--+ +--+ +--+ " + eol)
            }
            "have a quadbar as a String of a form '+---+'" in {
                field.quadbar(3,0) should be ("+---+" + eol)
            }
            "have scalable quadbars" in {
                field.quadbar(5,3) should be ("   +-----+" + eol)
                field1.quadbar(1,5) should be ("     +-+" + eol)
                field2.quadbar(4,4) should be ("    +----+" + eol)
            }
            "have a quadcell as a String of a form '|   |'" in {
                field.quadcell(2,4) should be ("    |  |" + eol)
            }
            "have scalable quadcells" in {
                field.quadcell(0,0) should be ("||" + eol)
                field1.quadcell(4,1) should be (" |    |" + eol)
                field2.quadcell(6,2) should be ("  |      |" + eol)
            }
            "have a scalable quadrat in form of" + eol +
            "+------+" + eol +
            "|      |" + eol +
            "|      |" + eol +
            "+------+" in {
                field.quadrat(6,2,0) should be ("+------+" + eol + "|      |" + eol + "|      |" + eol + "+------+" + eol)
            }
            "have as scalable mesh in form of" + eol +
            "               +----+" + eol +
            "               |    |" + eol +
            "               |    |" + eol +
            "               +----+" + eol + eol
            "+--+ +--+ +--+       " + eol +
            "|  | |  | |  |       " + eol +
            "+--+ +--+ +--+       " + eol +
            "|  | |  | |  |       " + eol +
            "+--+ +--+ +--+       " + eol +
            "|  | |  | |  |       " + eol +
            "+--+ +--+ +--+       " + eol + eol
            "-------------------------" + eol * 2+
            "+--+ +--+ +--+       " + eol +
            "|  | |  | |  |       " + eol +
            "+--+ +--+ +--+       " + eol +
            "|  | |  | |  |       " + eol +
            "+--+ +--+ +--+       " + eol +
            "|  | |  | |  |       " + eol +
            "+--+ +--+ +--+       " + eol +
            "               +----+" + eol +
            "               |    |" + eol +
            "               |    |" + eol +
            "               +----+" in {
                field.mesh(25,4,2,15,2,1,1) should be (
                    "               +----+" + eol +
                    "               |    |" + eol +
                    "               |    |" + eol +
                    "               +----+" + eol + eol +
                    "+--+ +--+ +--+ " + eol +
                    "|  | |  | |  | " + eol +
                    "+--+ +--+ +--+ " + eol +
                    "|  | |  | |  | " + eol +
                    "+--+ +--+ +--+ " + eol +
                    "|  | |  | |  | " + eol +
                    "+--+ +--+ +--+ " + eol + eol +
                    "-------------------------" + eol * 2 +
                    "+--+ +--+ +--+ " + eol +
                    "|  | |  | |  | " + eol +
                    "+--+ +--+ +--+ " + eol +
                    "|  | |  | |  | " + eol +
                    "+--+ +--+ +--+ " + eol +
                    "|  | |  | |  | " + eol +
                    "+--+ +--+ +--+ " + eol + eol +
                    "               +----+" + eol +
                    "               |    |" + eol +
                    "               |    |" + eol +
                    "               +----+" + eol)

            }



        }
    }
}