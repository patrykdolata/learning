import java.awt.*;

public class Main2 {
    public static int[][] spiralize(int size) {
        int[][] result = new int[size][size];
        Point position = new Point(-1, 0);
        int offset = 0;
        Main.Direction direction = Main.Direction.RIGHT;
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
                    if (direction == Main.Direction.UP) {
                        offset += 2;
                    }
                } else {
                    break;
                }
            }
        }

        return result;
    }

    private static boolean canPutOneHere(Main.Direction direction, Point position, int[][] result) {
        int size = result.length;
        if (direction != Main.Direction.LEFT && position.x + 1 < size && result[position.y][position.x + 1] == 1) {
            return false;
        }
        if (direction != Main.Direction.RIGHT && position.x - 1 > 0 && result[position.y][position.x - 1] == 1) {
            return false;
        }
        if (direction != Main.Direction.UP && position.y + 1 < size && result[position.y + 1][position.x] == 1) {
            return false;
        }
        if (direction != Main.Direction.DOWN && position.y - 1 > 0 && result[position.y - 1][position.x] == 1) {
            return false;
        }
        return true;
    }

    private static Main.Direction getNextDirection(Main.Direction direction) {
        switch (direction) {
            case RIGHT:
                return Main.Direction.DOWN;
            case DOWN:
                return Main.Direction.LEFT;
            case LEFT:
                return Main.Direction.UP;
            case UP:
                return Main.Direction.RIGHT;
        }
        return Main.Direction.ERROR;
    }

    private static boolean canChangeDirection(Main.Direction direction, Point position, int[][] result) {
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

    private static boolean canMoveForward(Main.Direction direction, Point position, int[][] result, Integer offset) {
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

    private static void movePosition(Main.Direction direction, Point position) {
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
}
