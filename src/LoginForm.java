import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends AppFrame {
    private JButton loginButton;
    public JPanel loginPane;
    private JTextField unameField;
    private JTextField pwField;
    private JButton exitButton;

    public LoginForm() {
        this.setContentPane(loginPane);
        this.setTitle("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (App.login(unameField.getText(), pwField.getText())) {
                        JOptionPane.showMessageDialog(loginPane, "Welcome again " + (App.currentUser.role.equals(UserRole.Assistant) ? "assistant" : "manager") + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        // TODO: Open Dashboard
//                        ManagerDashboard managerDashboard = new ManagerDashboard();
//                        managerDashboard.showMe();
                        if (App.currentUser.role.equals(UserRole.Assistant)) {
                            (new AssistantDashboardForm()).open();
                        } else {
                            (new ManagerDashboardForm()).open();
                        }
                    } else {
                        JOptionPane.showMessageDialog(loginPane, "Invalid Credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(loginPane, ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
