package common

object MuckWithHumanPlayer {
  
  def main( args: Array[String] ) {
    val player = new HumanPlayer( "bob", 'b' )
    val boardT1 = player.makeMove( new Board() )
    println( boardT1 )
    val boardT2 = player.makeMove( boardT1 )
    println( boardT2 )
    
  }
}