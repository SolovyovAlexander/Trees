import java.io.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class BST <V,K extends Comparable<K>>  {
    Node<V,K> root = null;//root of tree
    Queue<Node> queue=new ConcurrentLinkedDeque<>();// queue for method print
    String []BSMT=new String[10000];//array that i use for output BSMT without empty line in the end, because with empty line test will write that there is 1 extra line
    int i=0;//index in BSMT array
   static String T;//String for method TraverseInString

    public static void main(String[] args) throws IOException {
        BST t = new BST();


    }
    //find method with argument value of node, that we need find

    public Node<V,K> find(K k) {
        return find(root,k);
    }
    //find method with arguments: value of node that we need find and node
    Node find(Node<V,K> node, K k){
        while( node != null ) {
            if( k.compareTo(node.key)<0 )
                node = node.left;
            else if( k.compareTo(node.key)>0 )
                node = node.right;
            else
                return node;
        }

        return null;
    }

    //insert method with argument - value of node that we need find
    public void insert(K k,V value) {
        root = insert(k,value, root);


    }
    //recursive insert method with arguments:value of node that we need find and node
    private Node insert(K k,V value, Node<V,K> node) {
        if (node == null) {

            node = new Node(value,k);


            return node;
        }


        if (k.compareTo(node.key)<0) {
            node.left = insert(k,value, node.left);
            node.left.parent = node;


        } else if (k.compareTo(node.key)>0) {
            node.right = insert(k,value, node.right);
            node.right.parent = node;

        }
        else if (k.compareTo(node.key)==0){
            System.out.println("Exception");

        }
        int a;
        int b;

        if(node.left==null)a=0;
        else{
            a=node.left.height;
        }
        if(node.right==null)b=0;

        else b=node.right.height;
        node.height =max( a, b) + 1;

        return node;


    }


    public void force(K k,V value) {
        root = force(k,value, root);


    }



    public boolean has_key(K k){
       Node n = find(k);
       if (n!=null) return true;
       else return false;

    }
    //recursive insert method with arguments:value of node that we need find and node
    private Node force(K k,V value, Node<V,K> node) {
        if (node == null) {

            node = new Node(value,k);


            return node;
        }


        if (k.compareTo(node.key)<0) {
            node.left = insert(k,value, node.left);
            node.left.parent = node;


        } else if (k.compareTo(node.key)>0) {
            node.right = insert(k,value, node.right);
            node.right.parent = node;

        }
        else if (k.compareTo(node.key)==0){
            node.value=value;

        }
        int a;
        int b;

        if(node.left==null)a=0;
        else{
            a=node.left.height;
        }
        if(node.right==null)b=0;

        else b=node.right.height;
        node.height =max( a, b) + 1;

        return node;


    }




   //method remove were i use method find, to find what node i need delete
    public void remove(K k) {
        heightchanger(k);
        Node<V,K> d = find(k);
        if (d.left != null) {
            Node<V,K> node = d.left;
            while (node.right != null) {
                node = node.right;
            }
            find(k).value = node.value;
            if (node.left != null) {
                node.value = node.left.value;
                node.left = null;

            } else if (node.parent.key.compareTo(node.key)<0) {
                node.parent.right = null;
            } else {
                node.parent.left = null;
            }
        } else if (d.right != null) {
            Node<V,K> node = d.right;
            while (node.left != null) {
                node = node.left;
            }
            find(k).value = node.value;
            if (node.right != null) {
                node.value = node.right.value;
                node.right = null;
            } else if (node.parent.key.compareTo(node.key)<0) {
                node.parent.right = null;
            } else {
                node.parent.left = null;
            }
        }
        else if(d.parent!=null&&d.left==null&&d.right==null){
            if(d.parent.key.compareTo(d.key)<0){
                d.parent.left=null;
            }
            else{d.parent.right=null;}
        }

    }

   //method traverse with argument "PrinterWriter out",because this method input in file
    public void traverse(PrintWriter out) throws IOException {
        traverse(root, out);
    }
    //recursive method traverse
    private void traverse(Node node, PrintWriter out) {

        if (node == null) {
            return;
        }
        traverse(node.left, out);
        out.print(node.value + " ");
        traverse(node.right, out);
    }


   //method print with argument "PrinterWriter out",because this method input in file
    public String print(PrintWriter out) {
        print(root,out);

        return null;
    }
    //recursive method print. In it i use queue to storage nodes
    public void  print(Node node,PrintWriter out){

        if(node.right!=null&&node.left!=null){
            out.print(node.value+" ");
            out.print(node.left.value+" ");
            out.println(node.right.value);
            queue.add(node.left);
            queue.add(node.right);
            while (!queue.isEmpty()){print(queue.poll(),out);}
        }
        if(node.left!=null&&node.right==null){
            out.print(node.value+" ");
            out.println(node.left.value);

            queue.add(node.left);
            while (!queue.isEmpty()){print(queue.poll(),out);}

        }
        if(node.right!=null&&node.left==null){
            out.print(node.value+" ");
            out.println(node.right.value);

            queue.add(node.right);
            while (!queue.isEmpty()){print(queue.poll(),out);}
        }


    }

    //method mirror almost like mirror, but here i use array for output
    //because when I out put last element it prints with using "println" and because of this test write:"String counts don't match: expected x, got x+1"
    //but array help me output last element with using "print" instead "println"
    public String mirror(PrintWriter out){
        if(!queue.isEmpty())queue.clear();
        mirror(root,out);
        String v="";

        for (int j = 0; j <BSMT.length-4 ; j++) {
            if(BSMT[j+3]==null&&BSMT[j+4]==null&&BSMT[j+1]==null&&BSMT[j-1]!=null&&BSMT[j]!=null)
                v= BSMT[j];
        }
        System.out.println(v);
        for (int j = 0; j <BSMT.length ; j++) {
            if(BSMT[j]!=null&&BSMT[j+1]==null&&!BSMT[j].equals(v))
                out.println(BSMT[j]+" ");
            else if(BSMT[j]!=null) out.print(BSMT[j]+" ");


        }

        return null;
    }//recursive method mirror
    public void  mirror(Node node,PrintWriter out){




        if(node.right!=null&&node.left!=null){
            BSMT[i]=node.value+"";
            i++;
            BSMT[i]=node.right.value+"";
            i++;
            BSMT[i]=node.left.value+"";
            i++;
            queue.add(node.right);
            queue.add(node.left);
            i++;
            while (!queue.isEmpty()){mirror(queue.poll(),out);}
        }
        if(node.left!=null&&node.right==null){
            BSMT[i]=node.value+"";
            i++;
            BSMT[i]=node.left.value+"";
            i++;

            queue.add(node.left);
            i++;
            while (!queue.isEmpty()){mirror(queue.poll(),out);}

        }
        if(node.right!=null&&node.left==null){
            BSMT[i]=node.value+"";
            i++;
            BSMT[i]=node.right.value+"";
            i++;

            queue.add(node.right);
            i++;
            while (!queue.isEmpty()){mirror(queue.poll(),out);}
        }


    }
    public int max(int a,int b){// find maximum between 2 values
        if(a>b) return a;
        else return b;

    }
    public void heightchanger(K k){
        if(find(k).parent!=null){
        find(k).parent.height--;

        heightchanger(find(k).parent.key);}

    }

    public void TraverseInString()  {
        traverse(root);
    }
    //recursive method traverse
    private void traverse(Node node) {

        if (node == null) {
            return;
        }
        traverse(node.left);
        T=T+(node.value + " ");
        traverse(node.right);
    }




}