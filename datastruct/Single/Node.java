package datastruct.Single;

import org.junit.Test;

public class Node {
    Object data;
    Node next;

    public Node(Object data) {
        this.data = data;
    }

    @Test
    public void test1() {
        Node node1 = new Node("AA");
        Node node2 = new Node("BB");
        node1.next = node2;
    }
}


