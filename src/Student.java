import java.util.ArrayList;

public class Student {
    private String id;
    private String name;
    private int year;
    private int missingWorkCount;
    private ArrayList<Grade> grades;
    private int attendance;

    public Student(String studentId, String studentName, int studentYear, int studentMissingWorkCount) {
        this.id = studentId;
        this.name = studentName;
        this.year = studentYear;
        this.missingWorkCount = studentMissingWorkCount;
        this.grades = new ArrayList<>();
        this.attendance = 0;
    }

    // Modifier methods
    public void setId(String newId) {
        this.id = newId;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setYear(int newYear) {
        this.year = newYear;
    }

    public void setMissingWorkCount(int newMissingWorkCount) {
        this.missingWorkCount = newMissingWorkCount;
    }

    public void addGrade(String subject, double grade) {
        this.grades.add(new Grade(subject, grade));
    }

    public void setAttendance(int newAttendance) {
        this.attendance = newAttendance;
    }

    // Accessor methods
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getYear() {
        return this.year;
    }

    public int getMissingWorkCount() {
        return this.missingWorkCount;
    }

    public ArrayList<Grade> getGrades() {
        return this.grades;
    }

    public int getAttendance() {
        return this.attendance;
    }

    public double getAverageGrade() {
        if (this.grades.isEmpty()) {
            return -1; // Indicate that there are no grades
        }
        double sum = 0;
        for (Grade grade : this.grades) {
            sum += grade.getGrade();
        }
        return sum / this.grades.size();
    }

    // New method to get grades as a formatted string
    public String getGradesString() {
        if (this.grades.isEmpty()) {
            return "No grades available.";
        }
        StringBuilder sb = new StringBuilder();
        for (Grade grade : this.grades) {
            sb.append("Subject: ").append(grade.getSubject()).append(", Grade: ").append(grade.getGrade()).append("; ");
        }
        return sb.toString();
    }

    public class Grade {
        private String subject;
        private double grade;

        public Grade(String subject, double grade) {
            this.subject = subject;
            this.grade = grade;
        }

        public String getSubject() {
            return this.subject;
        }

        public double getGrade() {
            return this.grade;
        }
    }
}
