# -- PROJECT MINESWEEPER --
## How to play ?
First of all you have to enter ROW and COL value for game board.

You can enter a minimum of 2x2 game board, or the system will ask you to enter a value again.

Lets suppose you enter a 5x3 value.This will be your game board, 

The first point of our minefield is always 0.0, you should not forget this when making a choice.

### * - - - - 
### - - - - -
### - - - - - 

For example, the "*" sign at the above point is a mine, and this mine is at the 0-0 point.

If there is no mine in the selection you make, the game continues, and if there is a mine around it, the number of mines around it is indicated at that point.

### - - - - -
### - - * - -
### - * 3 * - 

For example, if we choose the point 2-2, our output will be 3, since there are 3 mines at the intersection of the point.The game is won when you find all the clean points.If you choose a point with a mine, you will lose the game.

Don't forget. The choices you make should be positive numbers and you should not enter a value that will go out of the board, and you can't choose the mine-free point you have chosen again, and if you don't follow these instructions, the game will ask you to enter a value again.

### GOOD LUCK !.