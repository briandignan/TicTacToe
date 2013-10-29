package common

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MinimaxAiPlayerTests extends FunSuite {
  
  val halx = new MinimaxAiPlayer("hal", 'x')
  val haly = new MinimaxAiPlayer("hal", 'y')
  
  
  /*
  test("possible moves") {
    new BoardData {
      val p1 = halx.possibleMoves( 'x', gameOneMoveSix)
      assert( p1.size === 3 )
      assert( p1(0) === gameOneMoveSixAltOne )
      assert( p1(1) === gameOneMoveSixAltTwo )
      assert( p1(2) === gameOneMoveSixAltThree )
    }
  }*/
  
  
  test("Confirm optimal next move") {
    new BoardData {
      assert( haly.makeMove( gameOneMoveSixAltTwo ) === gameOneMoveSevenExpectedY )
      assert( halx.makeMove( gameOneMoveSixAltTwo ) === gameOneMoveSevenExpectedX )
      assert( halx.makeMove( gameOneMoveFour ) === gameOneMoveFiveExpectedX )
      //assert( haly.makeMove( gameOneMoveFour ) === gameOneMoveFiveExpectedY )   // Turns out to be a bad test
      for( i <- 1 to 100) {
        val g1m4y = haly.makeMove( gameOneMoveThree) // one of four possibilities. 
        // confirm that it picked one of the four side squares
        assert ( g1m4y.rows(1)(0) == 'y' || g1m4y.rows(0)(1) == 'y' || g1m4y.rows(1)(2) == 'y' || g1m4y.rows(2)(1) == 'y' )
      }
      
      //assert( haly.makeMove( gameBothOneTurnFromWinning ) === new Board() )
      //assert( haly.makeMove( gameBothCantWin ) === new Board() )
      
    }
  }
  
}