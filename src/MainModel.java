import java.util.List;

public class MainModel {
    private List<Student> studentArrayList;

    MainModel(List<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }

    public List<Student> getStudentArrayList() {
        return studentArrayList;
    }
}
