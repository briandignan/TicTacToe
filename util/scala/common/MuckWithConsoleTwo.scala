package common

import java.io.StringReader

object MuckWithConsoleTwo {
  def main( args: Array[String] ) {
    //println( "Hello world" )
    //println( Console.readLine )
    //println( Console.readLine )
    //println( Console.readLine )
    
    val reader = new StringReader( "blahOne\nblahTwo\nblahThree\n" )
    
    Console.setIn( reader )
    
    println( Console.readLine )
    println( Console.readLine )
    println( Console.readLine )
    
  }
  
}