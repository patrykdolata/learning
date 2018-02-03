package dolata.patryk;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Patryk Dolata on 29.01.2018.
 */
public class MainTest {
    @Test
    public void testGetMaxSerialNoIdOnlyNumbers() {
        //Given
        Map<String, AlertData> map = new ConcurrentHashMap<>();
        map.put("1", new AlertData("12317", "22214"));
        map.put("2", new AlertData("1231", "22214"));
        map.put("3", new AlertData("121", "22214"));
        map.put("4", new AlertData("1232", "22212"));
        map.put("5", new AlertData("1212", "22212"));
        map.put("6", new AlertData("120123", "22212"));
        map.put("7", new AlertData("-120123", "22212"));
        Optional<String> exceptedResult = Optional.of("120123");
        //When
        Optional<String> result = Main.getMaxSerialNo(map);
        //Then
        assertEquals(exceptedResult, result);
    }

    @Test
    public void testGetMaxSerialNoIdWithLetters() {
        //Given
        Map<String, AlertData> map = new ConcurrentHashMap<>();
        map.put("1", new AlertData("12317", "22214"));
        map.put("2", new AlertData("1231e", "22214"));
        map.put("3", new AlertData("121", "22214"));
        map.put("4", new AlertData("1232", "22212"));
        map.put("5", new AlertData("1212", "22212"));
        map.put("6", new AlertData("120123", "22212"));
        map.put("7", new AlertData("-120123", "22212"));
        //When
        try {
            Optional<String> result = Main.getMaxSerialNo(map);
        } catch (NumberFormatException e){
         //Then
            assertThat(e.getMessage(), containsString("1231e"));
        }
    }

    @Test
    public void testGetMaxSerialNoWithEmptyId(){
        Map<String, AlertData> map = new ConcurrentHashMap<>();
        map.put("1", new AlertData("", "22214"));
        map.put("2", new AlertData("1231", "22214"));
        //When
        try {
            Optional<String> result = Main.getMaxSerialNo(map);
        } catch (NumberFormatException e){
            //Then
            assertThat(e.getMessage(), containsString("Zero length BigInteger"));
        }
    }
}