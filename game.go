package main

import (
    "fmt"
    "math/rand"
    "time"
    "strconv"
    "os"
    "io"
    "io/ioutil"
)

func main() {
    mode := 0
    answer := 0
    num_guesses := 0
    guess := -1
    best_exists := false
    best_score := 0
    filename := ""

    rand.Seed(time.Now().UTC().UnixNano())

    //for and while share a keyword in go
    for mode < 1 || mode > 5 {
        fmt.Println(`Mode 1: 0-50
Mode 2: 0-100
Mode 3: 0-250
Mode 4: 0-500
Mode 5 0-2000`)
        //will print with no newline beacuse we've used Print instead of Println
        fmt.Print("1-5?  ")
        var input string
        fmt.Scan(&input)
        //Atoi casts string to int
        mode, _ = strconv.Atoi(input)

        filename = strconv.Itoa(mode)+".txt"
    }

    t, err := ioutil.ReadFile(filename)
    //if we got an error when reading the file
    if err != nil {
        fmt.Println("No one has played in this mode before...")
        best_exists = false
    } else{
        best_score, _ = strconv.Atoi(string(t))
        //%v prints a value in it's deafult format
        fmt.Printf("The score to beat is %v\n", best_score)
        best_exists = true
    }

    switch (mode){
        case 1:
            answer = rand.Intn(51)
        case 2:
            answer = rand.Intn(101)
        case 3:
            answer = rand.Intn(251)
        case 4:
            answer = rand.Intn(501)
        case 5:
            answer = rand.Intn(2001)
    }

    for guess != answer {
        num_guesses ++
        fmt.Print("Enter a guess  ")
        var input string
        fmt.Scan(&input)
        guess, _ = strconv.Atoi(input)

        if answer > guess {
            fmt.Println("Too low!")
        } else if (guess > answer) {
            fmt.Println("Too high!")
        }
    }

    fmt.Println("Well done, you correctly guessed the number!")
    //newlines have to be manualy included in calls to Printf
    fmt.Printf("It took you %v guesses\n", num_guesses)
    
    if best_exists {
        if (num_guesses > best_score) {
            fmt.Printf("You did not beat the best score of %v\n", best_score)
        } else if (num_guesses == best_score) {
            fmt.Printf("You equaled the best score of %v\n", best_score)
        } else {
            fmt.Printf("You beat the best score of %v\n", best_score)
            f, _ := os.Create(filename)
            io.WriteString(f, strconv.Itoa(num_guesses))
        }
    } else {
        f, _ := os.Create(filename)
        io.WriteString(f, strconv.Itoa(num_guesses))
    }
}