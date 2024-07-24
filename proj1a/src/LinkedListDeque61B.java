import java.util.List;
import java.util.ArrayList; // import the ArrayList class
public class LinkedListDeque61B<T> implements Deque61B<T>{

    private class Node {
        private T date;
        private Node next;
        private Node previous;

        private Node(T date,Node previous, Node next){
            this.date = date;
            this.next = next;
            this.previous = previous;
        }
        private Node(){
            this.date = null;
            this.next = null;
            this.previous=null;
        }
    }
    private final Node sentinelone;
    private final Node sentineltwo;
    private int size;
    private Node last;
    private Node first;

    public LinkedListDeque61B(){
        sentinelone = new Node();
        sentineltwo = new Node();
        first = sentineltwo;
        last = sentinelone;
        sentinelone.next = sentineltwo;
        sentinelone.previous = sentineltwo;
        sentineltwo.next = sentinelone;
        size = 0;
    }
    @Override
    public void addFirst(T x) {
        Node node = new Node(x,sentinelone,first);
        sentinelone.next = node;
        first.previous = node;
        first = node;
        if(last == sentinelone) last = first;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node node = new Node(x,last,sentineltwo);
        sentineltwo.previous = node;
        last.next =node;
        last = node;
        if(first == sentineltwo) first = last;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node tmp = first;
        while (tmp != sentineltwo){
            returnList.add(tmp.date);
            tmp=tmp.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        Node tmp = first;
        T date = tmp.date;
        sentinelone.next = first.next;
        first.next.previous = sentinelone;
        first = first.next;
        tmp.date = null;
        tmp.previous = null;
        tmp.next = null;
        size -= 1;
        return date;
    }

    @Override
    public T removeLast() {
        Node tmp = first;
        T date = tmp.date;
        last.previous.next = sentineltwo;
        sentineltwo.previous = last.previous;
        last = last.previous;
        tmp.date = null;
        tmp.previous = null;
        tmp.next = null;
        size -= 1;
        return date;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
