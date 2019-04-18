import java.io.PrintWriter;

public interface BInteface {
    public void insert(BTree t, int key, String value);
    public BNode find(BNode root, int key);
    public void traverse(BNode node,PrintWriter out);
}
