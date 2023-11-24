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
        departmentComboBoxModel.addAll(App.departmentService.getItems());
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (departmentComboBox.getSelectedItem() == null || nameField.getText().isBlank() || epfField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(mainPanel, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Employee newEmployee = new Employee(nameField.getText(), epfField.getText(), ((Department) Objects.requireNonNull(departmentComboBox.getSelectedItem())).id);
                    App.employeeService.addItem(newEmployee);
                    JOptionPane.showMessageDialog(mainPanel, "Employee Added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    listModel.setRowCount(0);
                    for (Employee em : App.employeeService.getItems()) {
                        listModel.addRow(em.tableRow());
                    }
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanel, ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
