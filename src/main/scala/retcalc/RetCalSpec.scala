package retcalc

import org.scalactic.{Equality, TolerantNumerics, TypeCheckedTripleEquals}
import org.scalatest.wordspec._
import org.scalatest.matchers.should._


class RetCalSpec extends AnyWordSpec with Matchers with TypeCheckedTripleEquals {

  implicit val doubleEquality: Equality[Double] = TolerantNumerics.tolerantDoubleEquality(0.0001)

  /*"RetCalc.futureCapital" should {
    "calculate the amount of savings I will have in n months" in {
      val actual = RetCalc.futureCapital( interestRate = 0.04 / 12, nbOfMonths = 10 * 12, netIncome = 100000, currentExpenses = 95000, initialCapital = 0)
      val expected = 541267.1990
      assert(actual >= expected)
    }

    "calculate how much savings will be left after having taken a pension for n months" in {
      val actual = RetCalc.futureCapital(interestRate = 0.04/12, nbOfMonths = 40*12,netIncome = 0,currentExpenses = 2000,initialCapital = 541267.1990)
      val expected = 309867.5317620648
      actual should === (expected)
    }
  }*/
  //Refactored version with updated interst rate:
  "RetCalc.futureCapital" should {
    "calculate the amount of savings I will have in n months" in {
      val actual = RetCalc.futureCapital( FixedReturns(0.04), nbOfMonths = 10 * 12, netIncome = 100000, currentExpenses = 90000, initialCapital = 0)
      val expected = 541267.1990
      actual should === (expected)
    }

    "calculate how much savings will be left after having taken a pension for n months" in {
      val actual = RetCalc.futureCapital(FixedReturns(0.04), nbOfMonths = 40*12,netIncome = 0,currentExpenses = 2000,initialCapital = 541267.1990)
      val expected = 309867.5317620648
      actual should === (expected)
    }
  }

  "RetCalc.simulatePlan" should {
    "calculate the capital at retirement and the capital after death" in {
      val (capitalAtRetirement, capitalAfterDeath) =
        RetCalc.simulatePlan(interestRate = 0.04 / 12,nbOfMonthsSaving = 25 * 12, nbOfMonthsInRetirement = 40 * 12,netIncome = 3000, currentExpenses = 2000,initialCapital = 10000)
      capitalAtRetirement should === (541267.1990)
      capitalAfterDeath should === (309867.5316)
    }
  }
  "RetCalc.nbOfMonthSaving" should {
    "calculate the number of months that I have to save before i retire" in {
      val actual = RetCalc.nbOfMonthSaving(interestRate = 0.04 / 12, nbOfMonthinRetirement = 10 * 12, netIncom = 100000,
        curentExpence = 95000, initialCapital = 10000)
      val expected = 23 * 12 * 1
      actual should ===(expected)

    }
    "not crash if the resulting nbOfMonths is very high" in {
      val actual = RetCalc.nbOfMonthSaving(
        interestRate = 0.01 / 12, nbOfMonthinRetirement = 40 * 12,
        netIncom = 3000, curentExpence = 2999, initialCapital = 0)
      val expected = 8280
      actual should ===(expected)
    }
  }
}
