import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddEmployeeForm extends AppDialog {
    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField epfField;
    private JComboBox<Department> departmentComboBox;
    private JButton addEmployeeButton;


    public AddEmployeeForm(JFrame owner, DefaultTableModel listModel) {
        super(owner);
        setContentPane(mainPanel);
        setTitle("Add New Employee");

        DefaultComboBoxModel<Department> departmentComboBoxModel = new DefaultComboBoxModel<>();
        departmentComboBox.setModel(departmentComboBoxModel);
        departmentComboBoxModel.addAll(App.getDepartments());
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (departmentComboBox.getSelectedItem() == null || nameField.getText().isBlank() || epfField.getText().isBlank()) {
                    App.showErrorMessage(AddEmployeeForm.this, "All fields are required!");
                    return;
                }
                App.addEmployee(nameField.getText(), ((Department) Objects.requireNonNull(departmentComboBox.getSelectedItem())).id, epfField.getText());
                App.showSuccessMessage(AddEmployeeForm.this, "Employee added!");
                listModel.setRowCount(0);
                for (Employee em : App.getEmployees()) {
                    listModel.addRow(em.tableRow());
                }
                dispose();
            }
        });
    }
}
