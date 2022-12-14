import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class chessWindow extends JFrame {
    private final Color WHITE = new Color(255, 255, 255);
    private final Color BLACK = new Color(0, 0, 0);
    private final Color BOARD_LIGHT = new Color(212, 176, 119);
    private final Color BOARD_DARK = new Color(150, 124, 81);

    public chessWindow() {
        super("Eden's Chess Application");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                processWindowEvent(e);
            }
        });
        setBackground(WHITE);
        setSize(640, 640);
        setResizable(false);
        setVisible(true);
    }

    private void drawBoard(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(BLACK);
        g2d.fillRoundRect(48, 48, 544, 544, 16, 16);
        Rectangle[][] board = new Rectangle[8][8];
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                int x = (file + 1) * 64;
                int y = (rank + 1) * 64;
                g2d.setColor(((rank + file) % 2 == 0 ? BOARD_LIGHT : BOARD_DARK));
                board[rank][file] = new Rectangle(x, y, 64, 64);
                g2d.fillRect(x, y, 64, 64);
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawBoard(g);
    }

    public void processWindowEvent(WindowEvent event) {
        if (event.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }
}