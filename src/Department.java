public class Department {
    public String id;
    public String name;

    public Department(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
