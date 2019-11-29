
/*
 * Hsing-Cheng Chou
 * CST8110 320
 * Professor Howard Rosenblum & Professor Wei Gong
 */

import java.util.Random;

public class CodeLetter {
	private char letterValue; // - a single letter in the code

	public CodeLetter(){// - Randomly select a code letter
		Random rnd = new Random();
		letterValue = (char) (rnd.nextInt(5)+'A');
	}

	public boolean isEquals(CodeLetter letter){// - BONUS - Do the code values match?
		return letter.isEquals(letterValue);
	}

	public boolean isEquals(char l){// - Is the given letter correct?
		return letterValue == l;

	}

	public void display(){// - Display the code letter
		System.out.print(letterValue);
	}

}
