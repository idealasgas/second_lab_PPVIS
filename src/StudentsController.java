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

    public ArrayList<Student> deleteByNumberOfSiblings(String type, String number){
        List list;
        if (type == "brothers") {
            list = model.students.stream().filter(student -> (student.getBrothers() == Integer.parseInt(number))).collect(Collectors.toList());
        } else {
            list = model.students.stream().filter(student -> (student.getSisters() == Integer.parseInt(number))).collect(Collectors.toList());
        }
        model.students.removeAll(list);
        model.studentArrayList.removeAll(list);
        ArrayList<Student> filtered = new ArrayList<>(list);
        return filtered;
    }

    public ArrayList<Student> deleteByParentsIncome(String parent, String lowerBound, String higherBound) {
        List list;
//        сделать булеаном
        if (parent == "mother's income") {
            list = model.students.stream().filter(student -> {
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
            list = model.students.stream().filter(student -> {
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
        model.students.removeAll(list);
        model.studentArrayList.removeAll(list);
        ArrayList<Student> filtered = new ArrayList<>(list);
        return filtered;
    }

    public ArrayList<Student> searchByStudentName(String name, String secondName, String surname) {
        List list = model.students.stream().filter(student ->
                ((equal(name, student.getFirstName())) && (equal(secondName, student.getSecondName()))
                        && (equal(surname, student.getSurname())))).collect(Collectors.toList());

        ArrayList<Student> filteredStudents = new ArrayList<>(list);

        return filteredStudents;
    }

    public ArrayList<Student> searchByParentsName(String type, String name, String secondName, String surname) {
        List list;
        if (type == "mother") {
            list = model.students.stream().filter(student -> (equal(name, student.getMother().getFirstName())) &&
                    (equal(secondName, student.getMother().getSecondName())) && (equal(surname, student.getMother().getSurname()))).collect(Collectors.toList());
        } else {
            list = model.students.stream().filter(student -> (equal(name, student.getFather().getFirstName())) &&
                    (equal(secondName, student.getFather().getSecondName())) && (equal(surname, student.getFather().getSurname()))).collect(Collectors.toList());
        }
        ArrayList<Student> filtered = new ArrayList<>(list);
        return filtered;
    }

    public ArrayList<Student> searchByNumberOfSiblings(String type, String number) {
        List list;
        if (type == "brothers") {
            list = model.students.stream().filter(student -> (student.getBrothers() == Integer.parseInt(number))).collect(Collectors.toList());
        } else {
            list = model.students.stream().filter(student -> (student.getSisters() == Integer.parseInt(number))).collect(Collectors.toList());
        }
        ArrayList<Student> filtered = new ArrayList<>(list);
        return filtered;
    }

    public ArrayList<Student> searchByParentsIncome(String parent, String lowerBound, String higherBound) {
        List list;
//        сделать булеаном
        if (parent == "mother's income") {
            list = model.students.stream().filter(student -> {
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
            list = model.students.stream().filter(student -> {
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

    private boolean equal(String input, String value) {
        if (input.isEmpty()) {
            return true;
        } else {
            return input.equals(value);
        }
    }
}
