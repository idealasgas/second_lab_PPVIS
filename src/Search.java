import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Search {
    private ArrayList<Student> students;

    Search(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> searchByStudentName(String firstName, String secondName, String surname) {
        List list = students.stream().filter(student ->
                (firstName.equals(student.getFirstName()) && secondName.equals(student.getSecondName())
                    && surname.equals(student.getSurname()))).collect(Collectors.toList());

        ArrayList<Student> filteredStudents = new ArrayList<>(list);

        return filteredStudents;
    }
}
