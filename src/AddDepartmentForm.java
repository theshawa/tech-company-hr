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
                    JOptionPane.showMessageDialog(mainPanel, "Name is required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    App.departmentService.addItem(new Department(nameField.getText()));
                    JOptionPane.showMessageDialog(mainPanel, "Department Added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    listModel.removeAllElements();
                    listModel.addElement(null);
                    listModel.addAll(App.departmentService.getItems());
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanel, ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
