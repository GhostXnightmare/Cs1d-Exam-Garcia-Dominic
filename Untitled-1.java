import java.util.Scanner;
import java.util.Random;
import java.io.File;

public class GuessingGame {
    public static void main(String[] args) {
        // read in the words from the file
        String[] words = readWordsFromFile("words.txt");

        // randomly select a word from the list
        Random random = new Random();
        String selectedWord = words[random.nextInt(words.length)];

        // initialize the display word to all question marks
        char[] displayWord = new char[selectedWord.length()];
        for (int i = 0; i < displayWord.length; i++) {
            displayWord[i] = '?';
        }

        // keep track of the guessed letters and the number of incorrect guesses
        String guessedLetters = "";
        int incorrectGuesses = 0;

        // loop until the word has been guessed or too many incorrect guesses have been made
        while (incorrectGuesses < 6 && !String.valueOf(displayWord).equals(selectedWord)) {
            // print the current display word and guessed letters
            System.out.println(String.valueOf(displayWord));
            System.out.println("Guessed letters: " + guessedLetters);

            // prompt the user to guess a letter
            System.out.print("Guess a letter: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            char guessedLetter = input.charAt(0);

            // check if the guessed letter is in the selected word
            if (selectedWord.indexOf(guessedLetter) == -1) {
                System.out.println("Incorrect!");
                incorrectGuesses++;
            } else {
                System.out.println("Correct!");

                // replace the question marks with the guessed letter in the display word
                for (int i = 0; i < selectedWord.length(); i++) {
                    if (selectedWord.charAt(i) == guessedLetter) {
                        displayWord[i] = guessedLetter;
                    }
                }
            }

            // add the guessed letter to the list of guessed letters
            guessedLetters += guessedLetter;
        }

        // print the final result
        if (incorrectGuesses >= 6) {
            System.out.println("Sorry, you lost. The word was " + selectedWord);
        } else {
            System.out.println("Congratulations, you won!");
        }
    }

    private static String[] readWordsFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            return scanner.useDelimiter("\\Z").next().split("\\r?\\n");
        } catch (FileNotFoundException e) {
            System.err.println("Error: file not found: " + filename);
            System.exit(1);
            return null;
        }
    }
}