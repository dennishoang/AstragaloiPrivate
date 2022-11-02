package model

case class Player (name: String, points: Int)

    val noah = Player(name = "Noah", points = 0)
    val dennis = Player(name = "Dennis", points = 0)

    object Player:
        val list = List(noah, dennis)
        def next = list.iterator.next
