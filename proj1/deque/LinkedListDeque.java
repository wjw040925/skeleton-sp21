package deque;

import java.util.Iterator;

public class LinkedListDeque <T> implements Iterable<T>{

    public class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(T item) {
            this.item = item;
            prev = null;
            next = null;
        }

    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node node = new Node(item);
        Node first = sentinel.next;
        sentinel.next = node;
        node.next = first;
        first.prev = node;
        node.prev = sentinel;
        size++;
    }

    public void addLast(T item) {
        Node node = new Node(item);
        Node last = sentinel.prev;
        sentinel.prev = node;
        node.prev = last;
        last.next = node;
        node.next = sentinel;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node now = sentinel.next;
        System.out.print(now.item);
        now = now.next;
        while (now.item != null) {
            System.out.print(" -> " + now.item);
            now = now.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next.item == null) {
            return null;
        }
        Node first = sentinel.next;
        sentinel.next = first.next;
        first.next = null;
        first.prev = null;
        sentinel.next.prev = sentinel;
        size--;
        return first.item;
    }

    public T removeLast() {
        if (sentinel.prev.item == null) {
            return null;
        }
        Node last = sentinel.prev;
        sentinel.prev = last.prev;
        last.next = null;
        last.prev = null;
        sentinel.prev.next = sentinel;
        size--;
        return last.item;
    }

    /** 迭代
     *
     * @param index 位置
     * @return 返回第index位置的元素
     */
    public T get(int index) {
        Node now = sentinel;
        for (int i = 0; i <= index; i++) {
            now = now.next;
        }
        return now.item;
    }

    /** 递归
     *
     * @param index 位置
     * @return 返回第index位置的元素
     */
    public T getRecursive(int index) {
        return getNode(sentinel.next, index);
    }

    public T getNode(Node now, int index) {
        if (index == 0) {
            return now.item;
        }
        return getNode(now.next, index - 1);
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int now;
        private Node nowNode;

        public LinkedListDequeIterator() {
            now = 0;
            nowNode = sentinel;
        }

        public boolean hasNext() {
            return now < size;
        }

        public T next() {
            nowNode = nowNode.next;
            now++;
            return nowNode.item;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        if (this.size != ((LinkedListDeque<T>) o).size) {
            return false;
        }
        Node node1 = this.sentinel.next;
        Node node2 = ((LinkedListDeque<T>) o).sentinel.next;
        for (int i = 0; i < this.size; i++) {
            if (node1.item != node2.item) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        LinkedListDeque<Integer> LLD = new LinkedListDeque<>();
        LLD.addFirst(1);
        LLD.addFirst(2);
        LLD.addFirst(3);
        LLD.addLast(4);
        LLD.addLast(5);
        for(int i : LLD){
            System.out.println(i);
        }
    }
}
