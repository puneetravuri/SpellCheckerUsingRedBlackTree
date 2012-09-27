package spellcheckerproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import redblacktreeproject.RedBlackTree;

/**
 * Implementation of a spell checker daemon that checks if the provided word is
 * correct and provides a closest word otherwise
 *
 * @author vravuri
 */
public class SpellChecker {

    private RedBlackTree wordList;

    /**
     * Constructor that loads the list of words into memory
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public SpellChecker() throws FileNotFoundException, IOException {
        BufferedReader in = new BufferedReader(new FileReader(SpellChecker.class.getResource("shortwords.txt").toString().substring(6)));
        String word;
        int wordCount = 0;
        wordList = new RedBlackTree();
        while ((word = in.readLine()) != null) {
            wordList.insert(word);
            wordCount++;
        }

        System.out.println("Red Black Tree loaded with " + wordCount + " words");
        System.out.println("The height of the tree is " + wordList.height());
        System.out.println("2 * log(n + 1)" + (2 * Math.log(wordCount + 1)));
        printUsage();
    }

    /**
     *
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        SpellChecker sCheck = new SpellChecker();

        Scanner in = new Scanner(System.in);
        String input;
        
        // Run the spell checker infinitely until user wishes to exit
        while (true) {
            System.out.print("> ");
            input = in.nextLine();

            // If user doesnt enter anything, ignore and continue
            if (input == null || input.length() == 0) {
                continue;
            }
            
            // For each option, its corresponding action is taken
            if (input.equals("p")) {
                System.out.println("Level Order Traversal:");
                sCheck.wordList.levelOrderTraversal();
                continue;
            } else if (input.equals("!")) {
                System.out.println("Bye !");
                return;
            } else if (input.startsWith("c ")) {
                if (sCheck.wordList.contains(input.substring(2))) {
                    System.out.println("Found " + sCheck.wordList.closeBy(input.substring(2)) + " after " + sCheck.wordList.getRecentCompares() + " comparisons");
                } else {
                    System.out.println("The word \"" + input.substring(2) + "\" is not present in dictionary. Perhaps you mean " + sCheck.wordList.closeBy(input.substring(2)));
                }
                continue;
            } else if (input.startsWith("a ")) {
                sCheck.wordList.insert(input.substring(2));
                System.out.println("The word \"" + input.substring(2) + "\" has been added to the dictionary");
                continue;
            } else {
                System.out.println("Invalid option");
                sCheck.printUsage();
                continue;
            }
        }

    }

    private void printUsage() {
        System.out.println("Legal commands are: ");
        System.out.println("<p> to print the entire word tree");
        System.out.println("<!> to quit");
        System.out.println("<c> <word> to spell check this word");
        System.out.println("<a> <word> add word to tree");
    }
}
