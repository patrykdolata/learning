public class Main {

    public static void main(String[] args) {
        int[][] array = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int[][] array2 = {{}};

        show2dTable(array);
        showResult(Snail.snail(array2));
    }

    private static void showResult(int[] snail) {
        for (int i : snail) {
            System.out.print(i);
        }
        System.out.println();
    }

    public static void show2dTable(int[][] result) {
        for (int y = 0; y < result.length; y++) {
            for (int x = 0; x < result[0].length; x++) {
                System.out.print(result[y][x]);
            }
            System.out.print("\n");
        }
    }
}
