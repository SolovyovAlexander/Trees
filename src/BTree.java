
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;



public class BTree implements BInteface {

    int order;

   BNode root;
    public static void main(String[] args) throws IOException {

        Locale.setDefault(Locale.US);

        File input = new File("input.txt");
        Scanner in = new Scanner(input);

        File outputFile = new File("output.txt");
        FileWriter fileWriter = new FileWriter(outputFile);
        PrintWriter out = new PrintWriter(fileWriter);

        String forArray = in.nextLine();
        String[] arr;
        arr = forArray.split(" ");

        BTree tree = new BTree(3);



        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
            tree.insert(tree, code(arr[i]), arr[i]);
        }

        tree.traverse(tree.root,out);
        out.close();





    }

    public BTree(int order) {//set order of tree

        this.order = order;
        root = new BNode(order, null);

    }
    @Override
    public BNode find(BNode root, int key) {
        int i = 0;

        while (i < root.count && key > root.keys[i])

        {
            i++;
        }

        if (i <= root.count && key == root.keys[i])

        {


            return root;
        }

        if (root.leaf)

        {

            return null;

        } else
        {

            return find(root.getChild(i), key);

        }
    }

    public void split(BNode x, int i, BNode y) {
        BNode z = new BNode(order, null);

        z.leaf = y.leaf;

        z.count = order - 1;//this is updated size

        for (int j = 0; j < order - 1; j++) {
            z.keys[j] = y.keys[j + order];
            z.values[j] = y.values[j + order];
        }
        if (!y.leaf)
        {
            for (int k = 0; k < order; k++) {
                z.children[k] = y.children[k + order];
            }
        }

        y.count = order - 1;

        for (int j = x.count; j > i; j--)
        {

            x.children[j + 1] = x.children[j];

        }
        x.children[i + 1] = z;

        for (int j = x.count; j > i; j--) {
            x.keys[j + 1] = x.keys[j];
            x.values[j + 1] = x.values[j];
        }
        x.keys[i] = y.keys[order - 1];
        x.values[i] = y.values[order - 1];

        y.keys[order - 1] = 0;
        y.values[order - 1] = "";

        for (int j = 0; j < order - 1; j++) {
            y.keys[j + order] = 0;
            y.values[j + order] = "";
        }


        x.count++;
    }


    @Override
    public void insert(BTree t, int key, String value) {
        BNode r = t.root;
        if (r.count == 2 * order - 1)//if is full
        {
            BNode s = new BNode(order, null);//new node

            t.root = s;
            s.leaf = false;
            s.count = 0;
            s.children[0] = r;
            split(s, 0, r);

            subInsert(s, key, value);
        } else
            subInsert(r, key, value);
    }
    public void subInsert(BNode x, int key, String value) {
        int i = x.count;

        if (x.leaf) {
            while (i >= 1 && key < x.keys[i - 1])
            {
                x.keys[i] = x.keys[i - 1];
                x.values[i] = x.values[i - 1];
                i--;
            }

            x.keys[i] = key;
            x.values[i] = value;
            x.count++;

        } else {
            int j = 0;
            while (j < x.count && key > x.keys[j]) {
                j++;
            }



            if (x.children[j].count == order * 2 - 1) {
                split(x, j, x.children[j]);

                if (key > x.keys[j]) {
                    j++;
                }
            }

            subInsert(x.children[j], key, value);
        }
    }



    public void traverse(BNode node,PrintWriter out) {
        if (node != null) {
            for (int i = 0; i < node.count + 1; i++) {
                traverse(node.children[i],out);
                if (i != node.count) out.print(node.values[i] + " ");
            }
        }
    }







    static int code(String value) {
        if (value.length() == 1) {
            if (Integer.valueOf(value.charAt(0)) >= 65 & Integer.valueOf(value.charAt(0)) <= 97) {
                return Integer.valueOf(value.charAt(0));
            } else return Integer.valueOf(value);
        } else return Integer.valueOf(value);
    }






}
class BNode {

    static int t;

    int count;

    int keys[];

    String values[];


    BNode children[];

    boolean leaf;

    BNode parent;



    public BNode getChild(int index) {
        return children[index];
    }
    public BNode(int t, BNode parent) {

        this.t = t;
        this.parent = parent;
        keys = new int[2 * t - 1];
        values = new String[2 * t - 1];
        children = new BNode[2*t];
        leaf = true;
        count = 0;
    }


}