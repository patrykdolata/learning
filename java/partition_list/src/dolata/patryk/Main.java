package dolata.patryk;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    private final static int elements = 10;
    private final static int elementsLimit = 5;

    public static void main(String[] args) {
    final AtomicInteger listCounter = new AtomicInteger();
    final AtomicInteger mapCounter = new AtomicInteger();
        Map<String, String> feedMap = new HashMap<>();
        for (int index = 0; index < elements; index++) {
            feedMap.put(String.valueOf(index), Character.toString((char)('A' + index)));
        }
        List<String> feedList = new ArrayList<>();
        for (int index = 0; index < elements; index++) {
            feedList.add(Character.toString((char)('A' + index)));
        }

        Collection<List<String>> partitionedList = feedList.stream()
                .collect(Collectors.groupingBy(element -> listCounter.getAndIncrement()/elementsLimit))
                .values();
        partitionedList.forEach(System.out::println);

        Collection<List<String>> partitionedMap = feedMap.values().stream()
                .collect(Collectors.groupingBy(element -> mapCounter.getAndIncrement()/elementsLimit, Collectors.toList()))
                .values();
        partitionedMap.forEach(System.out::println);

        List<String> partitionedTofeedList = partitionedList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        partitionedTofeedList.forEach(System.out::print);

    }
}
