package collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CollectionStart {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2,3,4,5);
        Iterator<Integer> iterableList = list.iterator();

        while(iterableList.hasNext()){
            int val = iterableList.next();
            System.out.println("Val: "+val);
        }
    }
}
