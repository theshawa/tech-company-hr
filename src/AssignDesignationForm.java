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
        designationComboBoxModel.addAll(App.getDesignations());

        DefaultComboBoxModel<Employee> employeeDefaultComboBoxModel = new DefaultComboBoxModel<>();
        employeeComboBox.setModel(employeeDefaultComboBoxModel);
        employeeDefaultComboBoxModel.addAll(App.getEmployees());

        assignDesignationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (designationComboBox.getSelectedItem() == null || employeeComboBox.getSelectedItem() == null) {
                    App.showErrorMessage(AssignDesignationForm.this, "All fields are required!");
                    return;
                }
                Employee currentEmployee = (Employee) employeeComboBox.getSelectedItem();
                String designationId = ((Designation) designationComboBox.getSelectedItem()).id;
                try {
                    App.assignDesignation(designationId, currentEmployee.id);
                } catch (Exception ex) {
                    App.showErrorMessage(AssignDesignationForm.this, "Unable to assign designation due to a system error!");
                }
                App.showSuccessMessage(AssignDesignationForm.this, "Designation assigned!");
                listModel.setRowCount(0);
                for (Employee em : App.getEmployees()) {
                    listModel.addRow(em.tableRow());
                }
                dispose();
            }
        });
    }
}
