public class RedBlackTree<T extends Comparable<T>> {

    RBTreeNode<T> root;

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    public RedBlackTree() {
        root = null;
    }

    void flipColors(RBTreeNode<T> node) {
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
        // TODO: YOUR CODE HERE
    }

    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        swapclor(node,node.left);
        RBTreeNode tmp = node;
        node = node.left;
        tmp.left = node.right;
        node.right = tmp;
        // TODO: YOUR CODE HERE
        return node;
    }

    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        swapclor(node,node.right);
        RBTreeNode tmp = node;
        node = node.right;
        tmp.right = node.left;
        node.left = tmp;
        // TODO: YOUR CODE HERE
        return node;
    }

    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }
    public void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    private void swapclor(RBTreeNode p ,RBTreeNode c){
        Boolean tmp = p.isBlack;
        p.isBlack = c.isBlack;
        c.isBlack = tmp;
    }
    private RBTreeNode FixOne(RBTreeNode p){
        if( p.left == null) {//由于插入的逻辑结构，调用fix函数时的节点，至少有一个子树
            return rotateLeft(p);//不变量1
        }
        return p;
    }
    private RBTreeNode FixTwo(RBTreeNode p){
        if(p.left.left != null){//红一左红二左错，向右旋转
            if(!p.left.isBlack && !p.left.left.isBlack){
                return rotateRight(p);//不变量2 父节点和子节点不同时为红
            }
        }
        return p;
    }
    private RBTreeNode FixTF(RBTreeNode p){
        if(p.right != null){
            if(!p.left.isBlack && !p.right.isBlack)flipColors(p);//先判断是否为二子红，若不是则左旋 不变量3
            if(!p.right.isBlack) p =  rotateLeft(p);//不变量4
            return p;
        }
        return p;
    }
    private RBTreeNode fix(RBTreeNode p){
        p = FixOne(p);
        p = FixTwo(p);
        p = FixTF(p);
        return p;
    }
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        // TODO: Insert (return) new red leaf node.
        if(node == null) return new RBTreeNode<>(false,item);
        int cmp = node.item.compareTo(item);
        if(cmp > 0){
            if(node.left == null) node.left =  new RBTreeNode<>(false,item);
            else node.left = insert(node.left,item);
        }
        if(cmp < 0){
            if(node.right == null) node.right =  new RBTreeNode<>(false,item);
            else node.right = insert(node.right,item);
        }
        return fix(node);//重点每一次递归都要调增
    }

}
