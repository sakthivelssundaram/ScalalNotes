//package retcalc
//import org.scalatest.{matchers, wordspec, _}
import org.scalactic.{Equality, TolerantNumerics, TypeCheckedTripleEquals}
import org.scalatest.wordspec._
import org.scalatest.matchers.should._
//import org.scalatest.matchers


class RetCalSpec extends AnyWordSpec with Matchers with TypeCheckedTripleEquals
{

  implicit val doubleEquality: Equality[Double] =
    TolerantNumerics.tolerantDoubleEquality(0.0001)


  "RetCalc.futureCapital" should {
    "calculate the amount of savings I will have in n months" in {
      val actual = RetCalc.futureCapital(
        interestRate = 0.04 / 12, nbOfMonths = 25 * 12,
        netIncome = 3000, currentExpenses = 2000,
        initialCapital = 10000)
      val expected = 541267.1990
      actual should ===(expected)
    }

  }
