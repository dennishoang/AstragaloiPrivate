import de.htwg.se.astragaloi.util.Observer

import de.htwg.se.astragaloi.controller.Controller
import de.htwg.se.astragaloi.model.Field
import de.htwg.se.astragaloi.model.Dice
import de.htwg.se.astragaloi.aview.TUI
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._



class ObserverSpec extends AnyWordSpec {

    class ObserverTest(controller: Controller) extends Observer:
        def get: Controller = controller
        override def update = println(controller.field.toString)

    val testobs = new ObserverTest(new Controller(new Field(3, 2, Dice.Empty)))

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
            testobs.get.add(testobs)
            testobs.get.notifyObservers should be (testobs.update)
        }
    }

    "The Observer" should {
        "be updated" in {
            testobs.update should equal (println(testobs.get.field.toString))
        }
    }

 }
