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
        departmentDefaultComboBoxModel.addAll(App.departmentService.getItems());

        DefaultComboBoxModel<Employee> employeeDefaultComboBoxModel = new DefaultComboBoxModel<>();
        employeeComboBox.setModel(employeeDefaultComboBoxModel);
        employeeDefaultComboBoxModel.addAll(App.employeeService.getItems());

        assignToDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (departmentComboBox.getSelectedItem() == null || employeeComboBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(mainPanel, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Employee currentEmployee = (Employee) employeeComboBox.getSelectedItem();
                    currentEmployee.departmentId = ((Department) departmentComboBox.getSelectedItem()).id;
                    App.employeeService.updateItem(currentEmployee);
                    JOptionPane.showMessageDialog(mainPanel, "Assigned to the Department!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
