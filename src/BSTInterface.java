import java.io.IOException;
import java.io.PrintWriter;

public interface BSTInterface {
    Node find(int k );
    void insert(int k);
    void remove(int k);
    void traverse(PrintWriter out) throws IOException;
    String print(PrintWriter out);
    String mirror(PrintWriter out);
}
