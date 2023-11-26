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
                    App.showErrorMessage(AddAssistantForm.this, "All fields are required!");
                    return;
                }
                try {
                    if (App.addAssistant(unameField.getText(), pwField.getText())) {
                        App.showSuccessMessage(AddAssistantForm.this, "Assistant Added!");
                        dispose();
                    }
                } catch (Exception ex) {
                    App.showErrorMessage(AddAssistantForm.this, "Unable to add assistant due to a system error!");
                }
            }
        });
    }
}
