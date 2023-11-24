import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAssistantForm extends AppDialog {
    private JTextField unameField;
    private JTextField pwField;
    private JButton addAssistantButton;
    private JPanel mainPanel;

    public AddAssistantForm(Frame owner) {
        super(owner);
        setContentPane(mainPanel);
        setTitle("Add New Assistant");
        addAssistantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (unameField.getText().isBlank() || pwField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(mainPanel, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    App.userService.addItem(new User(UserRole.Assistant, unameField.getText(), pwField.getText()));
                    JOptionPane.showMessageDialog(mainPanel, "Assistant Added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanel, ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
