import java.util.*;

public class KMPtable {
    public static void main(String[] args) {
        // Checking if string input has been provided
        if (args.length != 1) {
            System.err.println("Usage: java KMPtable <searchString>");
            System.exit(1);
        }

        // Declare Variables
        String pattern = args[0];
        System.out.println("Pattern: " + pattern);
        // Storing the skip values for each character
        int[] skip = new int[pattern.length()];
        skip[0] = 0;

        // Iterate through each character
        for (int i = 1; i < pattern.length(); i++) {
            int j = skip[i - 1];
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = skip[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            skip[i] = j;

        }

        // Output the skip array table header
        System.out.print("    ");
        for (int index = 0; index < pattern.length(); index++) {
            System.out.print(pattern.charAt(index) + "  ");
        }
        System.out.println();

        // Show/print skip values for each letter
        for (int i = 0; i < pattern.length(); i++) {
            char currentLetter = pattern.charAt(i);
            // Print skip values for unique characters only
            if (i == pattern.lastIndexOf(currentLetter)) {
                System.out.print(currentLetter + "   ");
                for (int j = 0; j < pattern.length(); j++) {
                    // Find the last occurrence of the character in the prefix
                    int lastCharIndex = pattern.lastIndexOf(pattern.charAt(j), i);
                    // If the character does not occur in the prefix, skip value is the length of
                    // the prefix
                    if (lastCharIndex == -1) {
                        System.out.print((i + 1) + " ");
                    } else {
                        System.out.print(i - lastCharIndex + " ");
                    }
                    System.out.print(" ");

                }
                System.out.println();

            }

        }

        // Add skip values to indicate no such character is found
        System.out.print("*   ");
        for (int index = 0; index < pattern.length(); index++) {
            System.out.print((index + 1) + "  ");
        }
        System.out.println();
    }
}
