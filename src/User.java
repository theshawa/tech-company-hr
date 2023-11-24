public class User extends Model {
    UserRole role;
    String username;
    String password;

    public User(UserRole role, String username, String password) {
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public static User fromString(String v) {
        User u = new User();
        String[] parts = v.split("\n");
        if (parts.length < 5) return null;
        u.id = parts[0];
        u.username = parts[1];
        u.password = parts[2];
        u.role = UserRole.values()[Integer.parseInt(parts[3])];
        u.updatedAt = Long.parseLong(parts[4]);
        return u;
    }

    @Override
    public String toDBtring() {
        return this.id + "\n" + this.username + "\n" + this.password + "\n" + this.role.ordinal() + "\n" + this.updatedAt + "\n";
    }
}
