package Chapter5




object Expression extends App {

  sealed trait Expr
  case class Binop(left: Expr, op: String, right: Expr) extends Expr
  case class Literal(value: Int) extends Expr
  case class Variable(name : String) extends Expr


  def stringify(expr: Expr): String = expr match {
    case Binop(left, op, right) => s"(${stringify (left)} ${op} ${stringify(right)})"
    case Literal(value) => value.toString
    case Variable(name) => name
  }

  //println(stringify(Binop(Variable("x"), "+", Literal(1))))

  def simplify(expr: Expr): Expr = {
    val res = expr match {
      case Binop(Literal(left), "+", Literal(right)) => Literal(left + right)
      case Binop(Literal(left), "-", Literal(right)) => Literal(left - right)
      case Binop(Literal(left), "*", Literal(right)) => Literal(left * right)

      case Binop(left, "*", Literal(1)) => simplify(left)
      case Binop(Literal(1), "*", right) => simplify(right)

      case Binop(left, "+", Literal(0)) => simplify(left)
      case Binop(Literal(0), "+", right) => simplify(right)

      case Binop(left, "-", Literal(0)) => simplify(left)

      case Binop(left, "*", Literal(0)) => Literal(0)
      case Binop(Literal(0), "*", right) => Literal(0)

      case Binop(left, "*", right) => Binop(simplify(left), "*", simplify(right))
      case Binop(left, "-", right) => Binop(simplify(left), "-", simplify(right))
      case Binop(left, "+", right) => Binop(simplify(left), "+", simplify(right))

      case Literal(value) => Literal(value)
      case Variable(name) => Variable(name)

    }
    res
  }

  //val example1 = Binop(Literal(1), "+", Literal(1))
  //val example2 = Binop(Binop(Literal(1), "+", Literal(1)), "*", Variable("x"))
  

  println(stringify(simplify(example1)))
  println(stringify(simplify(example2)))

}