import io.StdIn.readLine
import util.Random
import scala.annotation.{switch, tailrec}
import io.Source.fromFile
import java.io.{BufferedOutputStream}
import java.nio.file.{Files, Paths}

object Game extends App {
	@tailrec
	def get_mode(): Int ={
		val mode: Int = readLine("""Mode 1: 0-50
Mode 2: 0-100
Mode 3: 0-250
Mode 4: 0-500
Mode 5 0-2000
1-5?  """).toInt
		if ((mode >= 1) && (mode <= 5)){
			return mode
		}
		else {
			get_mode()
		}
	}
	def get_best_score(filename: String): Int ={
		if (best_exists) {
			val best_score = fromFile(filename).getLines.next().toInt
			println(s"The score to beat is ${best_score}")
			return best_score
		}
		else {
			println("No one has played in this mode before...")
			return Int.MaxValue
		}
	}

	val mode: Int = get_mode()
	val filename: String = s"${mode}.txt"
	val best_exists: Boolean = Files.exists(Paths.get(filename))

	val answer: Int = Random.nextInt((mode: @switch) match {case 1 => 51 case 2 => 101 case 3 => 251 case 4 => 501 case 5 => 2001})
	val best_score: Int = get_best_score(filename)

	var num_guesses: Int = 0
	var guess: Int = -1
	
	while (guess != answer) {
		guess = readLine("Enter a guess  ").toInt
		num_guesses += 1
		if (answer > guess){
			println("Too low!")
		}
		else if (answer < guess) {
			println("Too high!")
		}
	}

	println(s"It took you $num_guesses guesses to guess the correct answer")
	if (best_exists){
		if (num_guesses > best_score) {
			println(s"You did not beat the best score of $best_score")
		}
		else if (num_guesses == best_score) {
			println(s"You equaled the best score of $best_score")
		}
		else {
			println(s"You beat the best score of $best_score")
			val out1 = new BufferedOutputStream(Files.newOutputStream(Paths.get(s"$mode.txt")))
			out1.write(num_guesses.toString.getBytes(), 0, num_guesses.toString.length) 
			out1.close()
		}
	}
	else{
		val out1 = new BufferedOutputStream(Files.newOutputStream(Paths.get(s"$mode.txt")))
		out1.write(num_guesses.toString.getBytes(), 0, num_guesses.toString.length) 
		out1.close()
	}
}