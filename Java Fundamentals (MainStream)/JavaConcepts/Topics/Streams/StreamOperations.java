package Streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamOperations {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("Hey", "Hrithik", "How are you?");
        List<String> resultList = stringList.stream().map((str) -> str.toUpperCase()).collect(Collectors.toList());
        System.out.print(resultList.toString());

        List<List<String>> stringList2 = Arrays.asList(Arrays.asList("Hey", "Hrithik", "How are you?"), Arrays.asList("Hey", "Hrithik", "How are you?"));
        List<String> result2 = stringList2.stream().flatMap((val) -> val.stream().map(str -> str.toUpperCase())).collect(Collectors.toList());
        System.out.println(result2.toString());
    }
}
