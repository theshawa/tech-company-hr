import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Utils.initDBFiles();
        App.departmentService = new DepartmentService();
        App.designationService = new DesignationService();
        App.employeeService = new EmployeeService();
        SwingUtilities.invokeLater(() -> {
            (new LoginForm()).open();
        });
    }
}
