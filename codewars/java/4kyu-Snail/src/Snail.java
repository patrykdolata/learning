import java.awt.*;

public class Snail {
    public static int[] snail(int[][] array) {
        int size = array[0].length;
        int[] result = new int[size*size];
        int index = 0;
        Point position = new Point(-1, 0);
        Direction direction = Direction.RIGHT;
        int offset = 0;
        while (index < result.length) {
            if(canMoveForward(direction, position, array, offset)){
                movePosition(direction, position);
                result[index] = array[position.y][position.x];
                index++;
            } else {
                direction = getNextDirection(direction);
                if(direction == Direction.LEFT){
                    offset += 1;
                }
            }
        }
        return result;
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

    private static boolean canMoveForward(Direction direction, Point position, int[][] result, Integer offset) {
        int size = result.length;
        switch (direction) {
            case RIGHT:
                return position.x < size - 1 - offset;
            case DOWN:
                return position.y < size - 1 - offset;
            case LEFT:
                return position.x >= offset;
            case UP:
                return position.y > offset;
        }
        return false;
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

    enum Direction {
        RIGHT,
        DOWN,
        LEFT,
        UP,
        ERROR
    }
}
