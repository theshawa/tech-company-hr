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
                App.addDepartment(nameField.getText());
                App.showSuccessMessage(AddDepartmentForm.this, "Department Added!");
                listModel.removeAllElements();
                listModel.addElement(null);
                listModel.addAll(App.getDepartments());
                dispose();
            }
        });
    }
}
