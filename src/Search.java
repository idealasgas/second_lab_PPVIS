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
                (firstName.equals(student.getFirstName()) || secondName.equals(student.getSecondName())
                    || surname.equals(student.getSurname()))).collect(Collectors.toList());

        ArrayList<Student> filteredStudents = new ArrayList<>(list);

        return filteredStudents;
    }

    public ArrayList<Student> searchByParentName(String firstName, String secondName, String surname) {
        List list = students.stream().filter(student -> ((firstName.equals(student.getFather().getFirstName()) ||
                                                          secondName.equals(student.getFather().getSecondName()) ||
                                                          surname.equals(student.getFather().getSurname())) ||
                                                        (firstName.equals(student.getMother().getFullName()) ||
                                                         secondName.equals(student.getMother().getSecondName()) ||
                                                         surname.equals(student.getMother().getSurname())))).collect(Collectors.toList());
        ArrayList<Student> filteredStudents = new ArrayList<>(list);
        return filteredStudents;
    }

    public ArrayList<Student> searchByNumberOfSiblings(String type, String number) {
        switch (type) {
            case "sisters": {
                List list = students.stream().filter(student -> (Integer.parseInt(number) == student.getSisters())).collect(Collectors.toList());
                return new ArrayList<Student>(list);
            }
            case "brothers": {
                List list = students.stream().filter(student -> (Integer.parseInt(number) == student.getBrothers())).collect(Collectors.toList());
                return new ArrayList<Student>(list);
            }
        }
        return null;
    }
}
