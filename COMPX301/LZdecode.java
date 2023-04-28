import java.util.*;

public class LZdecode {

    /**
     * Decoder
     * Converts integer pairs back to the decoded text
     *
     * @param pairs
     * @return
     */
    public static String decode(List<int[]> pairs) {
        // Initialize dictionary
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put(i, String.valueOf((char) i));
        }

        // Initialize variables
        StringBuilder output = new StringBuilder();
        String phrase = "";
        int dictionarySize = 256;

        // Loop over integer pairs
        for (int[] pair : pairs) {
            int key = pair[0];
            int value = pair[1];

            // Check if the value is -1
            if (value == -1) {
                break;
            }

            String entry;
            if (dictionary.containsKey(key)) {
                entry = dictionary.get(key);
            } else {
                if (key == dictionarySize) {
                    entry = phrase + phrase.charAt(0);
                } else {
                    throw new IllegalArgumentException("Invalid key in input");
                }
            }

            if (value < 0) {
                value += 256;
            }

            String newPhrase = entry + (char) value;

            // Add new phrase to the dictionary
            dictionary.put(dictionarySize++, newPhrase);

            // Append the new phrase to the output string
            output.append(newPhrase);

            // Update the current phrase
            phrase = entry;
        }

        return output.toString();
    }

}