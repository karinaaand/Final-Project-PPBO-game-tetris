import java.awt.Color;

public abstract class Tetromino {
    protected int[][][] shapes;
    protected int currentShape;
    protected Color color;
    protected int x;
    protected int y;

    public Tetromino(int[][][] shapes, Color color) {
        this.shapes = shapes;
        this.color = color;
        this.currentShape = 0;
        this.x = 0;
        this.y = 0;
    }

    public int[][] getShape() {
        return this.shapes[this.currentShape];
    }

    public Color getColor() {
        return this.color;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveDown() {
        ++this.y;
    }

    public void moveLeft() {
        --this.x;
    }

    public void moveRight() {
        ++this.x;
    }

    public void rotate() {
        this.currentShape = (this.currentShape + 1) % this.shapes.length;
    }

    public void rotateBack() {
        this.currentShape = (this.currentShape + this.shapes.length - 1) % this.shapes.length;
    }

    public abstract void printType();
}
