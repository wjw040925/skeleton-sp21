package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;
import timingtest.SLList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> list1 = new AListNoResizing<>();
        BuggyAList<Integer> list2 = new BuggyAList<>();
        list1.addLast(4);
        list1.addLast(5);
        list1.addLast(6);
        list2.addLast(4);
        list2.addLast(5);
        list2.addLast(6);
        assertEquals(list1.removeLast(),list2.removeLast());
        assertEquals(list1.removeLast(),list2.removeLast());
        assertEquals(list1.removeLast(),list2.removeLast());
     }

     @Test
     public void randomizedTest(){
         AListNoResizing<Integer> L1 = new AListNoResizing<>();
         BuggyAList<Integer> L2 = new BuggyAList<>();

         int N = 5000;
         for (int i = 0; i < N; i += 1) {
             int operationNumber = StdRandom.uniform(0, 4);
             if (operationNumber == 0) {
                 // addLast
                 int randVal = StdRandom.uniform(0, 100);
                 L1.addLast(randVal);
                 L2.addLast(randVal);
//                 System.out.println("addLast(" + randVal + ")");
             } else if (operationNumber == 1) {
                 // size
                 int size = L1.size();
                 assertEquals(L1.size(),L2.size());
//                 System.out.println("size: " + size);
             } else if (operationNumber == 2) {
                 // getLast
                 if( L1.size() == 0 ) continue;
                 int last1 = L1.getLast();
                 int last2 = L2.getLast();
                 assertEquals(last1,last2);
//                 System.out.println("getLast: " + last1);
             } else if (operationNumber == 3) {
                 // removeLast
                 if( L1.size() == 0 ) continue;
                 int last1 = L1.removeLast();
                 int last2 = L2.removeLast();
                 assertEquals(last1,last2);
//                 System.out.println("removeLast: " + last1);
             }
         }
     }
}
