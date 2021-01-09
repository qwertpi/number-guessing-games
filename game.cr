mode = 0
num_guesses = 0
answer = 0
best_score = 0

loop do
    puts "Mode 1: 0-50
    Mode 2: 0-100
    Mode 3: 0-250
    Mode 4: 0-500
    Mode 5 0-2000"
    #print is puts without the newline
    print "1-5?  "
    mode = gets.to_s.to_i
    break if (mode > 0) && (mode < 6)
end

best_exists = File.exists?("#{mode}.txt")
if best_exists
    best_score = File.read("#{mode}.txt").to_i
    puts "The score to beat is #{best_score}"
else
    puts "No one has played in this mode before..."
end

case mode
    when 1
        answer = rand(50)
    when 2
        answer = rand(100)
    when 3
        answer = rand(250)
    when 4
        answer = rand(500)
    when 5
        answer = rand(2000)
    end

puts answer

loop do
    num_guesses += 1;
    print "Enter a guess  "
    guess = gets.to_s.to_i
    if answer > guess
        puts "Too low!"
    elsif guess > answer
        puts "Too high!"
    end
    break if guess == answer
end

puts "Well done, you correctly guessed the number!"
puts "It took you #{num_guesses} guesses"

if best_exists
    if num_guesses > best_score
        puts "You did not beat the best score of #{best_score}"

    elsif num_guesses == best_score
        puts "You equaled the best score of #{best_score}"
    else
        puts "You beat the best score of #{best_score}"
        File.write("#{mode}.txt", num_guesses)
    end
else
    File.write("#{mode}.txt", num_guesses)
end
