public class Main {
    /*
    Given a 2D array and a number of generations, compute n timesteps of Conway's Game of Life.

    The rules of the game are:
    Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
    Any live cell with more than three live neighbours dies, as if by overcrowding.
    Any live cell with two or three live neighbours lives on to the next generation.
    Any dead cell with exactly three live neighbours becomes a live cell.
    Each cell's neighborhood is the 8 cells immediately around it (i.e. Moore Neighborhood).
    The universe is infinite in both the x and y dimensions and all cells are initially dead
    - except for those specified in the arguments.
    The return value should be a 2d array cropped around all of the living cells.
    (If there are no living cells, then return [[]].)

    For illustration purposes, 0 and 1 will be represented as ░░ and ▓▓ blocks respectively
    (PHP, C: plain black and white squares). You can take advantage of the htmlize function to get a text
    representation of the universe, e.g.:
    */

    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[][] test = {{1, 1, 1, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 1, 1, 1}};
        int[][] test2 = {{1, 0, 0},
                {0, 1, 1},
                {1, 1, 0}};
        int[][] test3 = {{0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}};
        int[][] result = getGeneration(test, 16);
        showTable(result);
    }

    private static void showTable(int[][] cells) {
        for (int[] cell : cells) {
            for (int x = 0; x < cells[0].length; x++) {
                System.out.print(cell[x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int[][] getGeneration(int[][] cells, int generations) {
        int[][] resultCells = cells;
        for (int gen = 0; gen < generations; gen++) {
            resultCells = new int[cells.length + 2][cells[0].length + 2];
            for (int y = 0; y < cells.length + 2; y++) {
                for (int x = 0; x < cells[0].length + 2; x++) {
                    int[][] mask = getMask(cells, x, y);
                    resultCells[y][x] = analyzeMask(mask);
                }
            }
            cells = resultCells;
        }

        return crop(resultCells);
    }

    private static int[][] crop(int[][] resultCells) {
        if (resultCells[0].length > 0) {
            resultCells = removeUpLeftCorner(resultCells);
            resultCells = removeDownRightCorner(resultCells);
        }
        return resultCells;
    }

    private static int[][] removeDownRightCorner(int[][] resultCells) {
        int xOffset = resultCells[0].length-1;
        int yOffset = resultCells.length-1;
        int downRowsToDelete = 0;
        int downColumnsToDelete = 0;
        boolean isRowToDelete = true;
        boolean isColumnToDelete = true;
        while (isRowToDelete || isColumnToDelete) {
            if (rowContainsOnlyZeros(resultCells, yOffset)) {
                downRowsToDelete++;
            } else {
                isRowToDelete = false;
            }
            if (columnContainsOnlyZeros(resultCells, xOffset)) {
                downColumnsToDelete++;
            } else {
                isColumnToDelete = false;
            }
            xOffset--;
            yOffset--;
        }

        return copyRightDownArray(resultCells, downRowsToDelete, downColumnsToDelete);
    }

    private static int[][] removeUpLeftCorner(int[][] resultCells) {
        int xOffset = 0;
        int yOffset = 0;
        int upRowsToDelete = 0;
        int upColumnsToDelete = 0;
        boolean isRowToDelete = true;
        boolean isColumnToDelete = true;
        while (isRowToDelete || isColumnToDelete) {
            if (rowContainsOnlyZeros(resultCells, yOffset)) {
                upRowsToDelete++;
            } else {
                isRowToDelete = false;
            }
            if (columnContainsOnlyZeros(resultCells, xOffset)) {
                upColumnsToDelete++;
            } else {
                isColumnToDelete = false;
            }
            xOffset++;
            yOffset++;
        }

        return copyLeftPartArray(resultCells, upRowsToDelete, upColumnsToDelete);
    }

    private static boolean rowContainsOnlyZeros(int[][] cells, int yOffset) {
        for (int x = 0; x < cells[0].length; x++) {
            if (cells[yOffset][x] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean columnContainsOnlyZeros(int[][] cells, int xOffset) {
        for (int y = 0; y < cells.length; y++) {
            if (cells[y][xOffset] == 1) {
                return false;
            }
        }
        return true;
    }

    private static int[][] copyLeftPartArray(int[][] cells, int yCut, int xCut) {
        if (yCut == 0 && xCut == 0) {
            return cells;
        }
        int[][] copiedArray = new int[cells.length - yCut][cells[0].length - xCut];
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[0].length; x++) {
                if (y - yCut >= 0 && x - xCut >= 0) {
                    copiedArray[y - yCut][x - xCut] = cells[y][x];
                }
            }
        }
        return copiedArray;
    }

    private static int[][] copyRightDownArray(int[][] cells, int yCut, int xCut) {
        if (yCut == 0 && xCut == 0) {
            return cells;
        }
        int[][] copiedArray = new int[cells.length - yCut][cells[0].length - xCut];
        for (int y = 0; y < copiedArray.length; y++) {
            System.arraycopy(cells[y], 0, copiedArray[y], 0, copiedArray[0].length);
        }
        return copiedArray;
    }

    private static int analyzeMask(int[][] mask) {
        int liveCells = getLiveCells(mask);
        int cellToChange = mask[1][1];
        if (cellToChange == 0 && liveCells == 3) {
            return 1;
        } else {
            if (liveCells < 2 || liveCells > 3) {
                return 0;
            }
        }
        return cellToChange;
    }

    private static int getLiveCells(int[][] mask) {
        int liveCells = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (!(x == 1 && y == 1)) {
                    if ((mask[x][y] == 1)) {
                        liveCells++;
                    }
                }
            }
        }
        return liveCells;
    }

    static int[][] getMask(int[][] cells, int xOffset, int yOffset) {
        int[][] mask = new int[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int xCellPosition = x - 2 + yOffset;
                int yCellPosition = y - 2 + xOffset;
                if (xCellPosition >= 0
                        && yCellPosition >= 0
                        && xCellPosition < cells.length
                        && yCellPosition < cells[0].length) {
                    mask[x][y] = cells[xCellPosition][yCellPosition];
                } else {
                    mask[x][y] = 0;
                }
            }
        }
        return mask;
    }
}
