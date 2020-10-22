package com.enzo.selfproj;

public class LRUCache {


    private int capacity ;

    private int size;

    private Node head;

    private Node tail;


    private class Node{

        private int key;
        private int value;

        Node next;
        Node prev;


        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }

    }



    public LRUCache(int capacity) {
        if (capacity<=0){
            throw new IllegalArgumentException("");
        }
        this.capacity = capacity;
        this.size = 0;
    }

    public int get(int key) {
        if (this.size == 0){
            return -1;
        }

        Node temp = head;
        while(temp != null){

            if (temp.key == key){
                break;
            }
            temp = temp.next;
        }

        if (temp == null){
            return -1;
        }

        //仅当temp不是头指针的时候，需要移动指针
        if (size > 1 && temp != head){
            Node pre = temp.prev;
            Node next = temp.next;
            if (temp == tail){

                temp.next = head;
                head = temp;
                pre.next = null;
                temp.prev = null;
                tail = pre;

            }else{

                temp.next = head;
                head.prev = temp;
                temp = head;

                pre.next = next;
                next.prev = pre;

            }
        }



        return temp.value;
    }



    public void put(int key, int value) {
        if (size == 0){
            Node node = new Node(key, value);
            this.head = node;
            this.tail = node;
            size++;
            return;
        }

        Node node = new Node(key, value);
        node.next = head;
        head.prev = node;
        head = node;
        if (size < capacity){
            size++;
        }else {
           //释放最后一个节点
            Node t = tail;
            tail = tail.prev;
            t.prev = null;//help gc;
        }


    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3,3);
        System.out.println(cache.get(2));
    }
}
