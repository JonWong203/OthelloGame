=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: 38386977
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays
  I used a 2D array of ints to represent the logic of the game board, where a black piece has value 1, a white piece has
  value 2, and an empty square has value 0. When a click is registered, my program checks whether placing a piece in
  that array row col is a valid move, whether it will cause other pieces in the array to flip colors. This is the
  appropriate use of the concept because pieces have the same properties and the orientation of pieces in the array
  matters.

  2. Collections
  I used a LinkedList Collection to store the history of the game which is the state of the board after every turn is played,
  including the starting original game board. By storing the history of the game, I created the undoMove() method in the
  OthelloLogic class which alters the current gamestate to the previous gamestate. I chose a LinkedList to store the
  history of the game because it allows me to easily access the last element of the list, to addAll elements to another
  LinkedList, and keeps the order in which elements are added to the LinkedList. I also implemented the Reset function
  that clears the LinkedList history and restores the game state to the original state of the game.

  3. File I/O
  I used File I/O to save the entire state of the game by writing state information into a CSV file by writing
  whoseTurn it currently is and the storing the board and the history of the board in the CSV file. I also implemented
  the functionality of reading from a CSV file so that a user can continue a saved game from a CSV file. The method
  reads a CSV file, extracts whoseTurn, current boardState, and the entire history of the game. This way, when a user
  continues a saved game, they can Undo turns such that they return to the original startState of the Game.
  (by representing 2D array of ints and the number of turns played into a CSV file).


  4. JUnit Testable Component
  I tested that my game works properly by testing that my OthelloLogic methods work as they are expected. I use tests to
  ensure that the board is updated correctly when a move is placed, including edge cases like invalid moves that are
  moves made on non-empty squares. I also ensure that the history of my game is correct. I also ensure that when I load
  a saved game my game state is correct.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  OthelloLogic
  is the model of my game. It is a class that contains all the methods that actually change the game state, that return
  information about the game state. This entails executing moves, checking if moves are valid, updating history,
  resetting the game state, checking for winner.

  OthelloBoard
  is the controller that the user interacts with, but also has view component implemented. The OthelloBoard has a
  mouseListener on its 800 pixel x 800 pixel board that registers the user clicks on the board, which is how the user
  makes moves in Othello. The OthelloBoard knows how to repaint itself and it knows its size. When an OthelloBoard
  object is constructed, it also initializes its OthelloLogic field to be a blank game board in the starting logic
  state.

  RunOthello
  is the view component of the game that implements the Runnable interface. In the RunOthello class, the method run()
  is implemented to construct the main JFrame of the game that contains the constructed, painted OthelloBoard, a
  JPanel of the toolbar (Instructions, Save Game, Load Game, Undo Move, Reset), and a label that displays the status of
  the game (whose turn it is and whether the game is over).

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  I changed the logic for the executeMove() method many times. When talking to TAs, they had different suggestions,
  some suggested using objects for pieces, some suggested using recursion to implement the executeMove() method, and
  another suggested using a while loop. After changing the implementation a couple of times, I ended up writing a helper
  function listOfCoordinatesToModify along with the executeMove method to alter the game state when a move is made.

  Another stumbling block was realizing that the x position of a registered click should map to the column of the array
  and the y position of a click should map to the row of the array.

  Another stumbling block was with the history of the game. I learned that you can't add the current game board directly
  to the history LinkedList, you must add a copy of the game board. Otherwise a change to the current game board would
  also alter the 2D array in the history LinkedList.



- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  I think there is good separation of functionality.
  All changes made to the logic of the game board are made through the OthelloLogic class.
  Methods that write and read are written in the OthelloBoard class because when the OthelloBoard is added to the main
  game JFrame in the RunOthello class, the OthelloBoard does not have direct access to the OthelloLogic 8x8 logic board.
  Instead, it needs ot alter the OthelloLogic board through methods in the OthelloBoard class. Thus, the write and read
  methods are written i nthe OthelloBoard class. Further, the controller component for reading and writing is not in the
  8x8 grid of the game, instead, it is part of the OthelloBoard. The write and read methods get and set data from and
  to the OthelloLogic object that it contains through public getter and setter methods in the OthelloLogic class.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
  Java Docs on JTextArea, JFrame, JPanel, JButtons.