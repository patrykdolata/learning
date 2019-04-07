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


    private static void showResult(int[][] result) {
        for (int y = 0; y < result.length; y++) {
            for (int x = 0; x < result[0].length; x++) {
                System.out.print(result[y][x]);
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
//        int[][] testResult = {{1, 1, 1, 1, 1}, {0, 0, 0, 0, 1}, {1, 1, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}};
        int[][] testResult = spiralize(10);
        showResult(testResult);
    }

    public static int[][] spiralize(int size) {
        int[][] result = new int[size][size];
        Direction direction = Direction.RIGHT;
        int offset = 0;
        Point position = new Point(0, 0);
        while (true) {
            if (direction == Direction.RIGHT) {
                if (position.x != size - 1 - offset) {
                    if (isNextFieldValid(direction, position, result)) {
                        result[position.y][position.x] = 1;
                    }
                    position.x++;
                } else {
                    result[position.y][position.x] = 1;
                    if (canTurn(direction, position, result)) {
                        direction = Direction.DOWN;
                        position.y++;
                    } else {
                        break;
                    }
                }
            } else if (direction == Direction.DOWN) {
                if (position.y != size - 1) {
                    if (isNextFieldValid(direction, position, result)) {
                        result[position.y][position.x] = 1;
                    }
                    position.y++;
                } else {
                    result[position.y][position.x] = 1;
                    if (canTurn(direction, position, result)) {
                        direction = Direction.LEFT;
                        position.x--;
                    } else {
                        break;
                    }
                }

            } else if (direction == Direction.LEFT) {
                if (position.x > offset) {
                    if (isNextFieldValid(direction, position, result)) {
                        result[position.y][position.x] = 1;
                    }
                    position.x--;
                } else {
                    result[position.y][position.x] = 1;
                    if (canTurn(direction, position, result)) {
                        direction = Direction.UP;
                        position.y--;
                    } else {
                        break;
                    }
                }
            } else if (direction == Direction.UP) {
                if (position.y > offset) {
                    if (isNextFieldValid(direction, position, result)) {
                        result[position.y][position.x] = 1;
                    }
                    position.y--;
                } else {
                    position.y += 2;
                    if (canTurn(direction, position, result)) {
                        direction = Direction.RIGHT;
                        position.x++;
                        offset += 2;
                    } else {
                        break;
                    }
                }
            }
        }
        return result;
    }

    private static boolean isNextFieldValid(Direction direction, Point position, int[][] result) {
        int size = result.length;
        switch (direction) {
            case RIGHT:
                return position.x + 1 < size && result[position.y][position.x + 1] != 1;
            case DOWN:
                return position.y + 1 < size && result[position.y + 1][position.x] != 1;
            case LEFT:
                return result[position.y][position.x - 1] != 1;
            case UP:
                return result[position.y - 1][position.x] != 1;
        }
        return false;
    }

    private static boolean canTurn(Direction direction, Point position, int[][] result) {
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

    enum Direction {
        RIGHT,
        DOWN,
        LEFT,
        UP
    }
}
