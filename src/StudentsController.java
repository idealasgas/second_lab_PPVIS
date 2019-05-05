import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentsController {
    private MainModel model;

    public StudentsController(MainModel model) {
        this.model = model;
    }

    public ArrayList<Student> deleteByName(String name, String secondName, String surname) {
        List list = model.students.stream().filter(student ->
                (name.equals(student.getFirstName()) || secondName.equals(student.getSecondName())
                        || surname.equals(student.getSurname()))).collect(Collectors.toList());
        model.students.removeIf(student -> student.getFirstName().equals(name) && student.getSecondName().equals(secondName) && student.getSurname().equals(surname));

        ArrayList<Student> filteredStudents = new ArrayList<>(list);

        return filteredStudents;
    }
}
