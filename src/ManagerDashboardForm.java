import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerDashboardForm extends AppFrame {

    private JPanel dashboardPane;
    private JButton departmentButton;
    private JButton employeeButton;
    private JButton designationButton;
    private JButton assignDesignationButton;
    private JButton logoutButton;
    private JButton assistantButton;
    private JComboBox<Department> departmentComboBox;
    private JTextField epfField;
    private JButton searchButton;
    private JTextField nameField;
    private JTextField designationField;
    private JButton resetButton;
    private JTable employeeTable;
    private JButton assignToDepartmentButton;

    private final DefaultTableModel employeeTableModel;

    public ManagerDashboardForm() {
        this.setTitle("HR Manager Dashboard");
        this.setContentPane(dashboardPane);

        DefaultComboBoxModel<Department> departmentComboBoxModel = new DefaultComboBoxModel<>();
        departmentComboBox.setModel(departmentComboBoxModel);
        departmentComboBoxModel.addElement(null);
        departmentComboBoxModel.addAll(App.departmentService.getItems());

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
        departmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddDepartmentForm(ManagerDashboardForm.this, departmentComboBoxModel).open();
            }
        });
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployeeForm(ManagerDashboardForm.this, employeeTableModel).open();
            }
        });
        designationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddDesignationForm(ManagerDashboardForm.this).open();
            }
        });
        assistantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddAssistantForm(ManagerDashboardForm.this).open();
            }
        });
        assignDesignationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssignDesignationForm(ManagerDashboardForm.this, employeeTableModel).open();
            }
        });
        assignToDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssignToDepartmentForm(ManagerDashboardForm.this, employeeTableModel).open();
            }
        });
    }

    private void updateEmployeeTable() {
        Department departmentQ = (Department) departmentComboBox.getSelectedItem();
        String nameQ = nameField.getText().trim().toLowerCase();
        String designationQ = designationField.getText().trim().toLowerCase();
        String epfNoQ = epfField.getText().trim().toLowerCase();

        List<Employee> showingEmployees = App.employeeService.getItems();
        if (departmentQ != null) {
            showingEmployees = showingEmployees.stream().filter(e -> e.departmentId != null && e.departmentId.equals(departmentQ.id)).collect(Collectors.toList());
        }
        if (!nameQ.isBlank()) {
            showingEmployees = showingEmployees.stream().filter(e -> e.name.toLowerCase().startsWith(nameQ) || e.name.toLowerCase().contains(nameQ)).collect(Collectors.toList());
        }
        if (!designationQ.isBlank()) {
            showingEmployees = showingEmployees.stream().filter(e -> {
                Designation d = App.designationService.getById(e.designationId);
                return d != null && (d.title.toLowerCase().startsWith(designationQ) || d.title.toLowerCase().contains(designationQ));
            }).collect(Collectors.toList());
        }
        if (!epfNoQ.isBlank()) {
            showingEmployees = showingEmployees.stream().filter(e -> e.epfNo.toLowerCase().startsWith(epfNoQ) || e.epfNo.toLowerCase().contains(epfNoQ)).collect(Collectors.toList());
        }
        employeeTableModel.setRowCount(0);
        for (Employee e : showingEmployees) {
            employeeTableModel.addRow(e.tableRow());
        }
    }
}
