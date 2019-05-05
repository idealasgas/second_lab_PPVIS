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
                ((equal(name, student.getFirstName())) && (equal(secondName, student.getSecondName()))
                        && (equal(surname, student.getSurname())))).collect(Collectors.toList());

        model.students.removeIf(student -> equal(name, student.getFirstName()) && equal(secondName, student.getSecondName()) && equal(surname, student.getSurname()));
        ArrayList<Student> filteredStudents = new ArrayList<>(list);
        model.studentArrayList.removeAll(filteredStudents);

        return filteredStudents;
    }

    private boolean equal(String input, String value) {
        if (input.isEmpty()) {
            return true;
        } else {
            return input.equals(value);
        }
    }

    public ArrayList<Student> deleteByParentsName(String type, String name, String secondName, String surname){
        List list;
        if (type == "mother") {
            list = model.students.stream().filter(student -> (equal(name, student.getMother().getFirstName())) &&
                    (equal(secondName, student.getMother().getSecondName())) && (equal(surname, student.getMother().getSurname()))).collect(Collectors.toList());
        } else {
            list = model.students.stream().filter(student -> (equal(name, student.getFather().getFirstName())) &&
                    (equal(secondName, student.getFather().getSecondName())) && (equal(surname, student.getFather().getSurname()))).collect(Collectors.toList());
        }
        model.students.removeAll(list);
        model.studentArrayList.removeAll(list);
        ArrayList<Student> filtered = new ArrayList<>(list);
        return filtered;
    }
}
