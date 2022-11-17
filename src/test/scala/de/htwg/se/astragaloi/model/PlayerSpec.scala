package de.htwg.se.astragaloi.model

import de.htwg.se.astragaloi.model.Player
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class PlayerSpec extends AnyWordSpec {
    "A Player" should {
        val player = Player(playerID = 0, points = 0)
        val player1 = Player(playerID = 1, points = 0)
        "have a name" in {
            player.playerID should be (0)
            player1.playerID should be (1)
        }
        "have initially zero points" in {
            player.points should be (0)
        }

    }
}