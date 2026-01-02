import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static StudentDAO studentDAO = new StudentDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║                                                       ║");
        System.out.println("║              STUDENT MANAGEMENT SYSTEM                ║");
        System.out.println("║                                                       ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    viewStudentsTable();
                    break;
                case 4:
                    searchStudent();
                    break;
                case 5:
                    updateStudent();
                    break;
                case 6:
                    deleteStudent();
                    break;
                case 7:
                    running = false;
                    System.out.println("\n╔═══════════════════════════════════════════════════════╗");
                    System.out.println("║   Thank you for using Student Management System!     ║");
                    System.out.println("╚═══════════════════════════════════════════════════════╝");
                    break;
                default:
                    System.out.println("\n✗ Invalid choice! Please try again.");
            }

            if (running) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║                     MAIN MENU                         ║");
        System.out.println("╠═══════════════════════════════════════════════════════╣");
        System.out.println("║  1. Add New Student                                   ║");
        System.out.println("║  2. View All Students (Detailed)                      ║");
        System.out.println("║  3. View All Students (Table)                         ║");
        System.out.println("║  4. Search Student by ID                              ║");
        System.out.println("║  5. Update Student Information                        ║");
        System.out.println("║  6. Delete Student                                    ║");
        System.out.println("║  7. Exit                                              ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
    }

    private static void addNewStudent() {
        System.out.println("\n--- ADD NEW STUDENT ---");

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        System.out.print("Enter Marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine();

        Student student = new Student(name, email, phone, course, marks);
        studentDAO.addStudent(student);
    }

    private static void viewAllStudents() {
        System.out.println("\n--- ALL STUDENTS (DETAILED VIEW) ---");

        List<Student> students = studentDAO.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found in the database.");
        } else {
            System.out.println("Total Students: " + students.size());
            System.out.println("-------------------------------------------");

            for (Student student : students) {
                System.out.println(student);
                System.out.println("-------------------------------------------");
            }
        }
    }

    private static void viewStudentsTable() {
        System.out.println("\n--- ALL STUDENTS (TABLE VIEW) ---");

        List<Student> students = studentDAO.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found in the database.");
        } else {
            System.out.println("\nTotal Students: " + students.size());
            System.out.println("\n" + "=".repeat(110));
            System.out.printf("%-5s %-25s %-30s %-15s %-12s %-8s%n",
                    "ID", "Name", "Email", "Phone", "Course", "Marks");
            System.out.println("=".repeat(110));

            for (Student student : students) {
                System.out.printf("%-5d %-25s %-30s %-15s %-12s %-8.2f%n",
                        student.getStudentId(),
                        truncate(student.getName(), 25),
                        truncate(student.getEmail(), 30),
                        truncate(student.getPhone(), 15),
                        truncate(student.getCourse(), 12),
                        student.getMarks());
            }

            System.out.println("=".repeat(110));
        }
    }

    private static String truncate(String str, int maxLength) {
        if (str == null) return "";
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength - 3) + "...";
    }

    private static void searchStudent() {
        System.out.println("\n--- SEARCH STUDENT ---");

        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = studentDAO.getStudentById(id);

        if (student != null) {
            System.out.println("\n✓ Student Found:");
            System.out.println(student);
        } else {
            System.out.println("\n✗ No student found with ID: " + id);
        }
    }

    private static void updateStudent() {
        System.out.println("\n--- UPDATE STUDENT ---");

        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = studentDAO.getStudentById(id);

        if (student == null) {
            System.out.println("\n✗ No student found with ID: " + id);
            return;
        }

        System.out.println("Current Information:");
        System.out.println(student);

        System.out.println("\nEnter new information (press Enter to keep current value):");

        System.out.print("Name [" + student.getName() + "]: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) student.setName(name);

        System.out.print("Email [" + student.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) student.setEmail(email);

        System.out.print("Phone [" + student.getPhone() + "]: ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) student.setPhone(phone);

        System.out.print("Course [" + student.getCourse() + "]: ");
        String course = scanner.nextLine();
        if (!course.isEmpty()) student.setCourse(course);

        System.out.print("Marks [" + student.getMarks() + "]: ");
        String marksInput = scanner.nextLine();
        if (!marksInput.isEmpty()) {
            student.setMarks(Double.parseDouble(marksInput));
        }

        studentDAO.updateStudent(student);
    }

    private static void deleteStudent() {
        System.out.println("\n--- DELETE STUDENT ---");

        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = studentDAO.getStudentById(id);

        if (student == null) {
            System.out.println("\n✗ No student found with ID: " + id);
            return;
        }

        System.out.println("Student Information:");
        System.out.println(student);

        System.out.print("\n⚠ Are you sure you want to delete? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            studentDAO.deleteStudent(id);
        } else {
            System.out.println("\n✗ Deletion cancelled.");
        }
    }
}
