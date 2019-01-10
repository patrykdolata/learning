import java.util.*;

//compose(s1, s2) -> "a3456\nefyz1\nijkuv\nmnopq"
//        or printed:
//        abcd    qrst  -->  a3456
//        efgh    uvwx       efyz1
//        ijkl    yz12       ijkuv
//        mnop    3456       mnopq
public class Main {

    public static void main(String[] args) {
        System.out.println(compose("byGt\nhTts\nRTFF\nCnnI","jIRl\nViBu\nrWOb\nNkTB"));
    }

    public static String compose(String s1, String s2){
        String DELIMITER = "\n";
        List<String> a1 = Arrays.asList(s1.split(DELIMITER));
        List<String> a2 = Arrays.asList(s2.split(DELIMITER));
        Collections.reverse(a2);
        List<String> composed = new ArrayList<>();
        for(int index = 0; index < a1.size(); index++){
            String word = a1.get(index).substring(0, index + 1)
                    + a2.get(index).substring(0, a2.size()-index);
            composed.add(word);
        }
        return String.join(DELIMITER, composed);
    }
}
