package common

import scala.annotation.tailrec

class Board( val rows: Vector[Vector[Char]] ) {
  if ( rows.length != 3 || rows(0).length != 3 || rows(1).length != 3 || rows(2).length != 3 ) {
    throw new IllegalArgumentException("Invalid contents vector shape")
  }
  
  def this() = this( Vector( Vector(' ', ' ', ' '), Vector(' ', ' ', ' '), Vector(' ', ' ', ' '))) // 3x3 board with empty squares
  
  def makeMove( coordinate: Coordinate, mark: Char ): Board = {
    
    val row = coordinate.row
    val col = coordinate.col
    
    if ( row > rows.length || row < 0 || col > rows.length || col < 0 ) { 
      throw new IllegalArgumentException("row and columns values are not within bounds")
    } else if ( rows(row)(col) != ' ' ) {
      throw new IllegalArgumentException("Unable to place " + mark + " in " + row + "/" + col + " because " + rows(row)(col) + " was already placed there.")
    } else if ( gameIsOver ) {
      throw new IllegalStateException("Unable to make a move because the game is already over")
    }
    
    if ( uniqueMarks.+(mark).size > 2 ) {
      throw new IllegalArgumentException("Unable to place '" + mark + "' on board as there are already two other unique marks on the board." )
    }
    
    new Board(rows.updated( row, rows(row).updated(col, mark)))
    
  }
  
  
  /**
   * Return the set of all unique marks, not including the ' ' space char
   */
  def uniqueMarks: Set[Char] = 
    rows.foldLeft(Set[Char]())((acc: Set[Char], row: Vector[Char]) => 
      row.foldLeft(acc)((acc: Set[Char], c: Char) => 
        if (c == ' ') acc else acc.+(c) ))
  
  
  def gameIsOver(): Boolean = {
    // Two cases. Either all squares have been marked, or one of the two players got a line
    ( winnerOfGame != None || unmarkedSquares == 0 )
  }
  
  def unmarkedSquares(): Int = {
    // Sum the quantity of squares that are empty (contain a space character) in every row
    rows.foldLeft(0)((acc: Int, row: Vector[Char]) => 
      row.foldLeft(acc)((acc: Int, c: Char) => 
        if (c == ' ') acc + 1 else acc ))
  }
  
  
  def winnerOfGame(): Option[Char] = {
    // Check each of eight lines for three identical (non empty) marks
    
    @tailrec
    def findWinner( remainingLines: Vector[Vector[Char]] ): Option[Char] = {
      if ( remainingLines.isEmpty ) {
        None
        
      } else {
        // Determine whether this line contains three identical values that are not spaces
        
        val line = remainingLines.head
        val lineSet: Set[Char] = line.toSet
        
        if ( lineSet.size == 1 && line(0) != ' ' ) {
          Option(line(0))
          
        } else {
          findWinner( remainingLines.tail )
        
        }
      } 
    }
    
    findWinner( boardLines )
    
  }
  
  
  /**
   * Returns a Vector of eight size-three Vector[Char] (lines). These lines represents the eight possible
   * ways to win the game.  
   */
  private def boardLines: Vector[Vector[Char]] = {
      val columns: Vector[Vector[Char]] = Vector(
	      Vector(rows(0)(0), rows(1)(0), rows(2)(0)),
	      Vector(rows(0)(1), rows(1)(1), rows(2)(1)),
	      Vector(rows(0)(2), rows(1)(2), rows(2)(2)))
      
	  val diagonals: Vector[Vector[Char]] = Vector(
	      Vector(rows(0)(0), rows(1)(1), rows(2)(2)),
	      Vector(rows(0)(2), rows(1)(1), rows(2)(0)))
	      
	  Vector(
	      rows(0), rows(1), rows(2),
	      columns(0), columns(1), columns(2),
	      diagonals(0), diagonals(1))
  }
  
  override def toString(): String = {
    "\n" +
    "   1   2   3  \n" +
    " -------------\n" +
    rowToString( 'a' ) +
    " |---|---|---|\n" + 
    rowToString( 'b' ) +
    " |---|---|---|\n" +  
    rowToString( 'c' ) +
    " |---|---|---|\n"
  }
  
  /**
   * converts the board data to a three-line string representing the moves of the game
   */
  def toCompactString(): String = {
    "" + 
    rows(0)(0) + rows(0)(1) + rows(0)(2) + "\n" +
    rows(1)(0) + rows(1)(1) + rows(1)(2) + "\n" +
    rows(2)(0) + rows(2)(1) + rows(2)(2)
    
  }
  
  
  private def rowToString( row: Char ): String = {
    val rowNum = row - 97
    row +"| %c | %c | %c |\n".format( rows(rowNum)(0), rows(rowNum)(1), rows(rowNum)(2) )
  }
  
  
  override def equals( other: Any): Boolean = other match {
    case other: Board => other.rows == this.rows && other.getClass() == this.getClass() 
    case _ => false
  }
  
  
}
