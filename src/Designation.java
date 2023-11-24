public class Designation extends Model {
    public String title;

    public Designation(String title) {
        super();
        this.title = title;
    }

    public Designation() {
        super();
    }

    public static Designation fromString(String v) {
        Designation d = new Designation();
        String[] parts = v.split("\n");
        if (parts.length < 3) return null;
        d.id = parts[0];
        d.title = parts[1];
        d.updatedAt = Long.parseLong(parts[2]);
        return d;
    }

    @Override
    public String toDBtring() {
        return this.id + "\n" + this.title + "\n" + this.updatedAt + "\n";
    }

    @Override
    public String toString() {
        return this.title;
    }
}
