import de.htwg.se.astragaloi.util.Observer

import de.htwg.se.astragaloi.controller.Controller
import de.htwg.se.astragaloi.aview.TUI
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._



class ObserverSpec extends AnyWordSpec {

    class ObserverTest(controller: Controller) extends Observer:

        "The Observable" should {
            "be added" in {
                controller.add(this) should be (controller.subscribers)
            }
            "be removed" in {
                controller.add(this)
                controller.remove(this) should be (controller.subscribers)
            }
            "notify the Observer" in {
                controller.add(this)
                controller.notifyObservers should be (this.update)
            }
        }
}
