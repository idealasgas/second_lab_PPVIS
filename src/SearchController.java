import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchController {
    private ArrayList<Student> students;

    SearchController(ArrayList<Student> students) {
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

    public ArrayList<Student> searchByParentIncome(String type, String lowerBound, String upperBound) {
        switch (type) {
            case "mother": {
                if (!lowerBound.equals("") && upperBound.equals("")) {
                    List list = students.stream().filter(student -> (student.getMother().getIncome() >= Integer.parseInt(lowerBound)))
                                                 .collect(Collectors.toList());
                    return new ArrayList<Student>(list);
                }
                if (!upperBound.equals("") && lowerBound.equals("")) {
                    List list = students.stream().filter(student -> (student.getMother().getIncome() <= Integer.parseInt(upperBound)))
                                                 .collect(Collectors.toList());
                    return new ArrayList<Student>(list);
                }

                if (!upperBound.equals("") && !lowerBound.equals("")) {
                    List list = students.stream().filter(student -> ((student.getMother().getIncome() < Integer.parseInt(upperBound)) && student.getMother().getIncome() > Integer.parseInt(lowerBound)))
                                                 .collect(Collectors.toList());
                    return new ArrayList<Student>(list);
                }
            }
            case "father": {
                if (!lowerBound.equals("") && upperBound.equals("")) {
                    List list = students.stream().filter(student -> (student.getFather().getIncome() >= Integer.parseInt(lowerBound)))
                            .collect(Collectors.toList());
                    return new ArrayList<Student>(list);
                }
                if (!upperBound.equals("") && lowerBound.equals("")) {
                    List list = students.stream().filter(student -> (student.getFather().getIncome() <= Integer.parseInt(upperBound)))
                            .collect(Collectors.toList());
                    return new ArrayList<Student>(list);
                }

                if (!upperBound.equals("") && !lowerBound.equals("")) {
                    List list = students.stream().filter(student -> ((student.getFather().getIncome() < Integer.parseInt(upperBound)) && student.getMother().getIncome() > Integer.parseInt(lowerBound)))
                            .collect(Collectors.toList());
                    return new ArrayList<Student>(list);
                }
            }
        }
        return null;
    }
}
