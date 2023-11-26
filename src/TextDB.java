import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextDB {
    private static final Path employeePath = Path.of("employee.data.txt");
    private static final Path designationPath = Path.of("designation.data.txt");
    private static final Path departmentPath = Path.of("department.data.txt");
    private static final Path userPath = Path.of("user.data.txt");

    public static void init() throws Exception {
        new File(userPath.toUri()).createNewFile();
        new File(designationPath.toUri()).createNewFile();
        new File(departmentPath.toUri()).createNewFile();
        new File(employeePath.toUri()).createNewFile();
    }

    public static void saveEmployees() throws Exception {
        String content = "";
        for (Employee e : App.getEmployees()) {
            content = content.concat(String.format("%s\n%s\n%s\n%s\n\n", e.id, e.name, e.departmentId, e.epfNo));
        }
        Files.writeString(employeePath, content);
    }

    public static void saveDepartments() throws Exception {
        String content = "";
        for (Department d : App.getDepartments()) {
            content = content.concat(String.format("%s\n%s\n\n", d.id, d.name));
        }
        Files.writeString(departmentPath, content);
    }

    public static void saveDesignations() throws Exception {
        String content = "";
        for (Designation d : App.getDesignations()) {
            content = content.concat(String.format("%s\n%s\n%s\n\n", d.id, d.title, d.employeeId));
        }
        Files.writeString(designationPath, content);
    }

    public static void saveUsers() throws Exception {
        String content = "";
        for (User u : App.getUsers()) {
            content = content.concat(String.format("%s\n%s\n%b\n\n", u.username, u.password, u.isManager));
        }
        Files.writeString(userPath, content);
    }

    public static List<Employee> readEmployees() throws Exception {
        List<Employee> items = new ArrayList<>();
        String content = Files.readString(employeePath);
        for (String object : content.split("\n\n")) {
            String[] lines = object.split("\n");
            if (lines.length > 3) {
                items.add(new Employee(lines[0], lines[1], lines[2].equals("0") ? "" : lines[2], lines[3]));
            }
        }
        return items;
    }

    public static List<Department> readDepartments() throws Exception {
        List<Department> items = new ArrayList<>();
        String content = Files.readString(departmentPath);
        for (String object : content.split("\n\n")) {
            String[] lines = object.split("\n");
            if (lines.length > 1) {
                items.add(new Department(lines[0], lines[1]));
            }
        }
        return items;
    }

    public static List<Designation> readDesignations() throws Exception {
        List<Designation> items = new ArrayList<>();
        String content = Files.readString(designationPath);
        for (String object : content.split("\n\n")) {
            String[] lines = object.split("\n");
            if (lines.length > 2) {
                items.add(new Designation(lines[0], lines[1], lines[2].equals("0") ? "" : lines[2]));
            }
        }
        return items;
    }

    public static List<User> readUsers() throws Exception {
        List<User> items = new ArrayList<>();
        String content = Files.readString(userPath);
        for (String object : content.split("\n\n")) {
            String[] lines = object.split("\n");
            if (lines.length > 2) {
                items.add(new User(lines[0], lines[1], Boolean.parseBoolean(lines[2])));
            }
        }
        return items;
    }
}
