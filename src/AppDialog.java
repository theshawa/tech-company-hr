import javax.swing.*;
import java.awt.*;

public abstract class AppDialog extends JDialog {
    public AppDialog(Frame owner) {
        super(owner);
        this.pack();
        this.setSize(700, 500);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public void open() {
        this.setVisible(true);
    }
}
