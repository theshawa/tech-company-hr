public class Employee extends Model {
    public static final String[] tableHeader = {"Name", "EPF No", "Department", "Designation"};
    public String name;
    public String epfNo;
    // can be null
    public String departmentId;
    // can be null
    public String designationId;

    public Employee(String name, String epfNo, String departmentId) {
        super();
        this.name = name;
        this.departmentId = departmentId;
        this.epfNo = epfNo;
    }

    public Employee() {
        super();
    }

    public static Employee fromString(String v) {
        String[] parts = v.split("\n");
        if (parts.length < 6) return null;
        Employee e = new Employee();
        e.id = parts[0];
        e.name = parts[1];
        e.epfNo = parts[2];
        e.departmentId = parts[3].equals("0") ? null : parts[3];
        e.designationId = parts[4].equals("0") ? null : parts[4];
        e.updatedAt = Long.parseLong(parts[5]);
        return e;
    }

    @Override
    public String toDBtring() {
        return this.id + "\n" + this.name + "\n" + this.epfNo + "\n" + ((this.departmentId != null) ? this.departmentId : "0") + "\n" + ((this.designationId != null) ? this.designationId : "0") + "\n" + this.updatedAt + "\n";
    }

    public String[] tableRow() {
        Department d = App.departmentService.getById(this.departmentId);
        Designation ds = App.designationService.getById(this.designationId);
        String[] data = {this.name, this.epfNo, d != null ? d.name : "", ds != null ? ds.title : ""};
        return data;
    }

    @Override
    public String toString() {
        return this.name + "(" + this.epfNo + ")";
    }
}
