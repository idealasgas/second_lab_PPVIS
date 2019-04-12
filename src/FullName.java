public class FullName {
    public String firstName;
    public String secondName;
    public String surname;

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSurname() {
        return surname;
    }

    public FullName(String surname, String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
    }


}
