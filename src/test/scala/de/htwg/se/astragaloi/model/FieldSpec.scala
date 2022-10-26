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
            "have a bar as a String of a form '+-----+     +-----+     +-----+     '" in {
                field.bars(5,5,0) should be ("+-----+     +-----+     +-----+     " + eol)
            }
            "have a scalable bar" in {
                field1.bars(3,3,5) should be ("     +---+   +---+   +---+   " + eol)
                field2.bars(1,1,1) should be (" +-+ +-+ +-+ " + eol)
                field2.bars(4,1,2) should be ("  +----+ +----+ +----+ " + eol)
            }
            "have a cell as a String of a form '|     |     |     |     |     |     '" in {
                field.cells(5,5,0) should be ("|     |     |     |     |     |     " + eol)
            }
            "have a scalable cells" in {
                field1.cells(2,4,1) should be (" |  |    |  |    |  |    " + eol)
                field2.cells(5,0,3) should be ("   |     ||     ||     |" + eol)
                field.cells(0,2,0) should be ("||  ||  ||  " + eol)
            }
        }
    }
}

