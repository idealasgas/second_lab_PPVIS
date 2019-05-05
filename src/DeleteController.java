import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteController {
    private ArrayList<Student> students;

    DeleteController(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> deleteByStudentName(String firstName, String secondName, String surname) {
//        students.removeIf(student -> student.getFirstName().equals(firstName) && student.getSecondName().equals(secondName) && student.getSurname().equals(surname));
        List list = students.stream().filter(student ->
                (firstName.equals(student.getFirstName()) || secondName.equals(student.getSecondName())
                        || surname.equals(student.getSurname()))).collect(Collectors.toList());

        ArrayList<Student> filteredStudents = new ArrayList<>(list);

        return filteredStudents;
//        return students;
    }
}
