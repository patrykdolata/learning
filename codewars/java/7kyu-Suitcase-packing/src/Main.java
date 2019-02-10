import static java.lang.Math.max;
import static java.lang.Math.min;

/*
    Mr. Square is going on a holiday. He wants to bring 2 of his favorite squares
    with him, so he put them in his rectangle suitcase.
    Write a function that, given the size of the squares and the suitcase,
    return whether the squares can fit inside the suitcase.
*/
public class Main {

    public static void main(String[] args) {
        System.out.println(fit_in(7, 2, 8, 7));
    }

    public static boolean fit_in(int squareASize, int squareBSize, int rectangleWidth, int rectangleHeight) {
        int suitcaseField = rectangleWidth * rectangleHeight;
        int squareAField = squareASize * squareASize;
        int squareBField = squareBSize * squareBSize;

        if (suitcaseField < (squareAField + squareBField)) {
            return false;
        }
        if (squareASize + squareBSize > max(rectangleWidth, rectangleHeight)) {
            return false;
        }
        return max(squareASize, squareBSize) <= min(rectangleWidth, rectangleHeight);
    }
}
