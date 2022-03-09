## Retirement Fund Calculator
#### This is a small project to calcualte the retirement of fund.

#####
    * class and trait:
        Product Type and Sum type;
        
    In functional programming there are two main types of Algebric data types, they are 
        1. Product type eg., tuples
            val a = (1,4) => here both 1 and 4 are the part of the datatype and there is no choice between then both the value form a product type

        2. Sum Type eg., traite or something
            val a:Option[T] = Some[T] | None => here there is a choice for the value that a can contain it can me some[T] or None. 

    if you have to represnt Sum type in code you can use  traite as below:

        sealed trait Shape
        case class Circle(diameter: Double) extends Shape
        case class Rectangle(width: Double, height: Double) extends Shape
        
    : Here Shape is  a Algebric Data type:

        Sum type : composed of Circle or Rectangle (we have a choice for shape)
            val s1: Shape = new Circle(10) | new Rectangle(10,10)
        Product Type: class Rectangle is a product type and it must have both length and breadth. 
            Rectangle(10,10)