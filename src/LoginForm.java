import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends AppFrame {
    public JPanel loginPane;
    private JButton loginButton;
    private JTextField unameField;
    private JTextField pwField;
    private JButton exitButton;

    public LoginForm() {
        this.setContentPane(loginPane);
        this.setTitle("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (App.login(unameField.getText(), pwField.getText())) {
                    if (!App.currentUser.isManager) {
                        new AssistantDashboardForm().open();
                        App.showSuccessMessage(LoginForm.this, "Welcome assistant!");
                    } else {
                        new ManagerDashboardForm().open();
                        App.showSuccessMessage(LoginForm.this, "Welcome manager!");
                    }
                    dispose();
                } else {
                    App.showErrorMessage(LoginForm.this, "Invalid credentials!");
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
