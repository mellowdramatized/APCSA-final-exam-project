import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

public class App {
    private static ArrayList<Student> students = new ArrayList<>();
    private static JFrame frame;
    private static JTextArea studentDetailsTextArea;
    public static boolean isStudentSelected = false;
    public static int lastSelectedStudent = 0;

    public static void main(String[] args) {
        // Add initial students

        students.add(new Student("S001", "Carter", 9, 0));
        students.add(new Student("S002", "Emma", 10, 2));
        students.add(new Student("S003", "Liam", 11, 1));
        students.add(new Student("S004", "Olivia", 12, 3));
        students.add(new Student("S005", "Noah", 9, 0));
        students.add(new Student("S006", "Ava", 10, 4));
        students.add(new Student("S007", "Sophia", 11, 2));
        students.add(new Student("S008", "Mason", 12, 5));
        students.add(new Student("S009", "Isabella", 9, 1));
        students.add(new Student("S010", "Lucas", 10, 0));
        students.add(new Student("S011", "Mia", 11, 3));
        students.add(new Student("S012", "Ethan", 12, 2));
        students.add(new Student("S013", "Amelia", 9, 4));
        students.add(new Student("S014", "Kaidrick", 12, 0));
        // Set up the GUI frame
        frame = new JFrame("finite campus");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Add components to the frame
        studentDetailsTextArea = new JTextArea();
        studentDetailsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(studentDetailsTextArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 5));

        JButton changeNameButton = new JButton("Change Name");
        JButton changeYearButton = new JButton("Change Year");
        JButton changeMissingWorkButton = new JButton("Change Missing Work");
        JButton addGradeButton = new JButton("Add Grade");
        JButton getAverageGradeButton = new JButton("Get Average Grade");
        JButton addStudentButton = new JButton("Add Student");
        JButton removeStudentButton = new JButton("Remove Student");
        JButton deselectStudentButton = new JButton("Deselect Student");
        JButton editAttendanceButton = new JButton("Edit Absencece's");
        JButton searchStudentButton = new JButton("Search Student");
        JButton sortStudentsButton = new JButton("Sort Students");
        JButton exportDataButton = new JButton("Export Data");
        JButton encodeDataButton = new JButton("Encode Data");
        JButton decodeDataButton = new JButton("Decode Data");

