import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class byteToHex {

    /**
     * Constructor
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // Read the contents of the file as a byte array
        File file = new File("example.txt");
        byte[] byteArray = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(byteArray);
        }

        // Convert the byte array to a hex string and print it
        String hexString = bytesToHex(byteArray);
        System.out.println("Hex string: " + hexString);

        // Decode the hex string to a byte array and convert it back to text
        byte[] decodedArray = hexToBytes(hexString.replaceAll("\\s", ""));
        String decodedText = new String(decodedArray, StandardCharsets.UTF_8);
        System.out.println("Decoded text: " + decodedText);

        // Encode the byte array using LZ78 and print out the integer pairs
        List<int[]> pairs = LZencode.LZEncode(decodedArray);
        System.out.println("Integer pairs:");
        for (int[] pair : pairs) {
            System.out.println(pair[0] + " " + pair[1]);
        }
    }

    /**
     * bytesToHex
     * Converts bytes to hexadecimal
     * 
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        // Intialize stringBuilder
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            // format the bytes to a hexadecimal and append it to the stringBuilder
            sb.append(String.format("%02x ", b));
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