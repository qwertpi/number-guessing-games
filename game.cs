using System;
using System.IO;
using System.Linq;

class Game {
    public static void Main() {
        int mode;
        int answer = 0;
        int num_guesses = 0;
        int guess;
        bool best_exists;
        int best_score = 0;
        Random randint = new Random();
        
        do{
            Console.WriteLine(@"Mode 1: 0-50
Mode 2: 0-100
Mode 3: 0-250
Mode 4: 0-500
Mode 5 0-2000");
            Console.Write("1-5?  ");
            mode = Convert.ToInt32(Console.ReadLine());
        } while (mode < 1 || mode > 5);
        
        best_exists = File.Exists($"{mode}.txt");
        if (best_exists) {
            best_score = Convert.ToInt32(File.ReadLines($"{mode}.txt").First());
            Console.WriteLine($"The score to beat is {best_score}");
        }
        else {
            Console.WriteLine("No one has played in this mode before...");
        }
        
        switch (mode){
            case 1:
                answer = randint.Next(51);
                break;
            case 2:
                answer = randint.Next(101);
                break;
            case 3:
                answer = randint.Next(251);
                break;
            case 4:
                answer = randint.Next(501);
                break;
            case 5:
                answer = randint.Next(2001);
                break;
        }
        
        do {
            num_guesses += 1;
            Console.Write("Enter a guess  ");
            guess = Convert.ToInt32(Console.ReadLine());
            if (answer > guess) {
                Console.WriteLine("Too low!");
            }
            else if (guess > answer) {
                Console.WriteLine("Too high!");
            }
        } while (guess != answer);
        
        Console.WriteLine("Well done, you correctly guessed the number!");
        Console.WriteLine($"It took you {num_guesses} guesses");
        
        if (best_exists) {
            if (num_guesses > best_score) {
                Console.WriteLine($"You did not beat the best score of {best_score}");
            }
            else if (num_guesses == best_score) {
                Console.WriteLine($"You equaled the best score of {best_score}");
            }
            else {
                Console.WriteLine($"You beat the best score of {best_score}");
                File.WriteAllText($"{mode}.txt", num_guesses.ToString());
            }
        }
        else {
            File.WriteAllText($"{mode}.txt", num_guesses.ToString());
        }
  }
}