        buttonPanel.add(changeNameButton);
        buttonPanel.add(changeYearButton);
        buttonPanel.add(changeMissingWorkButton);
        buttonPanel.add(addGradeButton);
        buttonPanel.add(getAverageGradeButton);
        buttonPanel.add(addStudentButton);
        buttonPanel.add(removeStudentButton);
        buttonPanel.add(deselectStudentButton);
        buttonPanel.add(editAttendanceButton);
        buttonPanel.add(searchStudentButton);
        buttonPanel.add(sortStudentsButton);
        
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);

        // Action listeners for the buttons
        changeNameButton.addActionListener(e -> changeStudentName());
        changeYearButton.addActionListener(e -> changeStudentYear());
        changeMissingWorkButton.addActionListener(e -> changeStudentMissingWork());
        addGradeButton.addActionListener(e -> addGradeToStudent());
        getAverageGradeButton.addActionListener(e -> getStudentAverageGrade());
        addStudentButton.addActionListener(e -> addStudent());
        removeStudentButton.addActionListener(e -> removeStudent());
        deselectStudentButton.addActionListener(e -> deselectStudent());
        editAttendanceButton.addActionListener(e -> editAttendance());
        searchStudentButton.addActionListener(e -> searchStudent());
        sortStudentsButton.addActionListener(e -> sortStudents());
        exportDataButton.addActionListener(e -> exportData());
        encodeDataButton.addActionListener(e -> encode());
        decodeDataButton.addActionListener(e -> decode());

        // Initially show the list of students
        listStudents();
    }

    private static void listStudents() {
        StringBuilder sb = new StringBuilder("List of Students:\n");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            sb.append((i + 1)).append(". ").append(student.getName())
                    .append(" (ID: ").append(student.getId())
                    .append(", Year: ").append(student.getYear())
                    .append(", Missing work count: ").append(student.getMissingWorkCount())
                    .append(", Attendance: ").append(student.getAttendance())
                    .append(")\n");
        }
        studentDetailsTextArea.setText(sb.toString());
    }

    private static void printStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(student.getId()).append("\n")
                .append("Name: ").append(student.getName()).append("\n")
                .append("Year: ").append(student.getYear()).append("\n")
                .append("Missing work count: ").append(student.getMissingWorkCount()).append("\n")
                .append("Attendance: ").append(student.getAttendance()).append("\n");
        if (student.getGrades().isEmpty()) {
            sb.append("Grades: No grades available.\n");
        } else {
            sb.append("Grades:\n");
            for (Student.Grade grade : student.getGrades()) {
                sb.append("  Subject: ").append(grade.getSubject()).append(", Grade: ").append(grade.getGrade())
                        .append("\n");
            }
            sb.append("Average Grade: ").append(student.getAverageGrade()).append("\n");
        }
        studentDetailsTextArea.setText(sb.toString());
    }

    private static void changeStudentName() {
        int studentIndex = getStudentIndex();
        if (studentIndex != -1) {
            String newName = JOptionPane.showInputDialog("Enter new name:");
            students.get(studentIndex).setName(newName);
            JOptionPane.showMessageDialog(frame,
                    "Name changed successfully to " + students.get(studentIndex).getName());
            if (isStudentSelected) {
                printStudentDetails(students.get(studentIndex));
            } else {
                listStudents();
            }
        }
    }

    private static void changeStudentYear() {
        int studentIndex = getStudentIndex();
        if (studentIndex != -1) {
            String newYearStr = JOptionPane.showInputDialog("Enter new year:");
            int newYear = Integer.parseInt(newYearStr);
            students.get(studentIndex).setYear(newYear);
            JOptionPane.showMessageDialog(frame,
                    "Year changed successfully to " + students.get(studentIndex).getYear());
            if (isStudentSelected) {
                printStudentDetails(students.get(studentIndex));
            } else {
                listStudents();
            }
        }
    }

    private static void changeStudentMissingWork() {
        int studentIndex = getStudentIndex();
        if (studentIndex != -1) {
            String newMissingWorkCountStr = JOptionPane.showInputDialog("Enter new missing work count:");
            int newMissingWorkCount = Integer.parseInt(newMissingWorkCountStr);
            students.get(studentIndex).setMissingWorkCount(newMissingWorkCount);
            JOptionPane.showMessageDialog(frame,
                    "Missing work count changed successfully to " + students.get(studentIndex).getMissingWorkCount());
            if (isStudentSelected) {
                printStudentDetails(students.get(studentIndex));
            } else {
                listStudents();
            }
        }
    }

    private static void addGradeToStudent() {
        int studentIndex = getStudentIndex();
        if (studentIndex != -1) {
            String subject = JOptionPane.showInputDialog("Enter subject:");
            String gradeStr = JOptionPane.showInputDialog("Enter grade:");
            double grade = Double.parseDouble(gradeStr);
            students.get(studentIndex).addGrade(subject, grade);
            JOptionPane.showMessageDialog(frame, "Grade added successfully");
            if (isStudentSelected) {
                printStudentDetails(students.get(studentIndex));
            } else {
                listStudents();
            }
        }
    }

    private static void getStudentAverageGrade() {
        int studentIndex = getStudentIndex();
        if (studentIndex != -1) {
            double averageGrade = students.get(studentIndex).getAverageGrade();
            if (averageGrade == -1) {
                JOptionPane.showMessageDialog(frame, "No grades available to calculate an average.");
            } else {
                JOptionPane.showMessageDialog(frame, "Average grade: " + averageGrade);
            }
            if (isStudentSelected) {
                printStudentDetails(students.get(studentIndex));
            } else {
                listStudents();
            }
        }
    }

    private static void addStudent() {
        String id = JOptionPane.showInputDialog("Enter student ID:");
        String name = JOptionPane.showInputDialog("Enter student name:");
        String yearStr = JOptionPane.showInputDialog("Enter student year:");
        int year = Integer.parseInt(yearStr);
        String missingWorkCountStr = JOptionPane.showInputDialog("Enter student missing work count:");
        int missingWorkCount = Integer.parseInt(missingWorkCountStr);
        students.add(new Student(id, name, year, missingWorkCount));
        JOptionPane.showMessageDialog(frame, "Student added successfully.");
        listStudents();
    }

    private static void removeStudent() {
        int studentIndex = getStudentIndex();
        if (studentIndex != -1) {
            students.remove(studentIndex);
            JOptionPane.showMessageDialog(frame, "Student removed successfully.");
            listStudents();
        }
    }

    private static void deselectStudent() {
        isStudentSelected = false;
        listStudents();
    }

    private static void editAttendance() {
        int studentIndex = getStudentIndex();
        if (studentIndex != -1) {
            String newAttendanceStr = JOptionPane.showInputDialog("Enter new Absence count:");
            int newAttendance = Integer.parseInt(newAttendanceStr);
            students.get(studentIndex).setAttendance(newAttendance);
            JOptionPane.showMessageDialog(frame, "Attendance updated successfully.");
            if (isStudentSelected) {
                printStudentDetails(students.get(studentIndex));
            } else {
                listStudents();
            }
        }
    }

    private static void searchStudent() {
        String searchTerm = JOptionPane.showInputDialog("Enter student name or ID to search:");
        boolean found = false;
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getName().equalsIgnoreCase(searchTerm) || student.getId().equalsIgnoreCase(searchTerm)) {
                printStudentDetails(student);
                lastSelectedStudent = i;
                isStudentSelected = true;
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(frame, "Student not found.");
        }
    }

    private static void sortStudents() {
        String[] options = { "Name", "Year", "Missing Work Count" };
        int choice = JOptionPane.showOptionDialog(frame, "Sort students by:", "Sort Students",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        switch (choice) {
            case 0: // Sort by Name
                students.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
                break;
            case 1: // Sort by Year
                students.sort((s1, s2) -> Integer.compare(s1.getYear(), s2.getYear()));
                break;
            case 2: // Sort by Missing Work Count
                students.sort((s1, s2) -> Integer.compare(s1.getMissingWorkCount(), s2.getMissingWorkCount()));
                break;
        }
        listStudents();
    }

    private static void exportData() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID,Name,Year,Missing Work Count,Attendance,Grades\n");
        for (Student student : students) {
            sb.append(student.getId()).append(",");
            sb.append(student.getName()).append(",");
            sb.append(student.getYear()).append(",");
            sb.append(student.getMissingWorkCount()).append(",");
            sb.append(student.getAttendance()).append(",");
            sb.append(student.getGradesString()).append("\n");
        }
        try {
            FileWriter writer = new FileWriter("students_data.txt");
            writer.write(sb.toString());
            writer.close();
            JOptionPane.showMessageDialog(frame, "Data exported successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error exporting data: " + e.getMessage());
        }
    }

    private static int getStudentIndex() {
        if (!isStudentSelected) {
            String indexStr = JOptionPane.showInputDialog("Enter the student index (the number next to their name):");
            int studentIndex = Integer.parseInt(indexStr) - 1;
            lastSelectedStudent = studentIndex;
            if (studentIndex < 0 || studentIndex >= students.size()) {
                JOptionPane.showMessageDialog(frame, "Invalid student index.");
                return -1;
            }
            return studentIndex;
        } else {
            return lastSelectedStudent;
        }
    }

    public static void encode() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID,Name,Year,Missing Work Count,Attendance,Grades\n");
        for (Student student : students) {
            sb.append(student.getId()).append(",");
            sb.append(student.getName()).append(",");
            sb.append(student.getYear()).append(",");
            sb.append(student.getMissingWorkCount()).append(",");
            sb.append(student.getAttendance()).append(",");
            sb.append(student.getGradesString()).append("\n");
        }
        byte[] encodedBytes = Base64.getEncoder().encode(sb.toString().getBytes());
        String encodedString = new String(encodedBytes);

        // Display the encoded data in a new JFrame
        JFrame encodeFrame = new JFrame("Encoded Data");
        encodeFrame.setSize(600, 400);
        encodeFrame.setLayout(new BorderLayout());

        JTextArea encodedTextArea = new JTextArea(encodedString);
        encodedTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(encodedTextArea);
        encodeFrame.add(scrollPane, BorderLayout.CENTER);

        encodeFrame.setVisible(true);
    }

    public static void decode() {
        String encodedString = JOptionPane.showInputDialog("Enter encoded data:");
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);

        System.out.println("Decoded: " + decodedString);
        JOptionPane.showMessageDialog(frame, "Data decoded successfully.");
    }

}
