ASSIGNMENT 3
NAME: Arpit Christy
UTA ID: 1001672198

Programming Language Used :- Java
Code runs successfully on Omega
Code Structure :-
Depending on argument provided by user the game mode will be set to interactive
mode or one-move mode (logic in AiPlayer.java)

maxconnect4.java is the entry point of the program it’ll take the input and will start
running the program according to the game mode provided.

GameBoard.java is the util class for the matrix it handles various matrix operations for
the connect4 game such as printing the matrix, checking if the move is valid etc.

AiPlayer.java is the class which implements the minimax algorithm along with the alpha
beta pruning.

getMin() function will calculate beta value.

getMax() function will calculate alpha value.

#########################################################
Compilation and Execution instruction :-
##########################################################

Depending on the argument provided by user the game mode will be set to either
interactive mode or one move mode.

First Compile the java code using the following command:
javac maxconnect4.java

For Implementation of
Single Move Mode :-
use command: java maxconnect4 one-move [input_file] [output_file] [depth]
example : java maxconnect4 one-move input1.txt output1.txt 7

For Interactive Mode :-
use command
java maxconnect4 interactive [input_file] [computer-next/human-next] [depth]
example : java maxconnect4.java interactive input1.txt computer-next 7

Measuring Execution Time :-
Use command :-
time java maxconnect4 one-move input1.txt output1.txt


Reference :- Min Max algorithm(alpha beta algorithm) from :-
https://omega.uta.edu/~gopikrishnav/classes/common/4308_5360/slides/Game_Search.pdf
https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-4-alpha-beta-pruning/
