package retcalc

object RetCalc {
  def nbOfMonthSaving(interestRate:Double = 0.0033333333333333335, nbOfMonthinRetirement:Int = 120,
                      netIncom: Int, curentExpence: Int, initialCapital: Int) = {
    def loop(months: Int):Int = {
      val (capitalAtRetirement, capitalAfterDeath)= simulatePlan(
        interestRate = interestRate,
        nbOfMonthsSaving = months, nbOfMonthsInRetirement = nbOfMonthinRetirement,
        netIncome = netIncom, currentExpenses = curentExpence, initialCapital = initialCapital)
      println("*********************************capitalAtRetirement : " + capitalAtRetirement)
      println("************************************capitalAfterDeath : " + capitalAfterDeath)
      if(capitalAfterDeath > 0) months else loop(months +1)
    }
    loop(0)
  }
  def simulatePlan(interestRate: Double,
                   nbOfMonthsSaving: Int, nbOfMonthsInRetirement: Int,
                   netIncome: Int, currentExpenses: Int, initialCapital:
                   Double) : (Double, Double) = {
    val capitalAtRetirement = futureCapital(FixedReturns(interestRate), nbOfMonths = nbOfMonthsSaving,
      netIncome = netIncome, currentExpenses = currentExpenses, initialCapital = initialCapital)

    val capitalAfterDeath = futureCapital(FixedReturns(interestRate), nbOfMonths = nbOfMonthsInRetirement,
      netIncome = 0, currentExpenses = currentExpenses, initialCapital = capitalAtRetirement)

    println("*********************************capitalAtRetirement : " + capitalAtRetirement)
    println("************************************capitalAfterDeath : " + capitalAfterDeath)

    (capitalAtRetirement, capitalAfterDeath)
  }
  def futureCapital(retunrns: Returns, nbOfMonths: Int,
                    netIncome: Int, currentExpenses: Int, initialCapital: Double):Double = {
    val monthlySavings = netIncome - currentExpenses

    /*def nextCapital(accumulated: Double, month:Int): Double  = {
      println(s"accumulated: ${accumulated}, Month : ${month}")
      accumulated * (1+interestRate) + monthlySavings
    }
*/

  /*  (0 until nbOfMonths).foldLeft(initialCapital)(nextCapital) */

    (0 until nbOfMonths).foldLeft(initialCapital)(
      (accumulated, month) => {
        println(s"accumulated: ${accumulated}, Month : ${month}");
        accumulated * Returns.monthlyRate(retunrns, month) + monthlySavings
      }
    )
  }

}
