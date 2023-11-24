import javax.swing.*;
import java.awt.*;

public abstract class AppFrame extends JFrame {
    public AppFrame() {
        this.pack();
        this.setSize(1366, 768);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void open() {
        this.setVisible(true);
    }
}
