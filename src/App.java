public class App {
    public static User currentUser;
    public static DepartmentService departmentService;
    public static DesignationService designationService;
    public static EmployeeService employeeService;

    public static UserService userService;

    static {
        try {
            userService = new UserService();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean login(String username, String password) throws Exception {
        User currentUser = userService.getByUserName(username);
        if (currentUser != null && currentUser.password.equals(password)) {
            App.currentUser = currentUser;
            return true;
        }
        return false;
    }

    public static void logout() {
        currentUser = null;
        (new LoginForm()).open();
    }
}
