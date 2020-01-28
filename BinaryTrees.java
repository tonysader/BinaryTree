package binarytrees;
class Node{
    int id;
    Node left;
    Node right;
}
class Tree{
    Node root;
    public Node find(int key){
        Node cur = root;
        while(cur != null){
            if(cur.id == key)
                return cur;
            else if(key < cur.id)
                cur = cur.left;
            else if(key > cur.id)
                cur = cur.right;
        }
        return null;
    }
    public void insert(int v){
        Node newN = new Node();
        newN.id = v;
        Node cur,father;
        if(root == null)
        {
            root  = newN;
            return;
        }
        cur = root;
        while(true)
        {
            father = cur;
            if(v > cur.id)
            {
                cur = cur.right;
                if(cur == null){
                    father.right = newN;
                    return;
                }
            }else{
                cur = cur.left;
                if(cur == null){
                    father.left = newN;
                    return;
                }
            }
        }
    }
    public void inOrder(){
        inOrder(root);
        System.out.println();
    }
    private void inOrder(Node r){
        if(r==null)
            return ;
        else{
            inOrder(r.left);
            System.out.print(r.id + " , ");
            inOrder(r.right);
            
        }
        
    }
    private void postOrder(Node r){
        while(r != null){
            postOrder(r.left);
            postOrder(r.right);
            System.out.println(r.id + " , ");
        }
        return;
    }
    private void preOrder(Node r){
        if(r==null)
            return ;
        else{
            System.out.println(r.id + " , ");
            preOrder(r.left);
            preOrder(r.right);        
        }
    }
    public Node min(){
        Node last = root;
        Node cur = root;
        while(cur!= null){
            last = cur;
            cur = cur.left;
        }
        return last;
    }
    public Node max(){
        Node last = root;
        Node cur = root;
        while(cur!= null){
            last = cur;
            cur = cur.right;
        }
        return last;
    }
    public Node getSuccessor(Node delNode){
        Node sucFather = delNode;
        Node suc = delNode;
        Node cur = delNode.right;
        while(cur!= null){
            sucFather = suc;
            suc = cur;
            cur = cur.left;
        }
        if(suc != delNode.right){
            sucFather.left = suc.right;
            suc.right = delNode.right;
        }
        return suc;
    }
    public boolean deleteNode(int key){
        boolean isLeft = false;
        Node cur = root;
        Node father = cur;
        while(cur.id != key){
            father = cur;
            if(key < cur.id){
                isLeft = true;
                cur = cur.left;
            }else{
                isLeft = false;
                cur = cur.right;
            }
            if(cur == null)
                return false;
        }
        if(cur.right == null && cur.left == null){
            if(cur == root)
                root = null; 
            else if(isLeft)
            {
                father.left = null; 
            }else{
                father.right = null;
            }
        }
        else if(cur.right == null){
            if(cur == root){
                root = cur.left;
            }
            else if(isLeft){
                father.left = cur.left;
            }else
                father.right = cur.left;
        }
        else if(cur.left == null){
            if(cur == root)
                root = cur.right;
            else if(isLeft)
                father.left = cur.right;
            else
                father.right = cur.right;
        }
        else{
            Node suc = getSuccessor(cur);
            if(cur == root) root = suc;
            else if(isLeft) father.left = suc;
            else father.right = suc;
            suc.left = cur.left;
        }
        return true;
    }
    public Node mirror(){
        return mirrorTreeGetRoot(root);
    }
    private Node mirrorTreeGetRoot(Node r){
        if(r == null) return null;
        Node temp = r.right;
        r.right = r.left;
        r.left = temp;
        mirrorTreeGetRoot(r.right);
        mirrorTreeGetRoot(r.left);
        return r;
    }
}
public class BinaryTrees {
    public static void main(String[] args) {
        Tree t = new Tree();
        t.insert(15);
        t.insert(10);
        t.insert(20);
        t.insert(3);
        t.insert(12);
        t.insert(16);
        t.insert(25);
        t.insert(21);
        
        
        t.inOrder();
        System.out.println("Min is: " + t.min().id);
        System.out.println("Max is: " + t.max().id);
        
        t.deleteNode(20);
        t.inOrder();
        
        //t.deleteNode(3);
        //t.inOrder();
        
        //t.deleteNode(10);
        //t.inOrder();
        
        Tree t2 = new Tree();
        t2.insert(5);
        t2.insert(2);
        t2.insert(7);
        t2.insert(1);
        t2.insert(3);
        t2.insert(6);
        t2.insert(8);
        t2.insert(9);

        System.out.println("Tree before mirror");
        t2.inOrder();
        Node newR = t2.mirror();
        System.out.println("Tree after mirror");
        t2.inOrder();
    }
    
}
