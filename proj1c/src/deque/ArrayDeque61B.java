package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class ArrayDeque61B<T> implements Deque61B<T> {
    private  T[] array;
    private int nextfirst;
    private int nextlast;

    private int size;
    private int length;
    public ArrayDeque61B(){
        array =  (T[])new Object[8];
        nextfirst = 4;
        nextlast = 5;
        size = 0;
        length = 8;

    }
    public ArrayDeque61B(int len){
        array =  (T[])new Object[len];
        nextfirst = 0;
        nextlast = 1;
        size = 0;
        length = len;

    }
    private void AddHelper(int len){
        T[] alb = (T[])new Object[len];
        for(int i = 0; i < size; i++){
            alb[i] = get(i);
        }
        this.array = alb;
        nextlast = size;
        length = len;
        nextfirst = length - 1;
    }
    @Override
    public void addFirst(T x) {
        if(size == length) AddHelper(length*2);
        array[nextfirst] = x;
        nextfirst = Math.floorMod(nextfirst-1,length);
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if(size == length) AddHelper(length*2);
        array[nextlast] = x;
        nextlast = Math.floorMod(nextlast+1,length);
        size += 1;
    }

    @Override
    public List<T> toList() {
        if (size == 0) return null;
        List<T> list=  new ArrayList<>();
        for(int i = 0; i < size; i++){
            list.add(get(i));
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public T removeFirst() {
        if(size < length/4) AddHelper(length/4);
        nextfirst = Math.floorMod(nextfirst+1,length);
        size--;
        return array[nextfirst];
    }

    @Override
    public T removeLast() {
        if(size < length/4) AddHelper(length/4);
        nextlast = Math.floorMod(nextlast-1,length);
        size--;
        return array[nextlast];
    }

    @Override
    public T get(int index) {
        if(size==0)return null;
        return array[Math.floorMod(nextfirst+ 1 +index,length)];
    }

    @Override
    public T getRecursive(int index) {
        if(size==0)return null;
        return array[Math.floorMod(nextfirst+ 1 +index,length)];
    }

    private class Arrayiterator<T> implements Iterator<T>{

        int pot;
        int num;
        private Arrayiterator(){
            num = 0;
            pot = nextfirst + 1;
        }
        @Override
        public boolean hasNext() {
            return num++ < size;
        }

        @Override
        public T next() {
            return (T) array[Math.floorMod(pot++,length)];
        }
    }
    @Override
    public  Iterator<T> iterator() {
        return new  Arrayiterator();
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof LinkedListDeque61B<?>A)
            if(size != A.size())return false;
        int pot = 0;
        if(o instanceof ArrayDeque61B<?> A)
            for (Object i : A){
                if(!i.equals(get(pot++)))return false;
            }
        return true;
    }
    @Override
    public String toString(){
        return toList().toString();
    }

    public void ArryCopy(Deque61B<T> OntherArry){
        if(OntherArry instanceof ArrayDeque61B<T>OtArry){
            T[] alb = (T[])new Object[OtArry.size()];
            for(int i = 0; i < size; i++){
                alb[i] = get(i);
            }
            this.array = alb;
            nextlast = size;
            length =  OntherArry.size();
            nextfirst = length - 1;
            }
        }
}
