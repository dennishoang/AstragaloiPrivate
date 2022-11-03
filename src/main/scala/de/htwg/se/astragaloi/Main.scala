import model.TUI
import model.Dice


@main def game: Unit =
  val field = new TUI(3, Dice.Empty)
  println(field.toString)
  val field2 = field.put(Dice.ONE, 0, 0, 0)
  val field3 = field.put(Dice.TWO, 1, 0, 1)
  println(field2.toString)
  println(field3.toString)


