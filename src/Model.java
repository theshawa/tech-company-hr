import java.util.UUID;

public class Model {
    public String id;
    public long updatedAt;

    public Model() {
        this.id = UUID.randomUUID().toString();
        this.updatedAt = System.currentTimeMillis();
    }

    public Model(String id) {
        this.id = id;
        this.updatedAt = System.currentTimeMillis();
    }

    public String toDBtring() {
        return null;
    }
}
