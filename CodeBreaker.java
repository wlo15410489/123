
/*
 * Hsing-Cheng Chou
 * CST8110 320
 * Professor Howard Rosenblum & Professor Wei Gong
 */

import java.util.Scanner;

public class CodeBreaker {
	private CodeLetter letter1; // - First code letter
	private CodeLetter letter2; // - Second code letter
	private CodeLetter letter3; // - Third code letter
	private boolean done; // - Is the game done?
	private Scanner input; // - Common scanner
	private String guess; // - Current guess

	public CodeBreaker() {// - Display welcome message and create secret code

		System.out.println("Welcome to CodeBreaker");
		System.out.println("You have 6 tries to find the secret 3 letter code");
		System.out.println("The letters range from A to E");
		System.out.println("Good luck");
		System.out.println("The code has no repeat letters");

		letter1 = new CodeLetter();
		letter2 = new CodeLetter();
		letter3 = new CodeLetter();

		while (letter1.isEquals(letter2) || letter1.isEquals(letter3) || letter2.isEquals(letter3)) {
			letter1 = new CodeLetter();// If there's repeating characters in answer, ask for another answer.
			letter2 = new CodeLetter();
			letter3 = new CodeLetter();
		}

		//display();// showing answer => EZier for testing

		for (int i = 1; i <= 6; i++) {// start counting guesses, Max 6 guesses

			getGuess(i); // Answer validity is checked inside getGuess()

			if (done()) {
				System.out.println("You win");// If any guess matches the answer, break and print "You win"
				break;
			} else if (guess.equals("QUIT")) {// If any input equals "quit", break and print "You lose"
				System.out.println("You lose");		
				break;
			} else if (i==6 && !done()) {// IF guess #6 is still wrong, print hint, break and print "You lose"
				checkGuess();
				System.out.println("\nYou lose");		
				break;
			}else{
				checkGuess();// If none of the above happens => answer is valid but incorrect => check guess for hint messages..
			}
		}
	}

	public boolean done() {// - Is the game done?

		done = letter1.isEquals(guess.charAt(0)) && letter2.isEquals(guess.charAt(1))
				&& letter3.isEquals(guess.charAt(2));
		return done;
	}

	private boolean isValid(char guess) {// - Is the given letter valid?
		switch(guess){
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
			return true;
		default :
			return false;
		}
	}

	public void getGuess(int i) {// - Get guess #

		input = new Scanner(System.in);// only method where gets input from user

		System.out.print("\nEnter guess #" + i + ": ");// Get First input
		guess = input.nextLine().toUpperCase();

		while (!guess.equals("QUIT")){// when input = "QUIT", no need to check the input

			if (guess.length() < 3) {// too short
				System.out.println("Guess is too short");

			} else if (guess.length() > 3) {// too long
				System.out.println("Guess is too long");

			} else if (!isValid(guess.charAt(0)) || !isValid(guess.charAt(1)) || !isValid(guess.charAt(2))) {// guess other than abcde
			
				if (!isValid(guess.charAt(0))) {// guess letter 1 is other than abcde
				System.out.println(guess.charAt(0)+" is not a valid first letter");
				}
				
				if (!isValid(guess.charAt(1))) {// guess letter 2 is other than abcde
				System.out.println(guess.charAt(1)+" is not a valid second letter");
				}
				
				if (!isValid(guess.charAt(2))) {// guess letter 3 is other than abcde
				System.out.println(guess.charAt(2)+" is not a valid third letter");
				}

			} else if (guess.charAt(0) == guess.charAt(1) || guess.charAt(0) == guess.charAt(2)// guess with repeating characters(I'm so nice!)
					|| guess.charAt(1) == guess.charAt(2)) {
				System.out.println("There are no repeating characters");

			} else { // if the guess fits all the requirements, don't ask for
				break;// another input => break the while loop
			}

			System.out.print("Enter guess #" + i + ": ");// Get another input, same guess #
			guess = input.nextLine().toUpperCase();
		}
	}

	public void checkGuess() {// - Verify the guess

		int rp = 0;// count for right letter, right place
		int wp = 0;// count for wrong letter, wrong place

		for (int i = 0; i <= 2; i++) {
			if (i == 0 && letter1.isEquals(guess.charAt(i))) { // For first letter of answer == first letter of guess => 1 right place right character.
				rp++;
			} else if (i != 0 && letter1.isEquals(guess.charAt(i))) {// For first letter of answer == second/third letter of guess => 1 wrong place right character.
				wp++;
			}

			if (i == 1 && letter2.isEquals(guess.charAt(i))) {
				rp++;
			} else if (i != 1 && letter2.isEquals(guess.charAt(i))) {
				wp++;
			}

			if (i == 2 && letter3.isEquals(guess.charAt(i))) {
				rp++;
			} else if (i != 2 && letter3.isEquals(guess.charAt(i))) {
				wp++;
			}
		}

		if (!done()) { // if the answer is right, don't print anything
			System.out.println(rp + " right letter in the right place");
			System.out.println(wp + " right letter in the wrong place");
		}
	}

	public void display() {// - Display the secret code
		System.out.print("The code was ");
		letter1.display();
		letter2.display();
		letter3.display();
	}

}
