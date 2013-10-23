package common

trait Player {

  def makeMove( board: Board ): Board
  def getName(): String
  def getMark(): Char
  
}