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
        int index = 0;
        String current = "";

        // Loop over integer pairs
        for (int[] pair : pairs) {
            int key = pair[0];
            int value = pair[1];
            String next;

            // Check if the key exists in the dictionary
            if (dictionary.containsKey(key)) {
                current = dictionary.get(key);
            } else if (key == dictionary.size()) {
                current = current + current.charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid key in input");
            }

            // Check if the value is -1
            if (value == -1) {
                break;
            }

            // Check if the value exists in the dictionary
            if (dictionary.containsKey(value)) {
                next = dictionary.get(value);
            } else if (value == dictionary.size()) {
                next = current + current.charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid value in input");
            }

            // Append the next string to the output and add it to the dictionary
            output.append(next);
            dictionary.put(dictionary.size(), current + next.charAt(0));
            current = next;
        }

        return output.toString();
    }
}