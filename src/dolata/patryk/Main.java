package dolata.patryk;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) {
        Map<String, AlertData> map = new ConcurrentHashMap<>();
        map.put("1", new AlertData("1231", "22214"));
        map.put("2", new AlertData("1231", "22214"));
        map.put("3", new AlertData("1211", "22214"));
        map.put("4", new AlertData("1232", "22212"));
        map.put("5", new AlertData("9212", "22212"));
        map.put("6", new AlertData("1203", "22212"));
        Optional<String> maxSerial = getMaxSerialNo(map);
        maxSerial.ifPresent(System.out::println);
    }

    public static Optional<String> getMaxSerialNo(Map<String, AlertData> map) {
        return map.values().stream().sorted().findFirst().map(AlertData::getId);
    }
}
