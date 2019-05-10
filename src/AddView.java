import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class AddView {
    private StudentsController controller;

    public AddView(StudentsController controller) {
        this.controller = controller;
    }

    public Dialog getDialog() {
        javafx.scene.control.Dialog addDialog = new javafx.scene.control.Dialog();

        addDialog.setTitle("Add student");
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        addDialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField studentFirstName = new TextField();
        TextField studentSecondName = new javafx.scene.control.TextField();
        TextField studentSurname = new javafx.scene.control.TextField();
        TextField brothers = new javafx.scene.control.TextField();
        TextField sisters = new javafx.scene.control.TextField();

        TextField fathersFirstName = new TextField();
        TextField fathersSecondName = new TextField();
        TextField fathersSurname = new TextField();
        TextField fathersIncome = new TextField();

        TextField mothersFirstName = new TextField();
        TextField mothersSecondName = new TextField();
        TextField mothersSurname = new TextField();
        TextField mothersIncome = new TextField();

        setNumericInput(sisters);
        setNumericInput(brothers);
        setNumericInput(mothersIncome);
        setNumericInput(fathersIncome);

        Label motherFirstNameLabel = new Label("Mother's first name");
        Label motherSecondNameLabel = new Label("Mother's second name");
        Label motherSurnameLabel = new Label("Mother's surname");
        Label mothersIncomeLabel = new Label("Mother's income");

        Label fatherFirstNameLabel = new Label("Father's first name");
        Label fatherSecondNameLabel = new Label("Father's second name");
        Label fatherSurnameLabel = new Label("Father's surname");
        Label fathersIncomeLabel = new Label("Father's income");


        Label studentFirstNameLabel = new Label("Student's first name");
        Label studentSecondNameLabel = new Label("Student's second name");
        Label studentSurnameLabel = new Label("Student's surname");
        Label studentSistersLabel = new Label("Amount of sisters");
        Label studentBrothersLabel = new Label("Amount of brothers");

        grid.add(studentFirstNameLabel, 0, 0);
        grid.add(studentFirstNameLabel, 0, 0);
        grid.add(studentFirstName, 1, 0);
        grid.add(studentSecondNameLabel, 0, 1);
        grid.add(studentSecondName, 1, 1);
        grid.add(studentSurnameLabel, 0, 2);
        grid.add(studentSurname, 1, 2);

        grid.add(studentBrothersLabel, 0, 3);
        grid.add(brothers, 1, 3);

        grid.add(studentSistersLabel, 0, 4);
        grid.add(sisters, 1, 4);

        grid.add(motherFirstNameLabel, 0, 5);
        grid.add(mothersFirstName, 1, 5);
        grid.add(motherSecondNameLabel, 0, 6);
        grid.add(mothersSecondName, 1, 6);
        grid.add(motherSurnameLabel, 0, 7);
        grid.add(mothersSurname, 1, 7);
        grid.add(mothersIncomeLabel, 0, 8);
        grid.add(mothersIncome, 1, 8);

        grid.add(fatherFirstNameLabel, 0, 9);
        grid.add(fathersFirstName, 1, 9);
        grid.add(fatherSecondNameLabel, 0, 10);
        grid.add(fathersSecondName, 1, 10);
        grid.add(fatherSurnameLabel, 0, 11);
        grid.add(fathersSurname, 1, 11);
        grid.add(fathersIncomeLabel, 0, 12);
        grid.add(fathersIncome, 1, 12);

        addDialog.getDialogPane().setContent(grid);

        addDialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                controller.addStudent(studentFirstName.getText(), studentSecondName.getText(), studentSurname.getText(),
                        sisters.getText(), brothers.getText(),
                        fathersIncome.getText(), mothersIncome.getText(),
                        fathersFirstName.getText(), mothersFirstName.getText(), fathersSecondName.getText(),
                        mothersSecondName.getText(), fathersSurname.getText(), mothersSurname.getText());
            }
            return null;
        });

        return addDialog;
    }

    private void setNumericInput(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
