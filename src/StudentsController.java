import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentsController {
    private MainModel model;
    private Paginator paginator;

    public StudentsController(MainModel model, Paginator paginator) {
        this.model = model;
        this.paginator = paginator;
    }

    public ArrayList<Student> deleteByName(String name, String secondName, String surname) {
        ArrayList<Student> filteredStudents = searchByStudentName(name, secondName, surname);
        model.getStudentArrayList().removeAll(filteredStudents);
        paginator.refreshPages(model.getStudentArrayList());
        return filteredStudents;
    }

    public ArrayList<Student> deleteByParentsName(String type, String name, String secondName, String surname){
        ArrayList<Student> filteredStudents = searchByParentsName(type, name, secondName, surname);
        model.getStudentArrayList().removeAll(filteredStudents);
        paginator.refreshPages(model.getStudentArrayList());
        return filteredStudents;
    }

    public ArrayList<Student> deleteByNumberOfSiblings(String type, String number){
        ArrayList<Student> filteredStudents = searchByNumberOfSiblings(type, number);
        model.getStudentArrayList().removeAll(filteredStudents);
        paginator.refreshPages(model.getStudentArrayList());
        return filteredStudents;
    }

    public ArrayList<Student> deleteByParentsIncome(String parent, String lowerBound, String higherBound) {
        ArrayList<Student> filteredStudents = searchByParentsIncome(parent, lowerBound, higherBound);
        model.getStudentArrayList().removeAll(filteredStudents);
        paginator.refreshPages(model.getStudentArrayList());

        return filteredStudents;
    }

    public ArrayList<Student> searchByStudentName(String name, String secondName, String surname) {
        List list = model.getStudentArrayList().stream().filter(student ->
                ((equal(name, student.getFirstName())) && (equal(secondName, student.getSecondName()))
                        && (equal(surname, student.getSurname())))).collect(Collectors.toList());

        ArrayList<Student> filteredStudents = new ArrayList<>(list);

        return filteredStudents;
    }

    public ArrayList<Student> searchByParentsName(String type, String name, String secondName, String surname) {
        List list;
        if (type == "mother") {
            list = model.getStudentArrayList().stream().filter(student -> (equal(name, student.getMother().getFirstName())) &&
                    (equal(secondName, student.getMother().getSecondName())) && (equal(surname, student.getMother().getSurname()))).collect(Collectors.toList());
        } else {
            list = model.getStudentArrayList().stream().filter(student -> (equal(name, student.getFather().getFirstName())) &&
                    (equal(secondName, student.getFather().getSecondName())) && (equal(surname, student.getFather().getSurname()))).collect(Collectors.toList());
        }
        ArrayList<Student> filtered = new ArrayList<>(list);
        return filtered;
    }

    public ArrayList<Student> searchByNumberOfSiblings(String type, String number) {
        List list;
        if (type == "brothers") {
            list = model.getStudentArrayList().stream().filter(student -> (student.getBrothers() == Integer.parseInt(number))).collect(Collectors.toList());
        } else {
            list = model.getStudentArrayList().stream().filter(student -> (student.getSisters() == Integer.parseInt(number))).collect(Collectors.toList());
        }
        ArrayList<Student> filtered = new ArrayList<>(list);
        return filtered;
    }

    public ArrayList<Student> searchByParentsIncome(String parent, String lowerBound, String higherBound) {
        List list;
//        сделать булеаном
        if ("mother's income".equals(parent)) {
            list = model.getStudentArrayList().stream().filter(student -> {
                if (lowerBound.isEmpty()) {
                    return (student.getMother().getIncome() < Integer.parseInt(higherBound));
                } else if (higherBound.isEmpty()) {
                    return (student.getMother().getIncome() > Integer.parseInt(lowerBound));
                } else {
                    return ((student.getMother().getIncome() > Integer.parseInt(lowerBound))
                            && (student.getMother().getIncome() < Integer.parseInt(higherBound)));
                }
            }).collect(Collectors.toList());
        } else {
            list = model.getStudentArrayList().stream().filter(student -> {
                if (lowerBound.isEmpty()) {
                    return (student.getFather().getIncome() < Integer.parseInt(higherBound));
                } else if (higherBound.isEmpty()) {
                    return (student.getFather().getIncome() > Integer.parseInt(lowerBound));
                } else {
                    return ((student.getFather().getIncome() > Integer.parseInt(lowerBound))
                            && (student.getFather().getIncome() < Integer.parseInt(higherBound)));
                }
            }).collect(Collectors.toList());
        }
        ArrayList<Student> filtered = new ArrayList<>(list);
        return filtered;
    }

    public void addStudent(String name, String secondName, String surname, String sisters, String brothers, String fathersIncome,
                           String mothersIncome, String fathersName, String mothersName, String fathersSecondName,
                           String mothersSecondName, String fathersSurname, String mothersSurname) {
        Student newStudent = new Student(name, secondName, surname, Integer.parseInt(sisters), Integer.parseInt(brothers));
        Parent mother = new Parent(Integer.parseInt(mothersIncome), mothersName, mothersSecondName, mothersSurname);
        Parent father = new Parent(Integer.parseInt(fathersIncome), fathersName, fathersSecondName, fathersSurname);
        newStudent.setMother(mother);
        newStudent.setFather(father);

        model.getStudentArrayList().add(newStudent);
        paginator.refreshPages(model.getStudentArrayList());
    }

    private boolean equal(String input, String value) {
        if (input.isEmpty()) {
            return true;
        } else {
            return input.equals(value);
        }
    }
}
