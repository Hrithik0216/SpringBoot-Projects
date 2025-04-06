package ParrallelStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Task {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long  sum = LongStream.rangeClosed(0, 1000000000).sum();
        long end = System.currentTimeMillis();
        System.out.println("Sequential Time: " + (end - start)  + " ms");

        long startParallel = System.currentTimeMillis();
        long sumParallel = LongStream.rangeClosed(0, 1000000000).parallel().sum();
        long endParallel = System.currentTimeMillis();
        System.out.println("Parallel Time: " + (endParallel - startParallel)  + " ms");

        List<Integer> data = Collections.synchronizedList(new ArrayList<>());
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8).parallelStream().map(i -> {
            data.add(i); // // AVOID STATEFUL LAMBDA EXPRESSIONS!
            return i;
        }).forEachOrdered(i -> System.out.print(i + " "));

        System.out.println();
        System.out.println(data);

        List<Integer> dataVal = Arrays.asList(1,32,34,5,54,5,6,23);
        List<Integer> result = dataVal.stream().map(i->i*i)
                .collect(()->new ArrayList<>(),
                        (newList,val)->newList.add(val),
                        (leftlist, rightList) ->  leftlist.addAll(rightList));
        System.out.println(result);
        List<Integer> result2 = dataVal.parallelStream().map(i->i*i)
                .collect(()->new ArrayList<>(),
                        (newList,val)->newList.add(val),
                        (leftlist, rightList) ->  leftlist.addAll(rightList));
        System.out.println(result2);

        List<Integer> result3 = dataVal.parallelStream().map(i->i*i)
                .collect(Collectors.toCollection(()->new ArrayList<>()));
        System.out.println(result3);

    }
}
