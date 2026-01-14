import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDAO dao = new StudentDAO();

    public static void main(String[] args) {
        System.out.println("=== Student Management System (JDBC + MySQL) ===");

        while (true) {
            printMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    addStudentFlow();
                    break;
                case 2:
                    viewAllFlow();
                    break;
                case 3:
                    updateStudentFlow();
                    break;
                case 4:
                    deleteStudentFlow();
                    break;
                case 5:
                    viewByIdFlow();
                    break;
                case 0:
                    System.out.println("Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1) Add student");
        System.out.println("2) View all students");
        System.out.println("3) Update student");
        System.out.println("4) Delete student");
        System.out.println("5) View student by ID");
        System.out.println("0) Exit");
    }

    private static void addStudentFlow() {
        System.out.println("\n-- Add Student --");
        String name = readString("Name: ");
        int age = readInt("Age: ");
        String course = readString("Course: ");

        Student s = new Student(name, age, course);
        boolean ok = dao.addStudent(s);
        System.out.println(ok ? "Student added." : "Failed to add student.");
    }

    private static void viewAllFlow() {
        System.out.println("\n-- All Students --");
        List<Student> list = dao.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : list) {
                System.out.println(s);
            }
        }
    }

    private static void updateStudentFlow() {
        System.out.println("\n-- Update Student --");
        int id = readInt("ID to update: ");
        Student existing = dao.getById(id);
        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Current: " + existing);
        String name = readString("New name (leave blank to keep): ");
        String ageStr = readString("New age (leave blank to keep): ");
        String course = readString("New course (leave blank to keep): ");

        if (!name.isEmpty()) existing.setName(name);
        if (!ageStr.isEmpty()) existing.setAge(Integer.parseInt(ageStr));
        if (!course.isEmpty()) existing.setCourse(course);

        boolean ok = dao.updateStudent(existing);
        System.out.println(ok ? "Student updated." : "Failed to update student.");
    }

    private static void deleteStudentFlow() {
        System.out.println("\n-- Delete Student --");
        int id = readInt("ID to delete: ");
        boolean ok = dao.deleteStudent(id);
        System.out.println(ok ? "Student deleted." : "Failed to delete student.");
    }

    private static void viewByIdFlow() {
        System.out.println("\n-- View Student by ID --");
        int id = readInt("ID: ");
        Student s = dao.getById(id);
        System.out.println(s != null ? s : "Student not found.");
    }

    // Helpers
    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}