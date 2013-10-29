package common

object HumanVsMinimaxConsole {

  def main(args: Array[String]): Unit = {
    val human = new HumanPlayer( "bob", 'x' )
    val halx = new MinimaxAiPlayer("hal", 'y')
    
    val server = new ConsoleServer( new Board(), human, halx )
    server.playGame
  }

}