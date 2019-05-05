public class Student {
    private int sisters, brothers;
    private Parent mother, father;

    private String firstName;
    private String secondName;
    private String surname;


    public Student(String firstName, String secondName, String surname, int sisters, int brothers) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.brothers = brothers;
        this.sisters = sisters;
    }

    public String getFullName() {
        return this.surname + " " + this.firstName + " " + this.secondName;
    }

    public void setMother(Parent mother) {
        this.mother = mother;
    }

    public void setFather(Parent father) {
        this.father = father;
    }

    public Parent getMother() {
        return mother;
    }

    public Parent getFather() {
        return father;
    }

    public int getSisters() {
         return sisters;
    }

    public int getBrothers() {
        return brothers;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSurname() {
        return surname;
    }
}
