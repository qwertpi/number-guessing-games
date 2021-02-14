import 'dart:io';
import 'dart:math';

void main() {
    int mode;
    int answer;
    int num_guesses = 0;
    int guess;
    int best_score;
    var rng = new Random();

    do{
        print("""Mode 1: 0-50
Mode 2: 0-100
Mode 3: 0-250
Mode 4: 0-500
Mode 5 0-2000""");
        //print with no newline
        stdout.write("1-5?  ");
        mode = int.parse(stdin.readLineSync());
    } while (mode < 1 || mode > 5);

    //final marks a constant whose value cannot be determined at compile-time
    final filename = "${mode}.txt";
    final f = new File(filename);
    final best_exists = f.existsSync();
    if (best_exists) {
        best_score = int.parse(f.readAsStringSync());
        print("The score to beat is ${best_score}");
    }
    else {
        print("No one has played in this mode before...");
    }

    switch (mode) {
        case 1:
            answer = rng.nextInt(51);
            break;
        case 2:
            answer = rng.nextInt(101);
            break;
        case 3:
            answer = rng.nextInt(251);
            break;
        case 4:
            answer = rng.nextInt(501);
            break;
        case 5:
            answer = rng.nextInt(2001);
            break;
    }
    do {
        num_guesses ++;
        stdout.write("Enter a guess  ");
        guess = int.parse(stdin.readLineSync());
        if (answer > guess) {
            print("Too low!");
        }
        else if (guess > answer) {
            print("Too high!");
        }
    } while (guess != answer);

    print("Well done, you correctly guessed the number!");
    print("It took you ${num_guesses} guesses");
    if (best_exists) {
        if (num_guesses > best_score) {
            print("You did not beat the best score of ${best_score}");
        }
        else if (num_guesses == best_score) {
            print("You equaled the best score of ${best_score}");
        }
        else {
            print("You beat the best score of ${best_score}");
            f.writeAsStringSync(num_guesses.toString());
        }
    }
    else {
        f.writeAsStringSync(num_guesses.toString());
    }
}