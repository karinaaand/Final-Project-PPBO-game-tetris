import java.awt.Color;

public class ITetromino extends Tetromino {
    private static final int[][][] SHAPES = new int[][][]{{{1, 1, 1, 1}}, {{1}, {1}, {1}, {1}}};

    public ITetromino() {
        super(SHAPES, Color.CYAN);
    }

    public void printType() {
        System.out.println("This is an I-Tetromino");
    }
}