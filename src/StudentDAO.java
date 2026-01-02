import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // CREATE - Add new student
    public boolean addStudent(Student student) {
        String query = "INSERT INTO students (name, email, phone, course, marks, enrollment_date, status) " +
                "VALUES ('" + student.getName() + "', '" + student.getEmail() + "', '" +
                student.getPhone() + "', '" + student.getCourse() + "', " +
                student.getMarks() + ", CURDATE(), 'Active')";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("\n✓ Student added successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("\n✗ Error adding student: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    // READ - Get all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setCourse(rs.getString("course"));
                student.setMarks(rs.getDouble("marks"));
                student.setStatus(rs.getString("status"));

                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("\n✗ Error fetching students: " + e.getMessage());
            e.printStackTrace();
        }

        return students;
    }

    // READ - Get student by ID
    public Student getStudentById(int id) {
        String query = "SELECT * FROM students WHERE student_id = " + id;
        Student student = null;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setCourse(rs.getString("course"));
                student.setMarks(rs.getDouble("marks"));
                student.setStatus(rs.getString("status"));
            }

        } catch (SQLException e) {
            System.out.println("\n✗ Error fetching student: " + e.getMessage());
            e.printStackTrace();
        }

        return student;
    }

    // UPDATE - Update student information
    public boolean updateStudent(Student student) {
        String query = "UPDATE students SET name = '" + student.getName() +
                "', email = '" + student.getEmail() +
                "', phone = '" + student.getPhone() +
                "', course = '" + student.getCourse() +
                "', marks = " + student.getMarks() +
                " WHERE student_id = " + student.getStudentId();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("\n✓ Student updated successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("\n✗ Error updating student: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    // DELETE - Remove student
    public boolean deleteStudent(int id) {
        String query = "DELETE FROM students WHERE student_id = " + id;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("\n✓ Student deleted successfully!");
                return true;
            } else {
                System.out.println("\n✗ No student found with ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("\n✗ Error deleting student: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }
}