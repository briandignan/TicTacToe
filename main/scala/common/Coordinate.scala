package common

class Coordinate( rowChar: Char, colChar: Char ) {
  if ( rowChar.toLower < 97 || colChar < 48 ) {
    throw new IllegalArgumentException("Row and Column must positive")
  }
  
  val row = rowChar - 97
  val col = colChar - 49
  
  override def toString(): String = "(" + row + " / " + col + ")"
}
