import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    public static void main(String[] args) throws IOException {
        
        // Read words from file
        ArrayList<String> words = new ArrayList<>();
        FileReader fileReader = new FileReader("E:/java/words.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            words.add(line);
        }
        bufferedReader.close();
        
        // Select random word from list
        Random rand = new Random();
        String wordToGuess = words.get(rand.nextInt(words.size()));
        int wordLength = wordToGuess.length();
        char[] guessedLetters = new char[wordLength];
        for (int i = 0; i < wordLength; i++) {
            guessedLetters[i] = '?';
        }
        
        Scanner scanner = new Scanner(System.in);
        int guessesLeft = 6;
        boolean wordGuessed = false;
        
        // Game loop
        while (!wordGuessed && guessesLeft > 0) {
            System.out.print("Guess a letter: ");
            char guessedLetter = scanner.nextLine().charAt(0);
            boolean letterFound = false;
            for (int i = 0; i < wordLength; i++) {
                if (wordToGuess.charAt(i) == guessedLetter) {
                    guessedLetters[i] = guessedLetter;
                    letterFound = true;
                }
            }
            if (letterFound) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect.");
                guessesLeft--;
            }
            System.out.println("Guesses left: " + guessesLeft);
            System.out.println(guessedLetters);
            if (new String(guessedLetters).equals(wordToGuess)) {
                wordGuessed = true;
            }
        }
        
        if (wordGuessed) {
            System.out.println("Congratulations, you guessed the word!");
        } else {
            System.out.println("Sorry, you ran out of guesses. The word was " + wordToGuess + ".");
        }
        
        scanner.close();
    }

}