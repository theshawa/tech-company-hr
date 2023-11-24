import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Utils {
    public static void initDBFiles() {
        if (createFile("user.data.txt")) {
            writeContent("user.data.txt", "1\n" +
                    "john\n" +
                    "1234\n" +
                    "0\n" +
                    "1700749146387\n");
        }

//        if (createFile("department.data.txt")) {
//            writeContent("department.data.txt", "1\n" +
//                    "Finance\n" +
//                    "1700749146387\n" +
//                    "\n" +
//                    "2\n" +
//                    "Marketing\n" +
//                    "1700749146387\n" +
//                    "\n" +
//                    "3\n" +
//                    "Development\n" +
//                    "1700749146387\n");
//        }

//        if (createFile("employee.data.txt")) {
//            writeContent("employee.data.txt", "1\n" +
//                    "John Doe\n" +
//                    "5678\n" +
//                    "1\n" +
//                    "20\n" +
//                    "1723415946387\n" +
//                    "\n" +
//                    "2\n" +
//                    "Jane Smith\n" +
//                    "9876\n" +
//                    "2\n" +
//                    "19\n" +
//                    "1745128746387\n" +
//                    "\n" +
//                    "3\n" +
//                    "Michael Johnson\n" +
//                    "3456\n" +
//                    "3\n" +
//                    "18\n" +
//                    "1767831546387\n" +
//                    "\n" +
//                    "4\n" +
//                    "Emily Williams\n" +
//                    "6543\n" +
//                    "1\n" +
//                    "17\n" +
//                    "1790534346387\n" +
//                    "\n" +
//                    "5\n" +
//                    "Robert Brown\n" +
//                    "8765\n" +
//                    "2\n" +
//                    "16\n" +
//                    "1813237146387\n" +
//                    "\n" +
//                    "6\n" +
//                    "Amanda Davis\n" +
//                    "2345\n" +
//                    "3\n" +
//                    "15\n" +
//                    "1835939946387\n" +
//                    "\n" +
//                    "7\n" +
//                    "Christopher Lee\n" +
//                    "7890\n" +
//                    "1\n" +
//                    "14\n" +
//                    "1858642746387\n" +
//                    "\n" +
//                    "8\n" +
//                    "Sophia Miller\n" +
//                    "5432\n" +
//                    "2\n" +
//                    "13\n" +
//                    "1881345546387\n" +
//                    "\n" +
//                    "9\n" +
//                    "William Taylor\n" +
//                    "4321\n" +
//                    "3\n" +
//                    "12\n" +
//                    "1904048346387\n" +
//                    "\n" +
//                    "10\n" +
//                    "Olivia Martinez\n" +
//                    "6547\n" +
//                    "1\n" +
//                    "11\n" +
//                    "1926751146387\n" +
//                    "\n" +
//                    "11\n" +
//                    "Daniel Anderson\n" +
//                    "9870\n" +
//                    "2\n" +
//                    "10\n" +
//                    "1949453946387\n" +
//                    "\n" +
//                    "12\n" +
//                    "Isabella Garcia\n" +
//                    "1234\n" +
//                    "3\n" +
//                    "9\n" +
//                    "1972156746387\n" +
//                    "\n" +
//                    "13\n" +
//                    "Matthew Wilson\n" +
//                    "5678\n" +
//                    "1\n" +
//                    "8\n" +
//                    "1994859546387\n" +
//                    "\n" +
//                    "14\n" +
//                    "Emma Jackson\n" +
//                    "8765\n" +
//                    "2\n" +
//                    "7\n" +
//                    "2017562346387\n" +
//                    "\n" +
//                    "15\n" +
//                    "David White\n" +
//                    "3456\n" +
//                    "3\n" +
//                    "6\n" +
//                    "2040265146387\n" +
//                    "\n" +
//                    "16\n" +
//                    "Ava Thompson\n" +
//                    "7890\n" +
//                    "1\n" +
//                    "5\n" +
//                    "2062967946387\n" +
//                    "\n" +
//                    "17\n" +
//                    "Joseph Harris\n" +
//                    "6543\n" +
//                    "2\n" +
//                    "4\n" +
//                    "2085670746387\n" +
//                    "\n" +
//                    "18\n" +
//                    "Mia Lopez\n" +
//                    "4321\n" +
//                    "3\n" +
//                    "3\n" +
//                    "2108373546387\n" +
//                    "\n" +
//                    "19\n" +
//                    "James Hall\n" +
//                    "9876\n" +
//                    "1\n" +
//                    "2\n" +
//                    "2131076346387\n" +
//                    "\n" +
//                    "20\n" +
//                    "Sophie Adams\n" +
//                    "6547\n" +
//                    "2\n" +
//                    "1\n" +
//                    "2153779146387\n");
//        }

//        if (createFile("designation.data.txt")) {
//            writeContent("designation.data.txt", "1\n" +
//                    "Senior Software Engineer\n" +
//                    "1723415946387\n" +
//                    "\n" +
//                    "2\n" +
//                    "Marketing Specialist\n" +
//                    "1745128746387\n" +
//                    "\n" +
//                    "3\n" +
//                    "Financial Analyst\n" +
//                    "1767831546387\n" +
//                    "\n" +
//                    "4\n" +
//                    "Human Resources Manager\n" +
//                    "1790534346387\n" +
//                    "\n" +
//                    "5\n" +
//                    "Graphic Designer\n" +
//                    "1813237146387\n" +
//                    "\n" +
//                    "6\n" +
//                    "Project Manager\n" +
//                    "1835939946387\n" +
//                    "\n" +
//                    "7\n" +
//                    "Sales Representative\n" +
//                    "1858642746387\n" +
//                    "\n" +
//                    "8\n" +
//                    "Data Scientist\n" +
//                    "1881345546387\n" +
//                    "\n" +
//                    "9\n" +
//                    "Customer Support Specialist\n" +
//                    "1904048346387\n" +
//                    "\n" +
//                    "10\n" +
//                    "Operations Manager\n" +
//                    "1926751146387\n" +
//                    "\n" +
//                    "11\n" +
//                    "Research Scientist\n" +
//                    "1949453946387\n" +
//                    "\n" +
//                    "12\n" +
//                    "Product Manager\n" +
//                    "1972156746387\n" +
//                    "\n" +
//                    "13\n" +
//                    "UX/UI Designer\n" +
//                    "1994859546387\n" +
//                    "\n" +
//                    "14\n" +
//                    "Business Analyst\n" +
//                    "2017562346387\n" +
//                    "\n" +
//                    "15\n" +
//                    "IT Support Specialist\n" +
//                    "2040265146387\n" +
//                    "\n" +
//                    "16\n" +
//                    "Content Writer\n" +
//                    "2062967946387\n" +
//                    "\n" +
//                    "17\n" +
//                    "Quality Assurance Engineer\n" +
//                    "2085670746387\n" +
//                    "\n" +
//                    "18\n" +
//                    "Administrative Assistant\n" +
//                    "2108373546387\n" +
//                    "\n" +
//                    "19\n" +
//                    "Sales Manager\n" +
//                    "2131076346387\n" +
//                    "\n" +
//                    "20\n" +
//                    "Software Architect\n" +
//                    "2153779146387\n");
//        }
    }

    private static boolean createFile(String fileName) {
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("Database file created: " + file.getName());
                return true;
            } else {
                System.out.println("Database file already exists: " + file.getName());
                return false;
            }
        } catch (IOException e) {
            System.out.println("Unable to create database file due to an error: " + e.getMessage());
            return false;
        }
    }

    private static void writeContent(String fileName, String content) {
        try {
            Files.writeString(Path.of(fileName), content);
        } catch (IOException e) {
            System.out.println("Unable to write content due to an error: " + e.getMessage());
        }
    }

}
