import random

def play_game():
    user_total = 0
    computer_total = 0
    print("Game is start...")
    while True:
        print("Enter the Run between '0' to '6'")
        user_run = int(input())

        if 0 <= user_run <= 6:
            print("Bowling done by computer is")
            computer_run = random.randint(0, 6)
            print(computer_run)
            user_total += user_run
        else:
            print("You entered an invalid key. Please enter a number between 0 and 6.")
            continue  # Skip the rest of the loop and ask for input again

        if user_run == computer_run:
            print("You OUT")
            print("NOW COMPUTER IS TO BAT AND YOU HAVE TO BOWL")
            print("The run scored by you is", user_total - user_run)
            print("computer target run is")
            print(user_total + 1 - user_run)

            user_total -= user_run

            while True:
                print("Enter the Run between '0' to '6' for bowling")
                user_run = int(input())
                print("Run made by computer is")
                computer_run = random.randint(0, 6)
                print(computer_run)
                computer_total += computer_run

                if user_run == computer_run :
                    print("Computer OUT")
                    computer_total -= computer_run

                    if user_total > computer_total:
                        print("YOU WON THE MATCH")
                        return  # Return from the function to start a new game
                    elif user_total == computer_total:
                        print("MATCH DRAW")
                        return
                if computer_total > user_total:
                        print("YOU LOSE")
                        return
                else:
                    print("computer total run is")
                    print(computer_total)
        else:
            print("Your total run is")
            print(user_total)

while True:
    print("Press any letter to continue or press 'Q' or 'q' to quit the GAME")
    c = input()
    if c.lower() == 'q':
        print("Quitting the Game.....")
        break
    play_game()
    print("\n\n\n")
