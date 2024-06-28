import java.awt.Color;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.BorderLayout;


public class Tetris extends JFrame {
    private Board board = new Board();
    private JLabel scoreLabel = new JLabel("Score: 0 Level: 1");
    private NextPiecePanel nextPiecePanel;

    public Tetris() {
        this.scoreLabel.setForeground(Color.BLACK);
        this.scoreLabel.setBackground(Color.LIGHT_GRAY);
        this.scoreLabel.setOpaque(true);
        this.scoreLabel.setFont(new Font("Arial", 1, 16));
        this.nextPiecePanel = new NextPiecePanel();
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.DARK_GRAY);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(this.nextPiecePanel);
        rightPanel.add(Box.createVerticalGlue());
        this.add(this.board, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(this.scoreLabel, BorderLayout.NORTH);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Tetris");
        Timer timer = new Timer(100, (e) -> {
            this.updateScoreAndNextPiece();
            this.checkForLevelUp();
        });
        timer.start();
    }

    private void updateScoreAndNextPiece() {
        this.scoreLabel.setText("Score: " + this.board.getScore() + " Level: " + this.board.getLevel());
        this.nextPiecePanel.setNextPiece(this.board.getNextPiece());
    }

    private void updateScoreAndNextPiece(int score, Tetromino nextPiece) {
        this.scoreLabel.setText("Score: " + score + " Level: " + this.board.getLevel());
        this.nextPiecePanel.setNextPiece(nextPiece);
        this.scoreLabel.repaint();
    }

    private void checkForLevelUp() {
        if (this.board.getScore() >= 3000) {
            int bonusScore = this.board.getScore() + 500;
//            Tetromino specialPiece = new OTetromino();
            this.updateScoreAndNextPiece(bonusScore, null); //kalau skor diatas 3500 akan hilang bantuan next piece
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tetris tetris = new Tetris();
            tetris.setVisible(true);
        });
    }
}

