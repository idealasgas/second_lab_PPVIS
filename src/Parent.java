public class Parent {
    private int income;
    private FullName name;

    public Parent(int income, FullName name) {
        this.income = income;
        this.name = name;
    }

    public String getName() {
        return name.firstName;
    }
}
