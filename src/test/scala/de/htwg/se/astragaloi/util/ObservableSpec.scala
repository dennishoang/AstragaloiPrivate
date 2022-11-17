import de.htwg.se.astragaloi.util.Observer
import de.htwg.se.astragaloi.util.Observable

import de.htwg.se.astragaloi.controller.Controller
import de.htwg.se.astragaloi.model.Field
import de.htwg.se.astragaloi.model.Dice
import de.htwg.se.astragaloi.aview.TUI
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._



class ObservableSpec extends AnyWordSpec {

    class ObservableTest(field: Field) extends Observable

    val testobs = new ObservableTest(new Field(3, 2, Dice.Empty))

    "The Observable" should {
        /*
        "be added" in {
            val subs = testobs.get.add(testobs)
            subs should be (testobs.get.subscribers)
        }
        "be removed" in {
            testobs.get.add(testobs)
            testobs.get.remove(testobs) should be (testobs.get.subscribers)
        }
        */
        "notify the Observer" in {
            val obs = new TUI(new Controller(new Field(3, 2, Dice.Empty)))
            testobs.add(obs)
            testobs.notifyObservers should be (obs.update)
        }
    }

 }
