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
    public static List<int[]> Encoder(byte[] bytes) {
        // Initialize Dictionary
        Map<String, Integer> dictionary = new HashMap<>();

        // Initialize variables
        List<int[]> result = new ArrayList<>();
        byte[] currentHex = new byte[2];
        int counter = 1;

        // Loop over bytes in the array
        for (byte b : bytes) {
            // Check if currentHex exists in the dictionary
            String currentString = bytesToHex(currentHex) + bytesToHex(new byte[] { b });
            if (dictionary.containsKey(currentString)) {
                // CurrentHex exists, so update it
                System.arraycopy(currentString.getBytes(), 0, currentHex, 0, 2);
            } else {
                // CurrentHex doesn't exist, so add it to the dictionary
                int[] pair = { dictionary.getOrDefault(bytesToHex(currentHex), 0), b & 0xFF };
                result.add(pair);
                dictionary.put(currentString, counter++);
                // Update currentHex to the currentChar
                System.arraycopy(new byte[] { b }, 0, currentHex, 0, 1);
            }
        }

        // Add remaining bytes to result
        int[] pair = { dictionary.getOrDefault(bytesToHex(currentHex), 0), -1 };
        result.add(pair);

        return result;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}