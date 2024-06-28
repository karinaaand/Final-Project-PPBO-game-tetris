import java.awt.Color;

public class GenericTetromino extends Tetromino {
    public GenericTetromino(int[][][] shapes, Color color) {
        super(shapes, color);
    }

    public void printType() {
        System.out.println("This is a generic Tetromino");
    }
}
