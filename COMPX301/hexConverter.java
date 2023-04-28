import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class hexConverter {

    /**
     * Constructor
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        File inputFile = new File("example.txt");
        StringBuilder input = new StringBuilder();

        // Read input from text file
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.append(line);
                input.append(System.lineSeparator()); // Add new line character
            }
        }

        System.out.println("Original text: " + "\n" + input.toString());
        byte[] bytes = input.toString().getBytes();

        // Compress input using LZ78 algorithm
        List<int[]> compressed = LZencode.Encoder(bytes);
        System.out.println("Compressed Output: " + "\n" + Arrays.deepToString(compressed.toArray()));

        // Write compressed output to file
        try (PrintWriter pw = new PrintWriter(new File("output.txt"))) {
            pw.println(Arrays.deepToString(compressed.toArray()));
        }

        String decoded = LZdecode.decode(compressed);
        System.out.println("Decoded text: " + "\n" + decoded);
    }

    /**
     * bytesToHex
     * Converts bytes to hexadecimal
     * 
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b)); // Remove the space character after %02x
        }
        return sb.toString();
    }

    /**
     * hexToBytes
     * Converting hexadecimal back to byte array
     * 
     * @param hexString
     * @return
     */
    public static byte[] hexToBytes(String hexString) {
        // Check the length of the input
        int len = hexString.length();
        // Store converted bytes into array
        byte[] data = new byte[len / 2];
        // iterate over the characters, and convert it to the byte array
        for (int i = 0; i < len; i += 2) {
            // convert pair of hexadecimal characters
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

}
