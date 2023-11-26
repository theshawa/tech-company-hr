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
                    App.showErrorMessage(AddDesignationForm.this, "Title is required!");
                    return;
                }
                try {
                    App.addDesignation(titleField.getText());
                } catch (Exception ex) {
                    App.showErrorMessage(AddDesignationForm.this, "Unable to add designation due to a system error!");
                }
                App.showSuccessMessage(AddDesignationForm.this, "Designation added!");
                dispose();
            }
        });
    }
}
