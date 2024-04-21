# Conner Worrell

# Importing necessary modules
import random

# Function to print the game board
def print_board(board):
    for row in board:
        print(" | ".join(row))
        print("-" * 9)

# Function to check if a player has won
def check_winner(board, player):
    # Check rows
    for row in board:
        if all(cell == player for cell in row):
            return True
    # Check columns
    for col in range(3):
        if all(board[row][col] == player for row in range(3)):
            return True
    # Check diagonals
    if all(board[i][i] == player for i in range(3)) or all(board[i][2-i] == player for i in range(3)):
        return True
    return False

# Function to check if the board is full
def check_tie(board):
    for row in board:
        for cell in row:
            if cell == " ":
                return False  # If any cell is empty, the game is not a tie
    return True  # All cells are filled, the game is a tie

# Function to update and display the score
def display_score(score):
    print("Score:")
    print("Player 1:", score['X'], "wins")
    print("Player 2:", score['O'], "wins")

# Main function to play the game
def play_game():
    # Initialize the game board
    board = [[" " for _ in range(3)] for _ in range(3)]
    # Initialize player scores
    score = {'X': 0, 'O': 0}
    # Variable to keep track of current player
    current_player = 'X'
    
    # Main game loop
    while True:
        print_board(board)
        print("Player", current_player, "turn:")
        row = int(input("Enter row (1-3): ")) - 1
        col = int(input("Enter column (1-3): ")) - 1
        
        # Check if the selected position is valid and empty
        if 0 <= row < 3 and 0 <= col < 3 and board[row][col] == " ":
            board[row][col] = current_player
            # Check if the current player has won
            if check_winner(board, current_player):
                print_board(board)
                print("Player", current_player, "wins!")
                score[current_player] += 1
                display_score(score)
                break  # End the game
            # Check if the game is a tie
            elif check_tie(board):
                print_board(board)
                print("It's a tie!")
                display_score(score)
                break  # End the game
            # Switch to the other player
            current_player = 'O' if current_player == 'X' else 'X'
        else:
            print("Invalid move! Try again.")

# Start the game
play_game()
