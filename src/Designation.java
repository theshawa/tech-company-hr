public class Designation {
    public String id;
    public String title;
    public String employeeId;

    public Designation(String id, String title, String employeeId) {
        this.id = id;
        this.title = title;
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
