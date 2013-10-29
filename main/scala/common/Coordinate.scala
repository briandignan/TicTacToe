package common

class Coordinate( val row: Int, val col: Int ) {
  if ( row < 0 || col < 0 ) {
    throw new IllegalArgumentException("Row and Column must positive")
  }
  
  def this( rowChar: Char, colChar: Char ) = this( rowChar.toLower - 97, colChar - 49 )
  
  override def toString(): String = "(" + row + " / " + col + ")"
}
