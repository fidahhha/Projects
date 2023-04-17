import java.util.*;

public class LZencode {

    /**
     * lz78Compress
     * Compresses a byte array using the LZ78 algorithm and returns a list of
     * integer pairs representing the compressed data.
     * 
     * @param bytes
     * @return
     */
    public static List<int[]> LZEncode(byte[] bytes) {
        // Initialize Dictionary
        Map<String, Integer> dictionary = new HashMap<>();

        // Initialize variables
        List<int[]> result = new ArrayList<>();
        StringBuilder currentHex = new StringBuilder();
        int counter = 1;

        // Loop over bytes in the array
        for (byte b : bytes) {
            // Format value
            String currentChar = String.format("%02x", b);
            String currentString = currentHex.toString() + currentChar;

            // Check if currentHex exists in the array
            if (dictionary.containsKey(currentString)) {
                currentHex = new StringBuilder(currentString);
            } else {
                // IF it doesnt exist then add it to the dictionary
                int[] pair = { dictionary.getOrDefault(currentHex.toString(), 0), b & 0xFF };
                result.add(pair);
                dictionary.put(currentString, counter);
                currentHex = new StringBuilder(currentChar);
                // Increment the counter
                counter++;
            }
        }

        // Check if there are any valuse left in the currentHex
        if (currentHex.length() > 0) {
            // IF value does exist then add it to the output/result
            // -1 indicates there are no values left to be encoded
            int[] pair = { dictionary.getOrDefault(currentHex.toString(), 0), -1 };
            result.add(pair);
        }

        return result;
    }
}