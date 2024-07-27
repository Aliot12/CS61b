import java.lang.reflect.Array;
import java.util.Arrays;

public class UnionFind {
    // TODO: Instance variables
    int[] date;
    int length;
    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        date= new int[N];
        length = N;
        Arrays.fill(date,-1);
        // TODO: YOUR CODE HERE
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        if(date[v] < 0)return -date[v];
        // TODO: YOUR CODE HERE
        return sizeOf(date[v]);
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return date[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        while (parent(v1) > 0){
            if(parent(v1) == v2)return true;
            v1 = parent(v1);
        }
        // TODO: YOUR CODE HERE
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
//    private int FindHelper(int v){
//        if(parent(v) < 0)return v;
//        date[v] = FindHelper(v);
//        return FindHelper(parent(v));
//    }
    public int find(int v) {
        if(v < 0|| v > length)throw new IllegalArgumentException();
        if(parent(v) < 0)return v;
        date[v] = find(parent(v));
        return find(parent(v));
        // TODO: YOUR CODE HERE
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        if(v1 == v2) return;
        if(connected(v1,v2))return;
        int root1 = find(v1);
        int root2 = find(v2);
        int r1prent = parent(root1);
        int r2prent = parent(root2);
        if( r1prent >= r2prent){
            date[root2] = r1prent + r2prent;
            date[root1] = root2;
        }
        else {
            date[root1] = r1prent + r2prent;
            date[root2] = root1;
        }
        // TODO: YOUR CODE HERE
    }

}
