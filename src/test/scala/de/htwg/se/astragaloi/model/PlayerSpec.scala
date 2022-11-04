package de.htwg.se.astragaloi.model

import model.Player
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class PlayerSpec extends AnyWordSpec {
    "A Player" should {
        val player = Player(name = "Noah", points = 0)
        val player1 = Player(name = "Dan", points = 0)
        "have a name" in {
            player.name should be("Noah")
        }
        "have initially zero points" in {
            player.points should be (0)
        }

    }
}