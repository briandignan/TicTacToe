package common

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

  trait BoardData {
    def strToBoard( str: String ): Board = {
      val lines = str.split("\n")
      new Board( Vector(
          Vector(lines(0).charAt(0), lines(0).charAt(1), lines(0).charAt(2)),          
          Vector(lines(1).charAt(0), lines(1).charAt(1), lines(1).charAt(2)),          
          Vector(lines(2).charAt(0), lines(2).charAt(1), lines(2).charAt(2))))
    }
    val emptyBoard = new Board()
    val gameOverNoWinner = strToBoard(
        "xyx\n" +
        "yxy\n" +
        "yxy")
    val xWinTopRow = strToBoard(
        "xxx\n" +
        "yy \n" +
        "y  ")
    val yWinTopRow = strToBoard(
        "yyy\n" +
        "xx \n" +
        "x  ")
    val xWinMidRow = strToBoard(
        "y  \n" +
        "xxx\n" +
        "y  ")    
    val xWinBotRow = strToBoard(
        "y  \n" +
        "y  \n" +
        "xxx")    
    val xWinLeftCol = strToBoard(
        "xy \n" +
        "xy \n" +
        "x  ")
    val xWinMidCol = strToBoard(
        "yx \n" +
        "yx \n" +
        " x ")
    val xWinRightCol = strToBoard(
        "y x\n" +
        "y x\n" +
        "  x")
    val xWinLtRDiag = strToBoard(
        "x  \n" +
        "yxy\n" +
        "  x")
    val xWinRtLDiag = strToBoard(
        "  x\n" +
        "yxy\n" +
        "x  ")
    val noWinnerOne = strToBoard(
        "xy \n" +
        "yx \n" +
        "   ")
    val noWinnerTwo = strToBoard(
        "xyy\n" +
        "yx \n" +
        " x ")
    val gameOneMoveOne = strToBoard(
        "x  \n" +
        "   \n" +
        "   ")
    val gameOneMoveTwo = strToBoard(
        "x  \n" +
        " y \n" +
        "   ")
    val gameOneMoveThree = strToBoard(
        "x  \n" +
        " y \n" +
        "  x")
    val gameOneMoveFour = strToBoard(
        "x  \n" +
        " y \n" +
        "y x")
    val gameOneMoveFive = strToBoard(
        "x x\n" +
        " y \n" +
        "y x")
    val gameOneMoveSix = strToBoard(
        "x x\n" +
        " yy\n" +
        "y x")
    val gameOneMoveSeven = strToBoard(
        "xxx\n" +
        " yy\n" +
        "y x")
        
    val gameBothOneTurnFromWinning = strToBoard(
        "x x\n" +
        " yy\n" +
        "y x")
        
    val gameBothCantWin = strToBoard(
        "xyx\n" +
        "xxy\n" +
        "y x")
        
        
    val gameOneMoveSixAltOne = strToBoard(
        "xxx\n" +
        " yy\n" +
        "y x")
    val gameOneMoveSixAltTwo = strToBoard(
        "x x\n" +
        "xyy\n" +
        "y x")
    val gameOneMoveSixAltThree = strToBoard(
        "x x\n" +
        " yy\n" +
        "yxx")
        
        
        
    val gameOneMoveSevenExpectedX = strToBoard(
        "xxx\n" +
        "xyy\n" +
        "y x")
    val gameOneMoveSevenExpectedY = strToBoard(
        "xyx\n" +
        "xyy\n" +
        "y x")
    val gameOneMoveFiveExpectedX = strToBoard(
        "x x\n" +
        " y \n" +
        "y x")
    val gameOneMoveFiveExpectedY = strToBoard(
        "x y\n" +
        " y \n" +
        "y x")
        
        
    val gameOneMoveFourExpectedY = strToBoard(
        "x  \n" +
        " y \n" +
        "  x")
        
  }


@RunWith(classOf[JUnitRunner])
class BoardTests extends FunSuite {

  
  
  test("winner of game various scenarios") {
    new BoardData {
      assert(None === gameOverNoWinner.winnerOfGame)
      assert(Some('x') === xWinTopRow.winnerOfGame)
      assert(Some('y') === yWinTopRow.winnerOfGame)
      assert(Some('x') === xWinMidRow.winnerOfGame)
      assert(Some('x') === xWinBotRow.winnerOfGame)
      assert(Some('x') === xWinLeftCol.winnerOfGame)
      assert(Some('x') === xWinMidCol.winnerOfGame)
      assert(Some('x') === xWinRightCol.winnerOfGame)
      assert(Some('x') === xWinLtRDiag.winnerOfGame)
      assert(Some('x') === xWinRtLDiag.winnerOfGame)
      assert(None === noWinnerOne.winnerOfGame)
      assert(None === noWinnerTwo.winnerOfGame)
      assert(None === gameOneMoveOne.winnerOfGame)
      assert(None === gameOneMoveTwo.winnerOfGame)
      assert(None === gameOneMoveThree.winnerOfGame)
      assert(None === gameOneMoveFour.winnerOfGame)
      assert(None === gameOneMoveFive.winnerOfGame)
      assert(None === gameOneMoveSix.winnerOfGame)
      assert(Some('x') === gameOneMoveSeven.winnerOfGame)
    }
  }
  
  test("isGameOver various scenarios") {
    new BoardData {
      assert(true === gameOverNoWinner.gameIsOver)
      assert(true === xWinTopRow.gameIsOver)
      assert(true === yWinTopRow.gameIsOver)
      assert(true === xWinMidRow.gameIsOver)
      assert(true === xWinBotRow.gameIsOver)
      assert(true === xWinLeftCol.gameIsOver)
      assert(true === xWinMidCol.gameIsOver)
      assert(true === xWinRightCol.gameIsOver)
      assert(true === xWinLtRDiag.gameIsOver)
      assert(true === xWinRtLDiag.gameIsOver)
      assert(false === noWinnerOne.gameIsOver)
      assert(false === noWinnerTwo.gameIsOver)
      assert(false === gameOneMoveOne.gameIsOver)
      assert(false === gameOneMoveTwo.gameIsOver)
      assert(false === gameOneMoveThree.gameIsOver)
      assert(false === gameOneMoveFour.gameIsOver)
      assert(false === gameOneMoveFive.gameIsOver)
      assert(false === gameOneMoveSix.gameIsOver)
      assert(true === gameOneMoveSeven.gameIsOver)
    }
  }
  
  test("toCompactString") {
    new BoardData {
      assert( "xyx\nyxy\nyxy" === gameOverNoWinner.toCompactString )
    }
  }
  
  
  test("Attempt to place more than two different marks on a board") {
    new BoardData {
      
      // Confirm that an 'x' can be placed at c3 without exception
      gameOneMoveTwo.makeMove(new Coordinate('c','3'), 'x')
      intercept[IllegalArgumentException] {
        // Confirm that placing a 'z' at c3 (on the same original board) produces an exception
        gameOneMoveTwo.makeMove(new Coordinate('c','3'), 'z')
      }
      
    }
  }
  
  
  
}


