import io.StdIn.readLine
import util.Random
import scala.annotation.switch
import io.Source.fromFile
import java.io.FileNotFoundException
import java.io.{BufferedOutputStream}
import java.nio.file.{Files, Paths}

object Game extends App {
	val mode: Int = readLine("""Mode 1: 0-50
Mode 2: 0-100
Mode 3: 0-250
Mode 4: 0-500
Mode 5 0-2000
1-5?""").toInt
	var best_score = Int.MaxValue
	var best_exists: Boolean = false
	if ((mode >= 1) && (mode <= 5)){
		val answer: Int = Random.nextInt((mode: @switch) match {case 1 => 51 case 2 => 101 case 3 => 251 case 4 => 501 case 5 => 2001})
		try{
			best_score = fromFile(s"$mode.txt").getLines.next().toInt
			best_exists = true
		}
		catch{
			case _: FileNotFoundException => println("No one has played in this mode before...")
		}

		var num_guesses: Int = 1
		var guess: Int = 0
		guess = readLine("Enter a guess  ").toInt
		while (guess != answer){
		  num_guesses += 1
		  if (answer > guess){
			  println("Too low!")
		  }
		  else{
			  println("Too high!")
		  }
		  guess = readLine("Enter a guess  ").toInt
		}
		
		println(s"It took you $num_guesses guesses to guess the correct answer")
		if (best_exists){
			if (num_guesses > best_score){println(s"You did not beat the best score of $best_score")}
			else if (num_guesses == best_score){println(s"You equaled the best score of $best_score")}
			else{
				print(s"You beat the best score of $best_score")
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
	else{
		println("That mode does not exist")
	}
}