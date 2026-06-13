package huffman_encoding;

public class Main {
    public static void main(String[] args) throws Exception{

        String original = "abbccda";

        HuffmanCoder coder = new HuffmanCoder(original);

        String encoded = coder.encode(original);
        System.out.printf("Encoded: %s\n", encoded);

        String decoded = coder.decode(encoded);
        System.out.printf("Decoded: %s", decoded);
    }
}
