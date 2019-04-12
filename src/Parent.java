public class Parent {
    private int income;
    private FullName name;

    public Parent(int income, FullName name) {
        this.income = income;
        this.name = name;
    }

    public FullName getName() {
        return name;
    }

    public int getIncome() {
        return income;
    }
}
