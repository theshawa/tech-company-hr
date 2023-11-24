public class Department extends Model {
    public String name;

    public Department(String name) {
        super();
        this.name = name;
    }

    public Department() {
        super();
    }

    public static Department fromString(String v) {
        Department d = new Department();
        String[] parts = v.split("\n");
        if (parts.length < 3) return null;
        d.id = parts[0];
        d.name = parts[1];
        d.updatedAt = Long.parseLong(parts[2]);
        return d;
    }

    @Override
    public String toDBtring() {
        return this.id + "\n" + this.name + "\n" + this.updatedAt + "\n";
    }

    @Override
    public String toString() {
        return this.name;
    }
}
