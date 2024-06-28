import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TetrisKeyAdapter extends KeyAdapter {
    private Board board;

    public TetrisKeyAdapter(Board board) {
        this.board = board;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37:
                this.board.movePieceLeft();
                break;
            case 38:
                this.board.rotatePiece();
                break;
            case 39:
                this.board.movePieceRight();
                break;
            case 40:
                this.board.dropPiece();
        }

    }
}
