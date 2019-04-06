import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MainTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Input(new int[][]{{0, 1, 0}}, 0, 0), new int[][]{{0, 0, 0}, {0, 0, 1}, {0, 0, 0}}},
                {new Input(new int[][]{{0, 1}}, 0, 0), new int[][]{{0, 0, 0}, {0, 0, 1}, {0, 0, 0}}},
                {new Input(new int[][]{{1}}, 0, 0), new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}},
                {new Input(new int[][]{{0, 1, 0}, {0, 1, 0}}, 0, 0), new int[][]{{0, 0, 0}, {0, 0, 1}, {0, 0, 1}}},
                {new Input(new int[][]{{1, 1, 0}, {0, 1, 1}}, 0, 0), new int[][]{{0, 0, 0}, {0, 1, 1}, {0, 0, 1}}},
                {new Input(new int[][]{{1, 1, 1}, {1, 1, 1}}, 0, 0), new int[][]{{0, 0, 0}, {0, 1, 1}, {0, 1, 1}}},
                {new Input(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, 1, 0), new int[][]{{0, 0, 0}, {1, 1, 1}, {1, 1, 1}}},
                {new Input(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 0), new int[][]{{0, 0, 0}, {1, 1, 1}, {1, 1, 0}}},
                {new Input(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, 1, 0), new int[][]{{0, 0, 0}, {1, 1, 1}, {1, 1, 1}}},
                {new Input(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, 1, 1), new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}},
                {new Input(new int[][]{{0, 1, 0}, {1, 1, 1}, {1, 1, 1}}, 1, 1), new int[][]{{0, 1, 0}, {1, 1, 1}, {1, 1, 1}}},
                {new Input(new int[][]{{1, 1, 0}, {1, 1, 1}, {1, 1, 1}}, 2, 1), new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 1, 0}}},
                {new Input(new int[][]{{0, 1, 1}, {1, 1, 1}, {1, 1, 1}}, 2, 1), new int[][]{{1, 1, 0}, {1, 1, 0}, {1, 1, 0}}},
                {new Input(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, 2, 2), new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 0}}}
        });
    }

    private int[][] input;
    private int[][] expected;
    private int xOffset;
    private int yOffset;

    public MainTest(Input input, int[][] expected) {
        this.input = input.cells;
        this.expected = expected;
        this.xOffset = input.xOff;
        this.yOffset = input.yOff;
    }

    @Test
    public void testGetMask() {
        assertEquals(expected, Main.getMask(input, xOffset, yOffset));
    }

    static class Input {
        int[][] cells;
        int xOff;
        int yOff;

        Input(int[][] cells, int xOff, int yOff) {
            this.cells = cells;
            this.xOff = xOff;
            this.yOff = yOff;
        }
    }
}