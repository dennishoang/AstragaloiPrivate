package de.htwg.se.astragaloi.model

import model.player

class PlayerSpec extends AnyWordSpec {
    "A Player" should {
        val player = Player(name = "Noah", points = pointsp1)
        "have a name" in {
            player.name should be("Noah")
        }
        "have initially zero points" in {
            player.points should be (0)
        }
    }
}