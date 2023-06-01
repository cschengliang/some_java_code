package datastruct.Double;

import org.junit.Test;

public class Node {
    Node prev;
    Object data;
    Node next;

    public Node(Object data) {
        this.data = data;
    }

    {
        prev = null;
        data = null;
        next = null;
    }

    public Node(Node prev, Object data, Node next) {
        this.prev = prev;
        this.data = data;
        this.next = next;
    }

//    @Test
//    public void test1() {
//        Node[] nodes = new Node[100];
//        for (int i = 0; i < nodes.length; i++) {
//            nodes[i] = new Node(new Object());
//            if (i == 0) {
//                nodes[i].prev = null;
//                nodes[i].next = null;
//                continue;
//            }
//            if (i == nodes.length - 1) {
//                nodes[0].next = nodes[1];
//                nodes[0].prev = nodes[i];
//                nodes[i].prev = nodes[i - 1];
//                nodes[i].next = nodes[0];
//                continue;
//            }
//            nodes[i - 1].next = nodes[i];
//            nodes[i].prev = nodes[i - 1];
//        }
//    }
//    @Test
//    public void test2(){
//        Node node = new Node(null,"d",null);
//    }
}


