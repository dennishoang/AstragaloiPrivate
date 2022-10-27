import model.Field

//import scala.annotation.meta.field.apply

@main def hello: Unit =
  val field = Field()
  println(field.mesh(25,4,2,15,2,1,1))



