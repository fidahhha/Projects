import java.util.*;

public class LZencode {

    /**
     * lz78Compress
     * Compresses a byte array using the LZ78 algorithm and returns a list of
     * integer pairs representing the compressed data.
     *
     * @param hexInput
     * @return
     */
    public static List<int[]> compressLZ78(String hexInput) {
        // Convert the hexadecimal string input to a byte array
        byte[] bytes = hexConverter.hexToByte(hexInput);

        // Compress the byte array using the LZ78 algorithm
        Map<String, Integer> dictionary = new HashMap<>();
        String phrase = "";
        List<int[]> integerPairs = new ArrayList<>();

        for (byte b : bytes) {
            String s = Character.toString((char) b);
            if (dictionary.containsKey(phrase + s)) {
                phrase += s;
            } else {
                // Print byte-to-hex conversion of the current byte being compressed
                String hexString = hexConverter.byteToHex(new byte[] { b });
                System.out.println("Compressing byte " + b + " (" + hexString + ")");
                Integer index = dictionary.get(phrase);
                if (index == null) {
                    index = 0; // or some other default value
                }
                integerPairs.add(new int[] { index, b & 0xFF });
                dictionary.put(phrase + s, dictionary.size() + 1);
                phrase = s;
            }
        }

        if (!phrase.equals("")) {
            Integer index = dictionary.get(phrase);
            if (index == null) {
                index = 0; // or some other default value
            }
            integerPairs.add(new int[] { index, -1 });
        }

        return integerPairs;
    }

}