package common

import scala.annotation.tailrec

class ConsoleServer( board: Board, playerOne: Player, playerTwo: Player ) {
  
  def playGame(): Unit = {
    
    @tailrec
    def playUntilOver( board: Board, player: Player ): Unit = {
      if ( board.gameIsOver ) {
        println( board )
        print( "The game is over. The winner is " )
        val winner = board.winnerOfGame
        if ( winner == None ) {
          println( "no one!" )
        } else if ( winner.get == playerOne.getMark ) {
          println( playerOne.getName )
        } else if ( winner.get == playerTwo.getMark ) {
          println( playerTwo.getName )
        } else {
          throw new IllegalStateException("The game is over, but the winning mark (" + winner.get + ") doesn't match either player")
        }
          
      } else {
        // The game is not over. Play the next turn
        println( board.toString )
        val updatedBoard = player.makeMove( board )
        if ( updatedBoard.unmarkedSquares != (board.unmarkedSquares - 1) ) {
          throw new IllegalStateException("Player attempted to make multiple moves in the same turn.")
        }
        
        val nextPlayer = if ( player == playerOne ) playerTwo else playerOne
        playUntilOver( updatedBoard, nextPlayer )
        
      }
    }
    
    playUntilOver( board, playerOne )
    
  }
  
}
