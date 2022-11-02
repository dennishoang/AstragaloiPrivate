import model.Field2
import model.Dice

//import scala.annotation.meta.field.apply

@main def hello: Unit =
  val field = new Field2(3, Dice.Empty)
  println(field.toString)
  val field2 = field.put(Dice.ONE, 0, 0, field.matrix_1)
  val field3 = field.put(Dice.TWO, 0, 1, field.matrix_2)
  println(field2.toString)
  println(field3.toString)


