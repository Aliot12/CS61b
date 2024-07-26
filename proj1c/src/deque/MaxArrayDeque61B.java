package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{
    Comparator comparator;
    public MaxArrayDeque61B(Comparator<T> c){
        super();
        comparator = c;
    }
    public T max(){
        if(this.size() == 0) return null;
        int pos = 0;
        while (get(pos++) == null);
        T max = get(pos);
        for (Object i: this){
            if(comparator.compare(i,max) > 0){
                max = (T) i ;
            }
        }
        return max;
    }
    public T max(Comparator<T> c){
        int pos = 0;
        while (get(pos++) == null);
        T max = get(pos);
        for (Object i: this){
            if(c.compare(max,(T) i) > 0){
                max = (T) i ;
            }
        }
        return max;
    }
}