public class User {
    boolean isManager;
    String username;
    String password;

    public User(String username, String password, boolean isManager) {
        this.isManager = isManager;
        this.username = username;
        this.password = password;
    }
}
