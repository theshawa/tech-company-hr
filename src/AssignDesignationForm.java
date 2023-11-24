import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssignDesignationForm extends AppDialog {
    private JPanel mainPanel;
    private JComboBox<Designation> designationComboBox;
    private JComboBox<Employee> employeeComboBox;
    private JButton assignDesignationButton;

    public AssignDesignationForm(Frame owner, DefaultTableModel listModel) {
        super(owner);
        setContentPane(mainPanel);
        setTitle("Assign Designation");

        DefaultComboBoxModel<Designation> designationComboBoxModel = new DefaultComboBoxModel<>();
        designationComboBox.setModel(designationComboBoxModel);
        designationComboBoxModel.addAll(App.designationService.getItems());

        DefaultComboBoxModel<Employee> employeeDefaultComboBoxModel = new DefaultComboBoxModel<>();
        employeeComboBox.setModel(employeeDefaultComboBoxModel);
        employeeDefaultComboBoxModel.addAll(App.employeeService.getItems());

        assignDesignationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (designationComboBox.getSelectedItem() == null || employeeComboBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(mainPanel, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Employee currentEmployee = (Employee) employeeComboBox.getSelectedItem();
                    String designationId = ((Designation) designationComboBox.getSelectedItem()).id;
                    for (Employee employee : App.employeeService.getItems().stream().filter(em -> em.designationId != null && em.designationId.equals(designationId)).toList()) {
                        employee.designationId = null;
                        App.employeeService.updateItem(employee);
                    }
                    currentEmployee.designationId = designationId;
                    App.employeeService.updateItem(currentEmployee);
                    JOptionPane.showMessageDialog(mainPanel, "Designation Assigned!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
