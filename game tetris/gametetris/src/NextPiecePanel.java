//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class NextPiecePanel extends JPanel {
    private Tetromino nextPiece;
    private Image backgroundImage;

    public NextPiecePanel() {
        this.setPreferredSize(new Dimension(120, 150));
        this.setBorder(new LineBorder(Color.WHITE, 2));
        this.setLayout(new BorderLayout());
        JLabel nextLabel = new JLabel("NEXT", 0);
        nextLabel.setForeground(Color.BLACK);
        nextLabel.setFont(new Font("Arial", 1, 16));
        this.add(nextLabel, "North");
        this.backgroundImage = (new ImageIcon("path/to/your/background/image.png")).getImage();
    }

    public void setNextPiece(Tetromino nextPiece) {
        this.nextPiece = nextPiece;
        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        if (this.nextPiece != null) {
            g.setColor(this.nextPiece.getColor());

            for(int i = 0; i < this.nextPiece.getShape().length; ++i) {
                for(int j = 0; j < this.nextPiece.getShape()[i].length; ++j) {
                    if (this.nextPiece.getShape()[i][j] == 1) {
                        g.fillRect(j * 30, (i + 1) * 30, 30, 30);
                        g.setColor(Color.BLACK);
                        g.drawRect(j * 30, (i + 1) * 30, 30, 30);
                        g.setColor(this.nextPiece.getColor());
                    }
                }
            }
        }

    }
}
