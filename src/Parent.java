public class Parent {
    private int income;

    private String firstName;
    private String secondName;
    private String surname;

    public Parent(int income, String firstName, String secondName, String surname) {
        this.income = income;
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
    }

    public int getIncome() {
        return income;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getSecondName() {
        return secondName;
    }
}
