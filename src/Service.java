import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Service<T extends Model> {
    private final Path filePath;
    private List<T> items;

    public Service(Class<T> cls) throws IOException {
        this.items = new ArrayList<>();
        // init data file
        String fileName = cls.getName().toLowerCase() + ".data.txt";
        this.filePath = Path.of(fileName);
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("Database file created: " + file.getName());
            } else {
                System.out.println("Database file already exists: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("Unable to create database file due to an error: " + e.getMessage());
        }

        // load data
        String content = Files.readString(filePath).trim();
        if (!content.isBlank()) {
            for (String ci : content.split("\n\n")) {
                T item = null;
                if (cls.equals(Department.class)) {
                    item = (T) Department.fromString(ci);
                } else if (cls.equals(Employee.class)) {
                    item = (T) Employee.fromString(ci);
                } else if (cls.equals(Designation.class)) {
                    item = (T) Designation.fromString(ci);
                } else if (cls.equals(User.class)) {
                    item = (T) User.fromString(ci);
                }
                items.add(item);
            }
        }
    }

    public void addItem(T v) throws Exception {
        items.add(v);
        v.updatedAt = System.currentTimeMillis();
        save();
    }

    public void updateItem(T v) throws Exception {
        v.updatedAt = System.currentTimeMillis();
        removeItem(v.id);
        addItem(v);
        save();
    }

    public void removeItem(String id) throws Exception {
        items = items.stream().filter(i -> !i.id.equals(id)).collect(Collectors.toList());
        save();
    }

    public List<T> getItems() {
        return items;
    }

    public void save() throws Exception {
        String content = "";
        Files.writeString(filePath, "");
        items.sort(Comparator.comparingLong(o -> o.updatedAt));
        for (T i : items) {
            content = content.concat(i.toDBtring() + "\n");
        }
        Files.writeString(filePath, content);
    }

    public T getById(String id) {
        for (T d : this.getItems()) {
            if (d.id.equals(id)) return d;
        }
        return null;
    }
}

