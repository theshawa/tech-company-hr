import java.io.IOException;

public class EmployeeService extends Service<Employee> {
    public EmployeeService() throws IOException {
        super(Employee.class);
    }
}
