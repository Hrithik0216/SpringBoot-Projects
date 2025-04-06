package Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
    public static int solve(int x, int y) {
        try {
            return x / y;
        } catch (Exception e) {
            System.out.println("The err is" + e);
            return 0;
        }
    }

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(2);
        numbers.add(1);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
//        List<Integer> result = numbers.stream()
//                .filter(n->n%2==0)
//                .map(n-> Test.solve(n,2))
//                .toList();

//        Map<Integer, Integer> mapWithValue = result.stream().collect(Collectors.toMap(n->result.indexOf(n), n-> n));
//        List<Double> result = numbers.stream().map(n -> Math.pow(n, 2)).collect(Collectors.toList());
//        List<String> result = numbers.stream().
//                map(n -> String.valueOf(n)).
//                toList();

//        List<Integer> result = numbers.stream().
//                sorted((n1,n2)->n2-n1).
//                toList();

//        List<Integer> result = numbers.stream().peek(n->System.out.println(n)).collect(Collectors.toList());
//        int result = numbers.stream().reduce(0,(a,b)->a+b);
        int result = numbers.parallelStream().max((a,b)->b-a).get();
        System.out.println(result);

    }


}
