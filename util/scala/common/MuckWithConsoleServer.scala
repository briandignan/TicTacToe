package common

object MuckWithConsoleServer {

  def main( args: Array[String] ) {
    val playerOne = new HumanPlayer( "bob", 'x' )
    val playerTwo = new HumanPlayer( "jane", 'y' )
    val server = new ConsoleServer( new Board(), playerOne, playerTwo )
    
    server.playGame
  
  
  }
}