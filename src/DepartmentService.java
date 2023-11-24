import java.io.IOException;

public class DepartmentService extends Service<Department> {
    public DepartmentService() throws IOException {
        super(Department.class);
    }
}
