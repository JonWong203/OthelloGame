package org.cis120;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;


public class TestReview {



    public static void main (String args[]) {
//        TestReview a = new TestReview();
//        TestReview.Inner b = (new TestReview()).new Inner(2);
//        Iterable<String> name = new TreeSet<String>();
//        int [][] b = new int[4][];
//        b[0] = new int[] {0,1,2,3,0};
//        b[1] = new int[] {4,5};
//        b[2] = new int[] {6,7,8};
//        b[3] = new int[] {9};
//        int [][] temp = pad(b);
//        for(int row = 0; row <= temp.length - 1; row++) {
//            for (int col = 0; col <= temp[row].length-1; col++){
//                System.out.print(temp[row][col]);
//            }
//            System.out.println();
//        }

    }

    public class Inner {
        private int field;
        public Inner(int x) {
            field = x;
        }
    }

    public static int[][] pad(int[][] a) {
        int max = 0;
        for (int i = 0; i <= a.length-1; i ++) {
            if (a[i].length > max) {
                max = a[i].length;
            }
        }
        int [][] result = new int[a.length][max];
        for(int row = 0; row <= a.length - 1; row++) {
            for (int col = 0; col <= a[row].length-1; col++){
                result[row][col] = a[row][col];
            }
        }
        return result;
    }


    public Map<Integer, Set<String>> transpose (Map<String, Integer> inputMap) {
        Map<Integer, Set<String>> tgt = new TreeMap<Integer, Set<String>>();
        for (Map.Entry<String, Integer> entry : inputMap.entrySet()) {
            Integer className = entry.getValue();
            Set<String> s = new TreeSet<>();
            if (tgt.containsKey(className)) {
                s = tgt.get(className);
            }
            else {
                tgt.put(className, s);
            }
            s.add(entry.getKey());
        }
        return tgt;
    }




}
