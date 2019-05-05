import javafx.collections.ObservableList;

import java.util.ArrayList;

public class MainModel {
    public ArrayList<Student> studentArrayList;
    public ObservableList<Student> students;

    MainModel(ArrayList<Student> studentArrayList, ObservableList<Student> students) {
        this.studentArrayList = studentArrayList;
        this.students = students;
    }
}
