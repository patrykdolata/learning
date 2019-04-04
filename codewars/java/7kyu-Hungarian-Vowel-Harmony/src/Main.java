import java.util.Arrays;
import java.util.List;

public class Main {
    /*Vowel Harmony Rules (simplified)
    When the last vowel in the word is

    a front vowel (e, é, i, í, ö, ő, ü, ű) the suffix is -nek
    a back vowel (a, á, o, ó, u, ú) the suffix is -nak
    Examples:
    Kata.dative("ablak") == "ablaknak"
    Kata.dative("szék") == "széknek"
    Kata.dative("otthon") == "otthonnak"*/
    public static void main(String[] args) {
        System.out.println(dative("ablak"));
        System.out.println(dative("szék"));
        System.out.println(dative("otthon"));

        System.out.println(dativeClever("ablak"));
        System.out.println(dativeClever("szék"));
        System.out.println(dativeClever("otthon"));
    }

    public static String dative(String word) {
        List<Character> frontVowels = Arrays.asList('e', 'é', 'i', 'í', 'ö', 'ő', 'ü', 'ű');
        List<Character> backVowels = Arrays.asList('a', 'á', 'o', 'ó', 'u', 'ú');

        for (int index = word.length() - 2; index >= 0; index--) {
            if (frontVowels.contains(word.charAt(index))) {
                return word + "nek";
            }
            if (backVowels.contains(word.charAt(index))) {
                return word + "nak";
            }
        }
        return "";
    }

    public static String dativeClever(String word) {
        int lastFrontVowelIndex = word.replaceAll("[eéiíöőüű]", "1").lastIndexOf('1');
        int lastBackVowelIndex = word.replaceAll("[aáoóuú]", "2").lastIndexOf('2');
        return lastFrontVowelIndex > lastBackVowelIndex ? word + "nek" : word + "nak";
    }
}
