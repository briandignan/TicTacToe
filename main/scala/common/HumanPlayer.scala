package common

class HumanPlayer( name: String, mark: Char) extends Player {

  def getName() = name
  def getMark() = mark
  def makeMove( board: Board ): Board = {
    
    def getInputAndMakeMove(): Board = {
      val input = Console.readLine
      if ( input.length() != 2 ) {
        println( "Please enter a coordinate in the form xy, where x is the row and y is the column." )
        getInputAndMakeMove
      
      } else {
        val row = input.charAt(0)
        val col = input.charAt(1)
        try {
          val coordinate = new Coordinate( row, col )
          board.makeMove(coordinate, mark)
          
        } catch {
          case e1: IllegalArgumentException => {
            println( e1.getMessage() )
            getInputAndMakeMove
          }
        }
      }
    }
    
    println( "Your turn to move, " + name + ". Enter a coordinate to mark." )
    getInputAndMakeMove
    
  }
  

  
}