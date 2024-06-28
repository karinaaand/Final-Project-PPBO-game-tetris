import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public final class Board extends JPanel implements ActionListener, Movable {
    private final int boardWidth = 10;
    private final int boardHeight = 20;
    private Tetromino currentPiece;
    private Tetromino nextPiece;
    private Color[][] board;
    private Timer timer;
    private int score;
    private int level;
    private final int LEVEL_UP_SCORE = 200;

    public Board() {
        this.setPreferredSize(new Dimension(300, 600));
        this.board = new Color[20][10];
        this.currentPiece = TetrominoFactory.getRandomTetromino();
        this.nextPiece = TetrominoFactory.getRandomTetromino();
        this.currentPiece.setX(4);
        this.timer = new Timer(500, this);
        this.timer.start();
        this.setFocusable(true);
        this.addKeyListener(new TetrisKeyAdapter(this));
        this.score = 0;
        this.level = 1;
    }

    public Tetromino getNextPiece() {
        return this.nextPiece;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawBackground(g);
        this.drawBoard(g);
        this.drawCurrentPiece(g);
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void drawBoard(Graphics g) {
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                if (board[y][x] != null) {
                    drawBlock(g, x * 30, y * 30, board[y][x]);
                }
            }
        }
    }

    private void drawCurrentPiece(Graphics g) {
        g.setColor(currentPiece.getColor());
        for (int i = 0; i < currentPiece.getShape().length; i++) {
            for (int j = 0; j < currentPiece.getShape()[i].length; j++) {
                if (currentPiece.getShape()[i][j] == 1) {
                    drawBlock(g, (currentPiece.getX() + j) * 30, (currentPiece.getY() + i) * 30, currentPiece.getColor());
                }
            }
        }
    }

    private void drawBlock(Graphics g, int x, int y, Color color) {
        Graphics2D g2d = (Graphics2D) g;
        Color darker = color.darker();
        Color brighter = color.brighter();

        // Draw the main rectangle
        g2d.setPaint(new GradientPaint(x, y, brighter, x + 30, y + 30, darker));
        g2d.fillRect(x, y, 30, 30);

        // Draw the borders for a 3D effect
        g2d.setColor(darker);
        g2d.drawLine(x, y + 29, x + 29, y + 29);
        g2d.drawLine(x + 29, y, x + 29, y + 29);

        g2d.setColor(brighter);
        g2d.drawLine(x, y, x + 29, y);
        g2d.drawLine(x, y, x, y + 29);
    }

    public void dropPiece() {
        if (!this.canMove(this.currentPiece, this.currentPiece.getX(), this.currentPiece.getY() + 1)) {
            this.placePiece();
            this.clearLines();
            this.currentPiece = this.nextPiece;
            this.nextPiece = TetrominoFactory.getRandomTetromino();
            this.currentPiece.setX(4);
            if (!this.canMove(this.currentPiece, this.currentPiece.getX(), this.currentPiece.getY())) {
                this.timer.stop();
                this.showGameOverDialog();
            }
        } else {
            this.currentPiece.setY(this.currentPiece.getY() + 1);
        }

        this.repaint();
    }

    private void showGameOverDialog() {
        JDialog gameOverDialog = new JDialog();
        gameOverDialog.setTitle("Game Over");
        gameOverDialog.setModal(true);
        gameOverDialog.setDefaultCloseOperation(2);
        gameOverDialog.setLayout(new BorderLayout());
        gameOverDialog.getContentPane().setBackground(Color.BLACK);
        JLabel messageLabel = new JLabel("Game Over! Your score is: " + this.score, 0);
        messageLabel.setFont(new Font("Arial", 1, 16));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        messageLabel.setOpaque(true);
        messageLabel.setBackground(Color.BLACK);
        messageLabel.setForeground(Color.WHITE);
        gameOverDialog.add(messageLabel, "Center");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.BLACK);
        JButton restartButton = new JButton("Restart");
        restartButton.setBackground(Color.BLACK);
        restartButton.setForeground(Color.WHITE);
        restartButton.setFocusPainted(false);
        restartButton.addActionListener((e) -> {
            gameOverDialog.dispose();
            this.resetGame();
        });
        buttonPanel.add(restartButton);
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener((e) -> {
            System.exit(0);
        });
        buttonPanel.add(exitButton);
        gameOverDialog.add(buttonPanel, "South");
        gameOverDialog.pack();
        gameOverDialog.setLocationRelativeTo(this);
        gameOverDialog.setVisible(true);
    }

    private void resetGame() {
        this.board = new Color[20][10];
        this.currentPiece = TetrominoFactory.getRandomTetromino();
        this.nextPiece = TetrominoFactory.getRandomTetromino();
        this.currentPiece.setX(4);
        this.score = 0;
        this.level = 1;
        this.timer.start();
        this.repaint();
    }

    public void movePieceLeft() {
        if (this.canMove(this.currentPiece, this.currentPiece.getX() - 1, this.currentPiece.getY())) {
            this.currentPiece.moveLeft();
            this.repaint();
        }

    }

    public void movePieceRight() {
        if (this.canMove(this.currentPiece, this.currentPiece.getX() + 1, this.currentPiece.getY())) {
            this.currentPiece.moveRight();
            this.repaint();
        }

    }

    public void rotatePiece() {
        this.currentPiece.rotate();
        if (!this.canMove(this.currentPiece, this.currentPiece.getX(), this.currentPiece.getY())) {
            this.currentPiece.rotateBack();
        }

        this.repaint();
    }

    private boolean canMove(Tetromino piece, int newX, int newY) {
        int[][] shape = piece.getShape();

        for(int i = 0; i < shape.length; ++i) {
            for(int j = 0; j < shape[i].length; ++j) {
                if (shape[i][j] == 1) {
                    int x = newX + j;
                    int y = newY + i;
                    if (x < 0 || x >= 10 || y < 0 || y >= 20 || this.board[y][x] != null) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void placePiece() {
        int[][] shape = this.currentPiece.getShape();

        for(int i = 0; i < shape.length; ++i) {
            for(int j = 0; j < shape[i].length; ++j) {
                if (shape[i][j] == 1) {
                    this.board[this.currentPiece.getY() + i][this.currentPiece.getX() + j] = this.currentPiece.getColor();
                }
            }
        }

    }

    public int getLevel() {
        return this.level;
    }

    private void updateTimerDelay() {
        int newDelay = Math.max(100, 500 - (level - 1) * 50); // Mengurangi delay setiap level up, dengan batas minimum 100ms
        timer.setDelay(newDelay);
    }

    private void clearLines() {
        int linesCleared = 0;

        for (int y = 0; y < boardHeight; y++) {
            boolean lineFull = true;

            for (int x = 0; x < boardWidth; x++) {
                if (board[y][x] == null) {
                    lineFull = false;
                    break;
                }
            }

            if (lineFull) {
                linesCleared++;

                for (int row = y; row > 0; row--) {
                    System.arraycopy(board[row - 1], 0, board[row], 0, boardWidth);
                }

                Arrays.fill(board[0], null);
            }
        }

        score += linesCleared * 100;

        // Cek apakah kita harus naik level
        if (score / LEVEL_UP_SCORE >= level) {
            level++;
            updateTimerDelay();
        }
    }


    public int getScore() {
        return this.score;
    }

    public void actionPerformed(ActionEvent e) {
        this.dropPiece();
    }
}
