package common

import scala.util.Random

class MinimaxAiPlayer( name: String, mark: Char) extends Player {

  def getName(): String = name
  def getMark(): Char = mark
  
  def makeMove( board: Board ): Board = {
    
    /**
     * To simulate play scenarios, it's required to know how to fill in opponent pieces consistently. 
     * If the board is empty, return some character that's different than this player's mark. If the board
     * is not empty, then the opponent must have placed a mark somewhere. Determine that mark and return it. 
     */
    val opponentMark: Char = {
      val previousUniqueMarks = board.uniqueMarks
      if ( previousUniqueMarks.isEmpty ) {
        // The board is empty. Return a Char one higher than this player's mark. It doesn't matter that it won't match the opponent's actual mark. 
        (mark + 1).toChar
        
      } else if ( previousUniqueMarks.size == 1 ) {
        // The opponent has made a move, but we haven't made our first move yet. 
        previousUniqueMarks.head
        
      } else {
        // At least one move has been made by each player. 
        if ( previousUniqueMarks.head == mark ) {
          previousUniqueMarks.tail.head
        } else {
          previousUniqueMarks.head
        }
        
      }
    }
    
    
    
    
    
    
    
    //def minimaxAlt( board: Board ): ( )
    
    
    
    // Write the minimax function which will return the highest possible score for this player, or the lowest possible score for the other player. 
    
    // Also consider returning a tuple of score/board
    // The base call to minimax needs to build a list of boards that have the best cost, and return one at random. 
    
    // TODO Use groupBy to create mapping of cost -> Board
    
    def minimax( board: Board ): Board = {
      
      def minimaxHelper( player: Char, board: Board ): Int = {
        //println( "minimaxHelper called for player " + player + " with board " + board + "\n" )
        //println( "gameIsOver: " + board.gameIsOver )
        
        if ( board.gameIsOver ) {
          // The game is over. Return the result. 
          val winner = board.winnerOfGame
          //println( "Winner: " + winner )
          
          //println( "Potential Result: " + board + " where " + ( if ( winner == None ) winner else winner.get ) + " wins " )
          if ( winner == None ) {
            // It was a tie. Return 0
            //println( "TIE" )
            0
          } else if ( winner.get == mark ) {
            // We won. Return 1
            //println( mark + " wins" )
            1 
          } else {
            // We lost. Return -
            //println( opponentMark + " wins" )
            -1
          }
          
        } else {
          // The game isn't over yet. 
          val moves = possibleMoves( player, board )
          
          
          if ( player == mark ) {
            // It's THIS player's turn. Return the max value of any possible move
            moves.foldLeft(-1)( ( max: Int, board: Board ) => ( Math.max( max, minimaxHelper( opponentMark, board ) ) ) )
            
          } else if ( player == opponentMark ){
            // It's the OTHER player's turn. Return the min value of any possible move
            moves.foldLeft(1)( ( min: Int, board: Board ) => ( Math.min( min, minimaxHelper( mark, board ) ) ) )
            
          } else {
            throw new IllegalStateException("Called minimaxHelper with unknown mark of " + player )
            
          }
        }
      }
      
      //println( "Calling minimax for " + mark + " on " + board )
      
      // Create the list of all moves that this player is able to make. 
      val moves = possibleMoves( mark, board )
      
      // Determine the highest valued boards. Create a Vector of (value, board)
      val valuesAndMoves = moves.foldLeft( Vector[(Int, Board)]() )( (acc, b) => acc :+ ( minimaxHelper( opponentMark, b ), b) )
      
      //printValuesAndMoves( valuesAndMoves )
      
      // used for reverse sorting
      def Desc[T : Ordering] = implicitly[Ordering[T]].reverse
      
      val valesAndMovesSortedByValue = valuesAndMoves.sortBy( tup => (tup._1) )(Desc)
      
      val maxValue = valesAndMovesSortedByValue(0)._1
      //println( "max value: " + maxValue )
      
      val valuesAndMoveMaxValueOnly = valesAndMovesSortedByValue.filter( tup => tup._1 == maxValue )
      
      // Of all moves that are equally ideal, choose one from random
      valuesAndMoveMaxValueOnly( (new Random).nextInt( valuesAndMoveMaxValueOnly.length ) )._2
      
    }
    
    // Return the board with the ideal move
    val idealMove = minimax( board )
    println( "Ideal Move: " + idealMove )
    idealMove
    
  }
  
  
    /**
     * Returns a Vector of all 'potential' Board objects that the player could potentially move to. For example,
     * if there are 7 empty squares, it will return 7 potential Board objects, one for each potential move.
     * 
     * The structure of this function is basically a nested for loop, but written recursively. 
     * 
     * TODO Think about a better way to write this. 
     */
    def possibleMoves( player: Char, board: Board ): Vector[Board] = {
      
      //println( "Possible moves called for player " + player + " with board " + board + "\n")
      
      def iterRows( rowNum: Int ): Vector[Board] = {
        def iterCols( colNum: Int): Vector[Board] = {
          if ( colNum >= board.rows(rowNum).length ) {
            Vector[Board]()
            
          } else {
            if ( board.rows(rowNum)(colNum) == ' ' ) {
              // This space is empty. Return a new board with this space filled
              board.makeMove( new Coordinate( rowNum, colNum ) , player) +: iterCols( colNum + 1 )
              
            } else {
              // This space is not empty. Keep searching
              iterCols( colNum + 1 )
              
            }
          }
        }
        
        if ( rowNum >= board.rows.length ) {
          Vector[Board]()
          
        } else {
          iterCols(0) ++ iterRows(rowNum + 1)
          
        }
      }
      iterRows( 0 )
    }
    
    
    def printValuesAndMoves( vec: Vector[( Int, Board )] ): Unit = {
      vec.foreach( tup => println( "Potential board with value: " + tup._1 + tup._2 + "\n\n" ) )
    }
  
}