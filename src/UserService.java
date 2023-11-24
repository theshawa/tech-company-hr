public class UserService extends Service<User> {
    public UserService() throws Exception {
        super(User.class);
    }

    public User getByUserName(String username) throws Exception {
        for (User u : this.getItems()) {
            if (u.username.equals(username)) return u;
        }
        return null;
    }
}
