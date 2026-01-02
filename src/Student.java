public class Student {

    private int studentId;
    private String name;
    private String email;
    private String phone;
    private String course;
    private double marks;
    private String enrollmentDate;
    private String status;

    // Default Constructor
    public Student() {
    }

    // Constructor for adding new student
    public Student(String name, String email, String phone, String course, double marks) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.course = course;
        this.marks = marks;
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\n╔═══════════════════════════════════════════════════════╗" +
                "\n║                  STUDENT DETAILS                      ║" +
                "\n╠═══════════════════════════════════════════════════════╣" +
                "\n║ Student ID    : " + String.format("%-37s", studentId) + "║" +
                "\n║ Name          : " + String.format("%-37s", name) + "║" +
                "\n║ Email         : " + String.format("%-37s", email) + "║" +
                "\n║ Phone         : " + String.format("%-37s", phone) + "║" +
                "\n║ Course        : " + String.format("%-37s", course) + "║" +
                "\n║ Marks         : " + String.format("%-37s", marks) + "║" +
                "\n║ Status        : " + String.format("%-37s", status) + "║" +
                "\n╚═══════════════════════════════════════════════════════╝";
    }
}