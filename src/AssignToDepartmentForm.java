import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssignToDepartmentForm extends AppDialog {
    private JButton assignToDepartmentButton;
    private JComboBox<Employee> employeeComboBox;
    private JComboBox<Department> departmentComboBox;
    private JPanel mainPanel;

    public AssignToDepartmentForm(JFrame owner, DefaultTableModel listModel) {
        super(owner);
        setContentPane(mainPanel);
        setTitle("Assign to Department");

        DefaultComboBoxModel<Department> departmentDefaultComboBoxModel = new DefaultComboBoxModel<>();
        departmentComboBox.setModel(departmentDefaultComboBoxModel);
        departmentDefaultComboBoxModel.addAll(App.getDepartments());

        DefaultComboBoxModel<Employee> employeeDefaultComboBoxModel = new DefaultComboBoxModel<>();
        employeeComboBox.setModel(employeeDefaultComboBoxModel);
        employeeDefaultComboBoxModel.addAll(App.getEmployees());

        assignToDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (departmentComboBox.getSelectedItem() == null || employeeComboBox.getSelectedItem() == null) {
                    App.showErrorMessage(AssignToDepartmentForm.this, "All fields are required!");
                    return;
                }
                Employee currentEmployee = (Employee) employeeComboBox.getSelectedItem();
                App.changeDepartment(currentEmployee.id, ((Department) departmentComboBox.getSelectedItem()).id);
                App.showSuccessMessage(AssignToDepartmentForm.this, "Assigned to the department!");
                listModel.setRowCount(0);
                for (Employee em : App.getEmployees()) {
                    listModel.addRow(em.tableRow());
                }
                dispose();
            }
        });
    }
}
