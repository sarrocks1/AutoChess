# AutoChess :
- An effort to automate chessplay using Selenium, TestNG and Stockfish API , on chess.com.


# To Use : 
1. By Executing Java File
- Clone or Download the Zip File and execute TC_PlayGame.java file (IDE is prefered).
- Make sure you have TestNG installed on your IDE.
2. By Executing Batch File

- Replace "path-to-workspace" with your workspace path in "run.bat" file.
- Run the "run.bat" file.


# Features : 
- Automatically Starts The game and plays according to the colour.
- Communicates with Stockfish to get the best move for a particular board position and plays the move.
- The Converters convert board position to FEN , which is passed to stockfish to get the best move.

# Future Scope :
- Detecting EN Passant 
- Detecting Promotions

# Opening an Issue :
- Describe The Steps to reproduce the issue.
- Screenshot of the game , showing the moves played when the issue is encountered will be helpful.


# Disclaimer :
- **This Program is only intended to be used for learning purposes thus , only supports unrated guest login in chess.com , Using this for rated games will result in a ban on your chess.com account**
