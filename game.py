from random import randint 

mode = 0
while mode < 1 or mode > 5:
    mode = int(input('''Mode 1: 0-50
Mode 2: 0-100
Mode 3: 0-250
Mode 4: 0-500
Mode 5 0-2000
1-5?  '''))

try:
    with open(f"{mode}.txt") as f:
        best_score = int(f.readline())
        best_exists = True
except FileNotFoundError:
    print("No one has played in this mode before...")
    best_exists = False

answer = randint(0, {1: 50, 2: 100,  3: 250,  4: 500,  5: 2000}[mode])
num_guesses = 1

guess = int(input("Enter a guess  "))
while guess != answer:
    num_guesses += 1
    if (answer > guess):
        print("Too low!")
    else:
        print("Too high!")
    guess = int(input("Enter a guess  "))

print("Well done, you correctly guessed the number!")
print("It took you", num_guesses, "guesses")
if best_exists:
    if num_guesses > best_score:
        print("You did not beat the best score of", best_score)
    elif num_guesses == best_score:
        print("You equaled the best score of", best_score)
    else:
        print("You beat the best score of", best_score)
        with open(f"{mode}.txt", "w") as f:
            f.write(str(num_guesses)) 
else:
    with open(f"{mode}.txt", "w") as f:
        f.write(str(num_guesses)) 
