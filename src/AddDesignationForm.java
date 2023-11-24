import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDesignationForm extends AppDialog {
    private JPanel mainPanel;
    private JTextField titleField;
    private JButton addDesignationButton;

    public AddDesignationForm(Frame owner) {
        super(owner);
        setTitle("Add New Designation");
        setContentPane(mainPanel);
        addDesignationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titleField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(mainPanel, "Title is required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    App.designationService.addItem(new Designation(titleField.getText()));
                    JOptionPane.showMessageDialog(mainPanel, "Designation Added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanel, ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
