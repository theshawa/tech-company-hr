import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDepartmentForm extends AppDialog {

    private JTextField nameField;
    private JButton addDepartmentButton;
    private JPanel mainPanel;

    public AddDepartmentForm(JFrame owner, DefaultComboBoxModel<Department> listModel) {
        super(owner);
        this.setTitle("Add New Department");
        setContentPane(mainPanel);
        addDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().isBlank()) {
                    App.showErrorMessage(AddDepartmentForm.this, "Name is required!");
                    return;
                }
                try {
                    App.addDepartment(nameField.getText());
                } catch (Exception ex) {
                    App.showErrorMessage(AddDepartmentForm.this, "Unable to add department due to a system error!");
                }
                App.showSuccessMessage(AddDepartmentForm.this, "Department Added!");
                listModel.removeAllElements();
                listModel.addElement(null);
                listModel.addAll(App.getDepartments());
                dispose();
            }
        });
    }
}
