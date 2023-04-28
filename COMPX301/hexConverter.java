import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class hexConverter {

    /**
     * Main method to read input from a text file and convert to hexadecimal
     * representation.
     *
     * @param args command line arguments
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
        List<int[]> compressed = LZencode.compressLZ78(byteToHex(bytes));
        System.out.println("Compressed Output: " + "\n" + Arrays.deepToString(compressed.toArray()));

        // Write compressed output to file
        try (PrintWriter pw = new PrintWriter(new File("output.txt"))) {
            pw.println(Arrays.deepToString(compressed.toArray()));
        }

        String decoded = LZdecode.decode(compressed);
        System.out.println("Decoded text: " + "\n" + decoded);
    }

    /**
     * byteToHex(byte[] bytes)
     *
     * @param bytes the byte array to convert
     * @return the hexadecimal string
     */
    public static String byteToHex(byte[] bytes) {
        StringBuilder hexOutput = new StringBuilder();
        for (byte b : bytes) {
            hexOutput.append(String.format("%02X", b));
        }

        return hexOutput.toString();
    }

    /**
     * hexToByte(String hex)
     *
     * @param hex the hexadecimal string to convert
     * @return the byte array
     */
    public static byte[] hexToByte(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}