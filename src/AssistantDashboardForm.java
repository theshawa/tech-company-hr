import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AssistantDashboardForm extends AppFrame {
    private final DefaultTableModel employeeTableModel;
    private JPanel mainPane;
    private JComboBox<Department> departmentComboBox;
    private JTextField epfField;
    private JButton searchButton;
    private JTextField nameField;
    private JTextField designationField;
    private JTable employeeTable;
    private JButton resetButton;
    private JButton logoutButton;

    public AssistantDashboardForm() {
        this.setTitle("HR Assistant Dashboard");
        this.setContentPane(mainPane);

        DefaultComboBoxModel<Department> departmentComboBoxModel = new DefaultComboBoxModel<>();
        departmentComboBox.setModel(departmentComboBoxModel);
        departmentComboBoxModel.addElement(null);
        departmentComboBoxModel.addAll(App.getDepartments());

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployeeTable();
            }
        });

        employeeTableModel = new DefaultTableModel(Employee.tableHeader, 0);
        employeeTable.setModel(employeeTableModel);
        updateEmployeeTable();
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                departmentComboBox.setSelectedIndex(0);
                nameField.setText("");
                designationField.setText("");
                epfField.setText("");
                updateEmployeeTable();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                App.logout();
            }
        });
    }

    private void updateEmployeeTable() {
        Department departmentQ = (Department) departmentComboBox.getSelectedItem();
        String nameQ = nameField.getText().trim().toLowerCase();
        String designationQ = designationField.getText().trim().toLowerCase();
        String epfNoQ = epfField.getText().trim().toLowerCase();

        List<Employee> showingEmployees = App.filterEmployees(departmentQ != null ? departmentQ.id : "", designationQ, nameQ, epfNoQ);
        employeeTableModel.setRowCount(0);
        for (Employee e : showingEmployees) {
            employeeTableModel.addRow(e.tableRow());
        }
    }
}
