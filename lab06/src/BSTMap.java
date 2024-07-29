import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {

    BSTNode root;
    int size;
    private class BSTNode {
        K key;
        V value;
        BSTNode lchild;
        BSTNode rchild;

        private BSTNode() {
            key = null;
            value = null;
            lchild = null;
            rchild = null;
        }

        private BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            lchild = null;
            rchild = null;
        }
        public int compareKeys(K other){
            return this.key.compareTo(other);
        }
    }

    public BSTMap() {
        root = new BSTNode();
        size = 0;
    }

    private void BSTAdd(BSTNode node, K key ,V value) {
        int cmp = node.compareKeys(key);
        if ( cmp > 0) {
            if (node.lchild == null) {
                node.lchild = new BSTNode(key,value);
                size++;
                return;
            }
            BSTAdd(node.lchild,key,value);
        }
        else if(cmp < 0){
            if (node.rchild == null) {
                node.rchild = new BSTNode(key,value);
                size++;
                return;
            }
            BSTAdd(node.rchild,key,value);
        }
        else node.value = value;
    }
    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     */
    private BSTNode SearchBST(BSTNode node,K key){
        if(node == null)return null;
        if(key.compareTo(node.key) == 0)return node;
        else if (key.compareTo(node.key) > 0) return SearchBST(node.rchild,key);
        else return SearchBST(node.lchild,key);
    }
    @Override
    public void put(K key, V value) {
        if(root.key == null) {
            root.key = key;
            root.value = value;
            size++;
            return;
        }
        BSTAdd(root,key,value);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     */
    @Override
    public V get(K key) {
        if (size == 0) return null;
        BSTNode node = SearchBST(root,key);
        if(node != null) return node.value;
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        if (size == 0) return false;
        if(SearchBST(root,key) == null) return false;
        return true;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        root.key = null;
        root.value = null;
        root.lchild = null;
        root.rchild = null;
        size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new  UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        if(size == 0) return null;
        BSTNode node = SearchBST(root,key);
        if(node == null)return null;
        BSTNode tmp;
        if(node.lchild == null && node.rchild == null) {}
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return null;
    }

}