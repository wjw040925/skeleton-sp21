package deque;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class ArrayDeque <T> {
    private T[] array;
    int size;
    int capital;
    int first;
    int last;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        capital = 8;
        size = 0;
        first = 1;
        last = 0;
    }

    public void addFirst(T item) {
        if ( full() ){
            resize(capital*2);
        }
        first = (first - 1 + capital) % capital;
        array[first] = item;
        size++;
    }

    public void addLast(T item) {
        if ( full() ){
            resize(capital*2);
        }
        last = (last + 1) % capital;
        array[last] = item;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean full() {
        return size == capital;
    }

    public void resize(int newCapital){
        T[] newarray = (T[]) new Object[newCapital];
        if (first < last) {
            System.arraycopy(array, first, newarray, 0, size);
        } else {
            System.arraycopy(array, first, newarray, 0, capital - first);
            System.arraycopy(array, 0, newarray, capital - first, last+1);
        }
        array = newarray;
        first = 0;
        last = size - 1;
        capital = newCapital;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        System.out.print(get(0));
        for (int i = 1; i < size; i++) {
            System.out.print(" -> " + get(i));
        }
    }

    public T removeFirst() {
        if (capital > 8 && size < capital / 4) {
            resize(capital/2);
        }
        T ans = array[first];
        array[first] = null;
        first = (first + 1) % capital;
        size--;
        return ans;
    }

    public T removeLast() {
        if (capital > 8 && size < capital / 4) {
            resize(capital/2);
        }
        T ans = array[last];
        array[last] = null;
        last = (last - 1 + capital) % capital;
        size--;
        return ans;
    }

    /** 迭代
     *
     * @param index 位置
     * @return 返回第index位置的元素
     */
    public T get(int index) {
        if (index >= size) {
            System.out.println("index非法");
        }
        return array[ (first+index) % capital ];
    }




//    public Iterator<T> iterator() {
//    }

//    public boolean equals(Object o) {
//
//    }

    public static void main(String[] args){
        ArrayDeque<Integer> arrDeque = new ArrayDeque<>();
        LinkedListDeque<Integer> listDeque = new LinkedListDeque<>();
        int N = 100;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // test addFirst
                int addVal = StdRandom.uniform(0,100);
                arrDeque.addFirst(addVal);
                listDeque.addFirst(addVal);
            } else if (operationNumber == 1) {
                // test addLast
                int addVal = StdRandom.uniform(0,100);
                arrDeque.addLast(addVal);
                listDeque.addLast(addVal);
            } else if (operationNumber == 2) {
                // test removeFirst
                Object num1 = arrDeque.removeFirst();
                Object num2 = listDeque.removeFirst();
                if (num1 != null && num2 != null)
                    assertEquals(num1,num2);
            } else if (operationNumber == 3) {
                // test removeLast
                Object num1 = arrDeque.removeLast();
                Object num2 = listDeque.removeLast();
                if (num1 != null && num2 != null)
                    assertEquals(num1, num2);
            } else if (operationNumber == 4) {
                int index = StdRandom.uniform(0, arrDeque.size());
                int num1 = arrDeque.get(index);
                int num2 = listDeque.get(index);
                assertEquals(num1,num2);
            }
        }
    }
}
