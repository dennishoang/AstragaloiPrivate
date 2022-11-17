package de.htwg.se.astragaloi.model

import de.htwg.se.astragaloi.aview.TUI
import de.htwg.se.astragaloi.controller.Controller
import model.Field
import model.Dice
import model.Move

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class TUISpec extends AnyWordSpec {

    "A TUI" should {
        val tui = new TUI(Controller(new Field(3, 2, Dice.Empty)))
        "get Input and print loop" in { // 00 als Testeingabe
            tui.getInputAndPrintLoop(0, "00") should be (println(tui.controller.field.toString))
        }
        "be updated" in {
            tui.update should be (println(tui.controller.field.toString))
        }
        "recognize the input '00' and put the number one in matrix 0 and field 00 " in {
            tui.analyseInput("00", Dice.ONE, 0) should be (Some(Move(Dice.ONE, 0, 0, 0)))
        }


    }
}
