import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentsController {
    private ArrayList<Student> students;

    public StudentsController(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> deleteByName(String name, String secondName, String surname) {
        List list = students.stream().filter(student ->
                (name.equals(student.getFirstName()) || secondName.equals(student.getSecondName())
                        || surname.equals(student.getSurname()))).collect(Collectors.toList());

        ArrayList<Student> filteredStudents = new ArrayList<>(list);

        return filteredStudents;
    }
}
