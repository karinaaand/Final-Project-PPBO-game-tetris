import java.awt.Color;
import java.util.Random;

public class TetrominoFactory {
    private static final int[][][] I_SHAPE = new int[][][]{{{1, 1, 1, 1}}, {{1}, {1}, {1}, {1}}};
    private static final int[][][] O_SHAPE = new int[][][]{{{1, 1}, {1, 1}}};
    private static final int[][][] T_SHAPE = new int[][][]{{{0, 1, 0}, {1, 1, 1}}, {{1, 0}, {1, 1}, {1, 0}}, {{1, 1, 1}, {0, 1, 0}}, {{0, 1}, {1, 1}, {0, 1}}};
    private static final int[][][] S_SHAPE = new int[][][]{{{0, 1, 1}, {1, 1, 0}}, {{1, 0}, {1, 1}, {0, 1}}};
    private static final int[][][] Z_SHAPE = new int[][][]{{{1, 1, 0}, {0, 1, 1}}, {{0, 1}, {1, 1}, {1, 0}}};
    private static final int[][][] J_SHAPE = new int[][][]{{{1, 0, 0}, {1, 1, 1}}, {{1, 1}, {1, 0}, {1, 0}}, {{1, 1, 1}, {0, 0, 1}}, {{0, 1}, {0, 1}, {1, 1}}};
    private static final int[][][] L_SHAPE = new int[][][]{{{0, 0, 1}, {1, 1, 1}}, {{1, 0}, {1, 0}, {1, 1}}, {{1, 1, 1}, {1, 0, 0}}, {{1, 1}, {0, 1}, {0, 1}}};
    private static final Color[] COLORS;
    private static final int[][][][] SHAPES;

    public TetrominoFactory() {
    }

    public static Tetromino getRandomTetromino() {
        Random rand = new Random();
        int shapeIndex = rand.nextInt(SHAPES.length);
        return new GenericTetromino(SHAPES[shapeIndex], COLORS[shapeIndex]);
    }

    static {
        COLORS = new Color[]{Color.CYAN, Color.YELLOW, Color.MAGENTA, Color.GREEN, Color.RED, Color.BLUE, Color.ORANGE};
        SHAPES = new int[][][][]{I_SHAPE, O_SHAPE, T_SHAPE, S_SHAPE, Z_SHAPE, J_SHAPE, L_SHAPE};
    }
}
