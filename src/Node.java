class Node<V,K > {
    V value;
    K key;
    Node<V,K> parent;
    Node<V,K> left;
    Node<V,K> right;
    int height=0;


    Node(V item,K akey) {
        value = item;
        key =akey;


    }

}
