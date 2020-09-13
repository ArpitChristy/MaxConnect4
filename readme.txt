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


++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Algorithm Details
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
A minimax algorithm is a recursive algorithm for choosing the next move in an n-player game, usually a two-player, back and forth game. A value is associated with each position or state of the game. This value is computed by means of a position evaluation function and it indicates how good it would be for a player to reach that position. The player then makes the move that maximizes the minimum value of the position resulting from the opponent's possible following moves. If it is A's turn to move, A gives a value to each of his legal moves.

A simple version of the minimax algorithm, stated below, deals with games such as tic-tac-toe, where each player can win, lose, or draw. If player A can win in one move, his best move is that winning move. If player B knows that one move will lead to the situation where player A can win in one move, while another move will lead to the situation where player A can, at best, draw, then player B's best move is the one leading to a draw. Late in the game, it's easy to see what the "best" move is. The Minimax algorithm helps find the best move, by working backwards from the end of the game. At each step it assumes that player A is trying to maximize the chances of A winning, while on the next turn player B is trying to minimize the chances of A winning (i.e., to maximize B's own chances of winning).

Alpha–beta pruning is a search algorithm that seeks to decrease the number of nodes that are evaluated by the minimax algorithm in its search tree. This allows us to search much faster and even go into deeper levels in the game tree. It cuts off branches in the game tree which need not be searched because there already exists a better move available. The algorithm maintains two values, alpha and beta, which represent the maximum score that the maximizing player is assured of and the minimum score that the minimizing player is assured of respectively. Initially alpha is negative infinity and beta is positive infinity, i.e. both players start with their lowest possible score. It can happen that when choosing a certain branch of a certain node the minimum score that the minimizing player is assured of becomes less than the maximum score that the maximizing player is assured of (beta <= alpha). If this is the case, the parent node should not choose this node, because it will make the score for the parent node worse. Therefore, the other branches of the node do not have to be explored.

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
PSUEDOCODE 
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

function minimax(node, depth, isMaximizingPlayer, alpha, beta):

    if node is a leaf node :
        return value of the node
    
    if isMaximizingPlayer :
        bestVal = -INFINITY 
        for each child node :
            value = minimax(node, depth+1, false, alpha, beta)
            bestVal = max( bestVal, value) 
            alpha = max( alpha, bestVal)
            if beta <= alpha:
                break
        return bestVal

    else :
        bestVal = +INFINITY 
        for each child node :
            value = minimax(node, depth+1, true, alpha, beta)
            bestVal = min( bestVal, value) 
            beta = min( beta, bestVal)
            if beta <= alpha:
                break
        return bestVal
        
// Calling the function for the first time.
minimax(0, 0, true, -INFINITY, +INFINITY)

###############################################################################################################################
Reference :- Min Max algorithm(alpha beta algorithm) from :-
https://omega.uta.edu/~gopikrishnav/classes/common/4308_5360/slides/Game_Search.pdf
https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-4-alpha-beta-pruning/
