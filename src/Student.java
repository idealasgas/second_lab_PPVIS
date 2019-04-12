public class Student {
    private int sisters, brothers;
    private FullName name;
    private Parent mother, father;


    public Student(FullName name, Parent father, Parent mother, int sisters, int brothers) {
        this.name = name;
        this.sisters = sisters;
        this.brothers = brothers;
        this.father = father;
        this.mother = mother;
    }

    public Student(FullName name) {
        this.name = name;
    }

    public Student(FullName name, int sisters, int brothers) {
        this.name = name;
        this.sisters = sisters;
        this.brothers = brothers;
    }

    public String getName() {
        return name.firstName;
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
}
