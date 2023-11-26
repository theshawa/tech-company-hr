public class Employee {
    public static final String[] tableHeader = {"Name", "EPF No", "Department", "Designation"};
    public String id;
    public String name;
    public String epfNo;
    // can be null
    public String departmentId;

    public Employee(String id, String name, String departmentId, String epfNo) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.epfNo = epfNo;
    }

    public String[] tableRow() {
        Department d = App.getDepartmentById(this.departmentId);
        Designation ds = App.getDesignationByEmployeeId(this.id);
        String[] data = {this.name, this.epfNo, d != null ? d.name : "", ds != null ? ds.title : ""};
        return data;
    }

    @Override
    public String toString() {
        return this.name + "(" + this.epfNo + ")";
    }
}
