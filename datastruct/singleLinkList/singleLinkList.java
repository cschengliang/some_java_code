package datastruct.singleLinkList;

import org.junit.Test;

/**
 * 对单链表实现增删改查
 */

public class singleLinkList {
    public static Node init(){
        return new Node(0,null);
    }
    public static void insert1(Node head,int data){
        //头节点不使用
        Node newNode = new Node(data,null);
        Node p = head;
        while (p.next != null){
            p = p.next;
        }
        p.next = newNode;
    }
    public static void show(Node head){
        Node p = head;
        p = p.next;
        while (p != null){
            System.out.println(p.data);
            p = p.next;
        }
    }
    public static int getData(Node head,int index){
        Node p = head;
        int i = 0;
        //&&表示前面的条件不满足，后面的条件就不执行了
        //||表示前面的条件满足，后面的条件就不执行了
        while (p != null && i < index){
            p = p.next;
            i++;
        }
        if (p == null){
            return -1;
        }
        return p.data;

    }
    public static void SetData(Node head,int index,int data){
        Node p = head;
        int i =0;
        while (p != null && i < index){
            p = p.next;
            i++;
        }
        if (p == null){
            System.out.println("index is error");
        }
        p.data = data;
    }
    //在index位置后面插入data
    public static void insert2(Node head,int index,int data){
        Node p = head;
        int i = 0;
        while (p != null && i < index){
            p = p.next;
            i++;
        }
        if (p == null){
            System.out.println("index is error");
        }
        Node newNode = new Node(data,null);
        newNode.next = p.next;
        p.next = newNode;

    }
    @Test
    public void test1(){
        Node head = init();
        //插入1到10
        for (int i = 1; i < 11; i++) {
            insert1(head,i);
        }
        show(head);
    }
    @Test
    public void test2(){
        Node head = init();
        for (int i = 1; i < 11; i++) {
            insert1(head,i*2);
        }
        for (int i = 1; i < 11; i++) {
            System.out.println(getData(head,i));
        }
    }
    @Test
    public void test3(){
        Node head = init();
        for (int i = 1; i < 11; i++) {
            insert1(head,i*2);
        }
        for (int i = 1; i < 11; i++) {
            SetData(head,i,i*3);
        }
        for (int i = 1; i < 4; i++) {
            insert2(head,i*2,i*100);
        }
        show(head);
    }
}
