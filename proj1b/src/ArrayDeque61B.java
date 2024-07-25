import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
public class ArrayDeque61B <T>implements Deque61B<T>{
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
    private void AddHelper(){
        if(size != length) return;
        nextfirst = nextfirst + 1;
        T[] alb = (T[])new Object[length * 2];
        for(int i = 0; i < length; i++){
            alb[i] = get(Math.floorMod(nextfirst++,length));
        }
        this.array = alb;
        nextlast = length;
        length = length*2;
        nextfirst = length - 1;
    }
    @Override
    public void addFirst(T x) {
        AddHelper();
        array[nextfirst] = x;
        nextfirst = Math.floorMod(nextfirst-1,length);
        size += 1;
    }

    @Override
    public void addLast(T x) {
        AddHelper();
        array[nextlast] = x;
        nextlast = Math.floorMod(nextlast+1,length);
        size += 1;
    }

    @Override
    public List<T> toList() {
        if (size == 0) return null;
        List<T> list=  new ArrayList<>();
        int tmp = nextfirst + 1;
        for(int i = 0; i < size; i++){
            list.add(get(Math.floorMod(tmp++,length)));
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
        nextfirst = Math.floorMod(nextfirst+1,length);
        size--;
        return array[nextfirst];
    }

    @Override
    public T removeLast() {
        nextlast = Math.floorMod(nextlast-1,length);
        size--;
        return array[nextlast];
    }

    @Override
    public T get(int index) {
        if(size==0)return null;
        return array[Math.floorMod(index,length)];
    }

    @Override
    public T getRecursive(int index) {
        if(size==0)return null;
        return array[Math.floorMod(index,length)];
    }
    public void ArryCopy(Deque61B<T> OntherArry){
        if(OntherArry instanceof ArrayDeque61B<T>OtArry){
        int tmp = OtArry.nextfirst + 1;
        for(int i = 0; i < OntherArry.size(); i++) {
            array[i] = OntherArry.get(Math.floorMod(tmp++, OtArry.length));
            size++;
        }
        nextfirst = length - 1;
        nextlast = OtArry.size;
        }
    }
}
