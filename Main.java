public class Main{
    public static void main(String[] args) throws Exception{
        Heap<Integer> heap = new Heap<>();

        heap.insert(56);
        heap.insert(23);
        heap.insert(12);
        heap.insert(9);
        heap.insert(5);
        heap.insert(46);

        System.out.println(heap.remove());

        System.out.println(heap.heapSort());
    }
}