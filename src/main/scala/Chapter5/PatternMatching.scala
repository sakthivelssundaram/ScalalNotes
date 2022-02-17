package Chapter5

object PatternMatching extends App{

//## nested matching
// matching case class with string patten on names
  case class Person (name: String, title : String )

  def greet(p: Person) = p match {
    case Person(s"$firstName $lastName", title) => println(s"Hello $title $lastName")
    case Person(name, title) => println(s"Hello $title $name")
  }

  greet(Person("Sakthivel Somasundaram","Mr"))

  greet(Person("Sakthivel","Mr"))

  // multilevel pattenrmatching
  // multiple case class => string format

  def greet2(husband: Person, wife: Person) = (husband, wife) match {
    case (Person(s"$fName $lName", _), Person(s"$fName2 $lName2", _)) if lName == lName2 => println(s"Hello Mr & Ms $lName")
    case(Person(name1, _ ), Person(name2, _ )) => println(s"Hello $name1 and $name2")
  }

  greet2(Person("James Bond","Mr"),Person("Jane Bond","Ms"))
  greet2(Person("Bond","Mr"),Person("Jane","Ms"))

  //pattern matching on for loop
  val a  = Array[(Int, String)]((1,"One"),(2,"Two"),(3,"Three"))
  for ((i,s) <- a) println(s+i)

}
