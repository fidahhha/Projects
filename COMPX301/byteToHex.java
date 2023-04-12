public class byteToHex {
    public static void main(String args[]) {
        byte[] bytes = { 0x12, 0x34, (byte) 0xAB }; // Output: 1234AB
        String hex = convertor(bytes);
        System.out.println(hex);
    }

    public static String convertor(byte[] bytes) {
        // create stringBuilder
        StringBuilder sb = new StringBuilder();
        // iterate over each byte in the array
        for (byte b : bytes) {
            // format the byte to a hexadecimal and appends to the string builder
            sb.append(String.format("%02X", b));
        }

        return sb.toString();

    }
}
