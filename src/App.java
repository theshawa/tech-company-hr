import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class App {
    private static final List<Department> departments = new ArrayList<>(List.of(new Department[]{
            new Department("1", "Development")
    }));
    private static final List<User> users = new ArrayList<User>(List.of(new User[]{
                    new User("john", "1234", true),
                    new User("jessica", "1234", false),
            }
    )
    );
    public static User currentUser;
    private static List<Employee> employees = new ArrayList<>(List.of(new Employee[]{
            new Employee("1", "Karel Sigarel", "1", "1234")
    }));
    private static List<Designation> designations = new ArrayList<>(List.of(new Designation[]{
            new Designation("1", "Lead Developer", "1"),
    }));

    public static List<Employee> getEmployees() {
        return employees;
    }

    public static List<Department> getDepartments() {
        return departments;
    }

    public static List<Designation> getDesignations() {
        return designations;
    }

    // utils
    public static void showErrorMessage(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Error!", JOptionPane.ERROR_MESSAGE);
    }

    public static void showSuccessMessage(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Success!", JOptionPane.INFORMATION_MESSAGE);
    }

    // users
    public static boolean login(String username, String password) {
        User user = getUserWithUsername(username);
        if (user != null && user.password.equals(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public static void logout() {
        currentUser = null;
        new LoginForm().open();
    }

    private static User getUserWithUsername(String username) {
        return users.stream().filter(u -> u.username.equals(username)).findFirst().orElse(null);
    }

    public static boolean addAssistant(String username, String password) {
        if (getUserWithUsername(username) == null) {
            users.add(new User(username, password, false));
            return true;
        }
        return false;
    }

    // departments
    public static void addDepartment(String name) {
        departments.add(new Department(UUID.randomUUID().toString(), name));
    }

    public static Department getDepartmentById(String id) {
        return departments.stream().filter(d -> d.id.equals(id)).findFirst().orElse(null);
    }

    // designation
    public static void addDesignation(String title) {
        designations.add(new Designation(UUID.randomUUID().toString(), title, ""));
    }

    public static Designation getDesignationByEmployeeId(String eid) {
        return designations.stream().filter(d -> d.employeeId.equals(eid)).findFirst().orElse(null);
    }

    public static void assignDesignation(String id, String eid) {
        designations = designations.stream().peek(d -> {
            if (d.employeeId.equals(eid) && !d.id.equals(id)) {
                d.employeeId = "";
            } else if (d.id.equals(id)) {
                d.employeeId = eid;
            }
        }).toList();
    }

    // employees
    public static void addEmployee(String name, String departmentId, String epfNo) {
        employees.add(new Employee(UUID.randomUUID().toString(), name, departmentId, epfNo));
    }

    public static void changeDepartment(String eid, String departmentId) {
        employees = employees.stream().map(e -> {
            if (e.id.equals(eid)) {
                return new Employee(eid, e.name, departmentId, e.epfNo);
            }
            return e;
        }).toList();
    }

    public static List<Employee> filterEmployees(String departmentId, String designationQ, String nameQ, String epfNoQ) {
        List<Employee> out = employees;
        if (!departmentId.isBlank()) {
            out = out.stream().filter(e -> e.departmentId.equals(departmentId)).toList();
        }
        if (!designationQ.isBlank()) {
            Designation designation = designations.stream().filter(d -> d.title.toLowerCase().startsWith(designationQ) || d.title.toLowerCase().contains(designationQ)).findFirst().orElse(null);
            if (designation != null) {
                out = out.stream().filter(e -> e.id.equals(designation.employeeId)).toList();
            }
        }
        if (!nameQ.isBlank()) {
            out = out.stream().filter(e -> e.name.toLowerCase().startsWith(nameQ) || e.name.toLowerCase().contains(nameQ)).toList();
        }
        if (!epfNoQ.isBlank()) {
            out = out.stream().filter(e -> e.epfNo.toLowerCase().startsWith(epfNoQ) || e.epfNo.toLowerCase().contains(epfNoQ)).toList();
        }
        return out;
    }
}
