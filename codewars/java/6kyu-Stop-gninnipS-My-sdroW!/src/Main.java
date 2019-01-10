import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println(spinWords("Test reverse word"));
    }

    private static String spinWords(String sentence) {
        final String WORD_DELIMITER = " ";
        final int WORD_WITHOUT_REVERSE_LENGTH = 4;
        Stream<String> words = Arrays.stream(sentence.split(WORD_DELIMITER));
        return words.map(word -> word.length() > WORD_WITHOUT_REVERSE_LENGTH ? new StringBuilder(word).reverse() : word)
                    .collect(Collectors.joining(WORD_DELIMITER));
    }
}