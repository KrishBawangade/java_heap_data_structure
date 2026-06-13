package huffman_encoding;

import java.util.*;


public class HuffmanCoder{
    HashMap<Character, String> encoder;
    HashMap<String, Character> decoder;

    public HuffmanCoder(String feeder) throws Exception{
        HashMap<Character, Integer> fmap = new HashMap<>();

        for(int i =0; i<feeder.length(); i++){
            char ch = feeder.charAt(i);

            if(fmap.containsKey(ch)){
                fmap.put(ch, fmap.get(ch)+1);
            }else{
                fmap.put(ch, 1);
            }
        }

        Heap<Node> minHeap = new Heap<>();
        Set<Map.Entry<Character, Integer>> entrySet = fmap.entrySet();

        for(Map.Entry<Character, Integer> entry: entrySet){
            Node node = new Node(entry.getKey(), entry.getValue());
            minHeap.insert(node);
        }

        while(minHeap.size() != 1){
            Node first = minHeap.remove();
            Node second = minHeap.remove();

            Node newNode = new Node('\0', first.cost+second.cost);
            newNode.left = first;
            newNode.right = second;

            minHeap.insert(newNode);
        }

        Node root = minHeap.remove();
        this.encoder = new HashMap<>();
        this.decoder = new HashMap<>();
        
        createEncoderDecoder(root, "");
    }

    private void createEncoderDecoder(Node node, String code){
        if(node == null){
            return;
        }   

        if(node.left == null && node.right == null){
            this.encoder.put(node.data, code);
            this.decoder.put(code, node.data);
        }

        createEncoderDecoder(node.left, code+"0");
        createEncoderDecoder(node.right, code+"1");
    }

    public String encode(String source){
        String encoded = "";

        for(int i=0; i<source.length(); i++){
            char ch = source.charAt(i);

            encoded += this.encoder.get(ch);
        }

        return encoded;
    }

    public String decode(String encoded){
        String decoded = "";
        String code = "";

        for(int i=0; i<encoded.length(); i++){
            code+= encoded.charAt(i);
            if(this.decoder.containsKey(code)){
                char ch = this.decoder.get(code);
                decoded += ch;
                code = "";
            }

        }

        return decoded;
    }

    private class Node implements Comparable<Node>{
        Character data;
        int cost;
        Node left;
        Node right;

        public Node(Character data, int cost){
            this.data = data;
            this.cost = cost;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(Node other){
            return this.cost - other.cost;
        }
    }
}