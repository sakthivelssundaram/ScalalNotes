package retcalc

sealed trait Returns

case class FixedReturns(annualRate: Double) extends Returns

case class VariableReturn(monthId: String, monthlyRate: Double)

case class VariableReturns(returns: Vector[VariableReturn]) extends Returns {
  def fromUntil(monthFrom: String, monthUntil: String): VariableReturns = {
    VariableReturns(returns.dropWhile(_.monthId != monthFrom).takeWhile(_.monthId !=monthUntil))
  }

}


object Returns{
  def monthlyRate(retuns: Returns, Month: Int): Double = retuns match{
    case FixedReturns(r) => r / 12
    case VariableReturns(rs) => rs(Month % rs.length).monthlyRate
  }
}