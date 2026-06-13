package huffman_encoding;

import java.util.*;

public class Heap<T extends Comparable<T>>{
    private List<T> list;

    public Heap(){
        list = new ArrayList<>();
    }

    public int size(){
        return list.size();
    }

    private void swap(int i, int j){
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private int parent(int index){
        return (index-1)/2;
    }

    private int left(int index){
        return 2*index+1;
    }

    private int right(int index){
        return 2*index+2;
    }

    public void insert(T value){
        list.add(value);

        upheap(list.size()-1);
    }

    private void upheap(int i){
        if(i == 0){
            return;
        }

        int p = parent(i);
        // for finding minimum value
        if(list.get(i).compareTo(list.get(p)) < 0){
            swap(i, p);
            upheap(p);
        }
    }

    public T remove() throws Exception{
        if(list.isEmpty()){
            throw new Exception("Removing from an empty Heap!");
        }

        T temp = list.get(0);

        T last = list.remove(list.size()-1);

        if(!list.isEmpty()){
            list.set(0, last);
            downheap(0);
        }

        return temp;
    }

    private void downheap(int i){
        int smallest = i;

        int left = left(i);
        int right = right(i);

        if (left < list.size() &&
            list.get(left).compareTo(list.get(smallest)) < 0) {
            smallest = left;
        }

        if (right < list.size() &&
            list.get(right).compareTo(list.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            downheap(smallest);
        }
    }

    public List<T> heapSort() throws Exception{
        List<T> data = new ArrayList<>();

        while(!list.isEmpty()){
            data.add(this.remove());
        }

        return data;
    }
}