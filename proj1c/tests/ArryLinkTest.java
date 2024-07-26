import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import deque.MaxArrayDeque61B;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class ArryLinkTest {
    @Test
    public void TestIterstor(){
        MaxArrayDeque61B<Integer> lld1 = new MaxArrayDeque61B<Integer>(Comparator.naturalOrder());
        lld1.addFirst(9);
        lld1.addFirst(1);
        lld1.addFirst(29);
        lld1.addLast(99);
//        String a = lld1.toString();
        int x = lld1.max();
        System.out.print(x);
        for (Object i : lld1){
            System.out.print(i);
        }
//        Deque61B lld2 = new LinkedListDeque61B();
//        lld2.addFirst(" 你好");
//        lld2.addFirst(" 我叫");
//        lld2.addFirst(" 韩梅梅");
//        String b = lld2.toString();
//        System.out.print(b);
//        ArrayDeque61B lld3 = new ArrayDeque61B();
//        lld3.addFirst(" LiHua");
//        System.out.print(lld3.equals(lld1));
//        lld3.ArryCopy(lld1);
//        for (Object i : lld2){
//            System.out.print(i);
//        }
//        System.out.print(lld3.equals(lld1));
//        LinkedListDeque61B lld4 = new LinkedListDeque61B();
//        lld4.addFirst(" 你好");
//        System.out.print(lld2.equals(lld4));
    }
}
