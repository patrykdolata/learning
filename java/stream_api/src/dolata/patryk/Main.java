package dolata.patryk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
	    final int elements = 25;
        //Lists:
        List<String> feedList = new ArrayList<>();
        for(int index = 0; index < elements; index++){
            feedList.add(String.valueOf(index));
        }
        System.out.println("Feed list:");
        feedList.forEach(e -> System.out.print(e + " "));

        System.out.println("\nGet only less then 10:");
        List<String> lessThan10 = feedList.stream()
                .filter(e -> Integer.valueOf(e) < 10)
                .collect(Collectors.toList());
        lessThan10.forEach(e -> System.out.print(e + " "));

        System.out.println("\nGet partitions by max 3 elements:");
        final AtomicInteger counter = new AtomicInteger();
        Collection<List<String>> partitions = feedList.stream()
                .collect(Collectors.groupingBy(e -> counter.getAndIncrement()/3))
                .values();
        partitions.forEach(e -> System.out.print(e + " "));

        System.out.println("\nGet sorted descending:");
        List<String> desc = feedList.stream()
                .sorted((e1, e2) -> Long.compare(Long.valueOf(e2), Long.valueOf(e1)))
                .collect(Collectors.toList());
        desc.forEach(e -> System.out.print(e + " "));
    }
}
