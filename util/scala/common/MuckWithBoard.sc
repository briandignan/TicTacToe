package common

object MuckWithBoard {

  val emptyBoard = new Board()                    //> emptyBoard  : common.Board = 
                                                  //|    1   2   3  
                                                  //|  -------------
                                                  //| a|   |   |   |
                                                  //|  |---|---|---|
                                                  //| b|   |   |   |
                                                  //|  |---|---|---|
                                                  //| c|   |   |   |
                                                  //|  |---|---|---|
                                                  //| 
  val t1 = emptyBoard.makeMove(0, 0, 'x')         //> t1  : common.Board = 
                                                  //|    1   2   3  
                                                  //|  -------------
                                                  //| a| x |   |   |
                                                  //|  |---|---|---|
                                                  //| b|   |   |   |
                                                  //|  |---|---|---|
                                                  //| c|   |   |   |
                                                  //|  |---|---|---|
                                                  //| 
  val t2 = t1.makeMove(1, 0, 'y')                 //> t2  : common.Board = 
                                                  //|    1   2   3  
                                                  //|  -------------
                                                  //| a| x |   |   |
                                                  //|  |---|---|---|
                                                  //| b| y |   |   |
                                                  //|  |---|---|---|
                                                  //| c|   |   |   |
                                                  //|  |---|---|---|
                                                  //| 
}