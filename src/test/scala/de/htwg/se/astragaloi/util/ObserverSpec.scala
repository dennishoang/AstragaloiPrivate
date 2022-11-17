package de.htwg.se.astragaloi.util

//import util.Observable
//import de.htwg.se.astragaloi.controller.Controller
//import de.htwg.se.astragaloi.aview.TUI
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._



class ObserverSpec extends AnyWordSpec {
    /*
    class ObserverTest(controller: Controller) extends Observer:
        def get: Controller = controller
        override def update = println(controller.field.toString)

    val testobs = new ObserverTest(new Controller(new Field(3, 2, Dice.Empty)))

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
        */
    "An observable" when {
        "new observer" should {
            var updated = false
            val observable = new Observable
            val observer = new Observer {
                override def update: Unit = updated = true
            }

            observable.add(observer)

            "have a subscriber" in {
                observable.subscribers.contains(observer) should be(true)
            }
            "have subscriber removed" in {
                observable.remove(observer)
                observable.subscribers.contains(observer) should be(false)
            }
            "have a subscriber add" in {
                observable.add(observer)
                observable.subscribers.contains(observer) should be(true)
            }
            "remove an Observer" in {
                observable.remove(observer)
                observable.subscribers should not contain (observer)
            }
            "add an Observer" in {
                observable.add(observer)
                observable.subscribers should contain (observer)
            }

        }
    }
}
