/*
package de.htwg.se.astragaloi.model

import de.htwg.se.astragaloi.aview.TUI
import de.htwg.se.astragaloi.controller.Controller
import de.htwg.se.astragaloi.model.Field
import de.htwg.se.astragaloi.model.Dice
import de.htwg.se.astragaloi.model.Move
import de.htwg.se.astragaloi.util.Observer
import de.htwg.se.astragaloi.util.Event


import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class TUISpec extends AnyWordSpec {

    "A TUI" should {

        val tui = new TUI(Controller(new Field(3, 2, Dice.Empty, 0), 0))
        "be updated" in {
            tui.update(Event.Quit) should (sys.exit(0))
            tui.update(Event.Move) should (println(tui.controller.field.toString))
        }
        "recognize the input '00' and put the number one in matrix 0 and field 00 " in {
            tui.analyseInput(new Move(Dice.ONE, 0, 0, Dice.SIX), "1") should be (Some(Move(Dice.ONE, 0, 1, Dice.SIX)))
        }



    }
}
*/