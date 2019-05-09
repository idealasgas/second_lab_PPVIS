import java.util.ArrayList;

public class MainModel {
    private ArrayList<Student> studentArrayList;

    MainModel(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }

    public ArrayList<Student> getStudentArrayList() {
        return studentArrayList;
    }
}
