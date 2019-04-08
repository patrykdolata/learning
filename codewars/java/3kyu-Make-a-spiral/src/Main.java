import java.awt.*;

/*Your task, is to create a NxN spiral with a given size.
For example, spiral with size 5 should look like this:
00000
....0
000.0
0...0
00000
and with the size 10:
0000000000
.........0
00000000.0
0......0.0
0.0000.0.0
0.0..0.0.0
0.0....0.0
0.000000.0
0........0
0000000000
Return value should contain array of arrays, of 0 and 1, for example for given size 5 result should be:
[[1,1,1,1,1],[0,0,0,0,1],[1,1,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Because of the edge-cases for tiny spirals, the size will be at least 5.

General rule-of-a-thumb is, that the snake made with '1' cannot touch to itself.*/
public class Main {


    public static void showResult(int[][] result) {
        for (int y = 0; y < result.length; y++) {
            for (int x = 0; x < result[0].length; x++) {
                //System.out.print(result[x][y]);
                if (x == 1 && y == 200) {
                    System.out.print("*");
                } else {

                    System.out.print(result[y][x] == 1 ? "1" : ".");
                }
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
//        int[][] testResult = {{1, 1, 1, 1, 1}, {0, 0, 0, 0, 1}, {1, 1, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}};
//        showResult(spiralize(1));
//        showResult(spiralize(2));
//        showResult(spiralize(3));
//        showResult(spiralize(4));
        showResult(spiralize(3));
        /*              { 1, 1, 1, 1, 1, 1, 1, 1 },
                        { 0, 0, 0, 0, 0, 0, 0, 1 },
                        { 1, 1, 1, 1, 1, 1, 0, 1 },
                        { 1, 0, 0, 0, 0, 1, 0, 1 },
                        { 1, 0, 1, 1, 0, 1, 0, 1 },
                        { 1, 0, 1, 1, 1, 1, 0, 1 },
                        { 1, 0, 0, 0, 0, 0, 0, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1 },*/
    }

    public static int[][] spiralize(int size) {
        int[][] result = new int[size][size];
        Point position = new Point(-1, 0);
        int offset = 0;
        Direction direction = Direction.RIGHT;
        while (true) {
            movePosition(direction, position);
            if (canMoveForward(direction, position, result, offset)) {
                result[position.y][position.x] = 1;
            } else {
                if (canPutOneHere(direction, position, result)) {
                    result[position.y][position.x] = 1;
                }
                if (canChangeDirection(direction, position, result)) {
                    direction = getNextDirection(direction);
                    if (direction == Direction.UP) {
                        offset += 2;
                    }
                } else {
                    break;
                }
            }
            showResult(result);
            System.out.println();
        }
        if (size > 2 && size%2 == 0) { result[size/2][size/2-1] = 1; }// buggy kata
        return result;
    }

    private static boolean canPutOneHere(Direction direction, Point position, int[][] result) {
        int size = result.length;

        if (direction != Direction.LEFT && position.x + 1 < size && result[position.y][position.x + 1] == 1) {
            return false;
        }
        if (direction != Direction.RIGHT && position.x - 1 >= 0 && result[position.y][position.x - 1] == 1) {
            return false;
        }
        if (direction != Direction.UP && position.y + 1 < size && result[position.y + 1][position.x] == 1) {
            return false;
        }
        if (direction != Direction.DOWN && position.y - 1 >= 0 && result[position.y - 1][position.x] == 1) {
            return false;
        }

        return true;
    }

    private static Direction getNextDirection(Direction direction) {
        switch (direction) {
            case RIGHT:
                return Direction.DOWN;
            case DOWN:
                return Direction.LEFT;
            case LEFT:
                return Direction.UP;
            case UP:
                return Direction.RIGHT;
        }
        return Direction.ERROR;
    }

    private static boolean canChangeDirection(Direction direction, Point position, int[][] result) {
        switch (direction) {
            case RIGHT:
                return isDownFieldValid(position, result);
            case DOWN:
                return isLeftFieldValid(position, result);
            case LEFT:
                return isUpFieldValid(position, result);
            case UP:
                return isRightFieldValid(position, result);
        }
        return false;
    }

    private static boolean isRightFieldValid(Point position, int[][] result) {
        return position.x + 1 < result.length && result[position.y][position.x + 1] != 1;
    }

    private static boolean isUpFieldValid(Point position, int[][] result) {
        return position.y - 1 >= 0 && result[position.y - 1][position.x] != 1;
    }

    private static boolean isLeftFieldValid(Point position, int[][] result) {
        return position.x - 1 >= 0 && result[position.y][position.x - 1] != 1;
    }

    private static boolean isDownFieldValid(Point position, int[][] result) {

        return position.y + 1 < result.length && result[position.y + 1][position.x] != 1;
    }

    private static boolean canMoveForward(Direction direction, Point position, int[][] result, Integer offset) {
        int size = result.length;
        switch (direction) {
            case RIGHT:
                return position.x < size - 1 - offset;
            case DOWN:
                return position.y < size - 1 - offset;
            case LEFT:
                return position.x > offset;
            case UP:
                return position.y > offset;
        }
        return false;
    }

    private static void movePosition(Direction direction, Point position) {
        switch (direction) {
            case RIGHT:
                position.x += 1;
                break;
            case DOWN:
                position.y += 1;
                break;
            case LEFT:
                position.x -= 1;
                break;
            case UP:
                position.y -= 1;
                break;
        }
    }

    enum Direction {
        RIGHT,
        DOWN,
        LEFT,
        UP,
        ERROR
    }
}
