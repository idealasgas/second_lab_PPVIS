import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class DeleteView {
    private Button deleteByStudentNameButton;
    private Button deleteByParentNameButton;
    private Button deleteByNumberOfSiblingsButton;
    private Button deleteByParentIncomeButton;
    private VBox box;
    private Dialog deleteDialog;
    private StudentsController controller;
    private ArrayList<Student> students;
    private Paginator paginator;

    DeleteView(StudentsController controller, Paginator paginator) {
        this.controller = controller;
        this.paginator = paginator;
    }

    public Dialog getDialog() {
        deleteDialog = new Dialog();

        deleteDialog.setTitle("Delete a student");
        deleteDialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);

        box = new VBox();
        box.setPadding(new Insets(20, 150, 10, 10));

        deleteByStudentNameButton = new Button("delete by student's name");
        deleteByParentNameButton = new Button("delete by parent's name");
        deleteByNumberOfSiblingsButton = new Button("delete by number of siblings");
        deleteByParentIncomeButton = new Button("delete by income");

        deleteByStudentNameButton.setOnAction(event -> deleteByStudentName());
        deleteByParentNameButton.setOnAction(event -> deleteByParentName());
        deleteByNumberOfSiblingsButton.setOnAction(event -> deleteByNumberOfSiblings());
        deleteByParentIncomeButton.setOnAction(event -> deleteByParentsIncome());

        HBox buttons = new HBox();

        buttons.getChildren().addAll(deleteByStudentNameButton, deleteByParentNameButton, deleteByNumberOfSiblingsButton, deleteByParentIncomeButton);
        box.getChildren().add(buttons);

        deleteDialog.getDialogPane().setContent(box);
        return deleteDialog;
    }

    private void deleteByStudentName() {
        Label name = new Label("first name:");
        Label secondName = new Label("second name:");
        Label surname = new Label("surname:");

        TextField nameField = new TextField();
        TextField secondNameField = new TextField();
        TextField surnameField = new TextField();

        Button deleteButton = new Button("delete");
        deleteButton.setOnAction(event -> {
            setStudents(controller.deleteByName(nameField.getText(), secondNameField.getText(), surnameField.getText()));
            showAlert();
        });

        HBox labelText1 = new HBox();
        HBox labelText2 = new HBox();
        HBox labelText3 = new HBox();

        labelText1.getChildren().addAll(name, nameField);
        labelText2.getChildren().addAll(secondName, secondNameField);
        labelText3.getChildren().addAll(surname, surnameField);

        VBox container = new VBox();
        container.getChildren().addAll(labelText1, labelText2, labelText3, deleteButton);
        disableButtons();
        deleteDialog.setHeight(300);
        box.getChildren().add(container);
    }

    private void deleteByParentName() {
        ToggleGroup radioButtons = new ToggleGroup();
        Button deleteButton = new Button("delete");
        RadioButton mother = new RadioButton("mother");
        RadioButton father = new RadioButton("father");

        radioButtons.getToggles().addAll(mother, father);

        Label nameLabel = new Label("name: ");
        Label secondNameLabel = new Label("second name: ");
        Label surnameLabel = new Label("surname: ");
        TextField name = new TextField();
        TextField secondName = new TextField();
        TextField surname = new TextField();

        GridPane grid = new GridPane();
        grid.add(nameLabel, 0, 0);
        grid.add(secondNameLabel,  0, 1);
        grid.add(surnameLabel,  0, 2);
        grid.add(name,  1, 0);
        grid.add(secondName,  1, 1);
        grid.add(surname,  1, 2);
        grid.add(mother, 0, 3);
        grid.add(father, 1, 3);
        grid.add(deleteButton, 2, 3);

        box.getChildren().add(grid);
        disableButtons();
        deleteDialog.setHeight(300);

        deleteButton.setOnAction(event -> {
            if (radioButtons.getSelectedToggle() == null) {
                // алерт
            } else {
                RadioButton selected = (RadioButton) radioButtons.getSelectedToggle();
                setStudents(controller.deleteByParentsName(selected.getText(), name.getText(), secondName.getText(), surname.getText()));
                showAlert();
            }
        });
    }

    private void deleteByNumberOfSiblings() {
        Label numberLabel = new Label("Number of siblings:");
        TextField number = new TextField();

        ToggleGroup radioButtons = new ToggleGroup();
        Button deleteButton = new Button("delete");
        RadioButton sisters = new RadioButton("sisters");
        RadioButton brothers = new RadioButton("brothers");

        radioButtons.getToggles().addAll(sisters, brothers);

        GridPane grid = new GridPane();
        grid.add(sisters, 0, 0);
        grid.add(brothers, 1, 0);
        grid.add(numberLabel, 0, 1);
        grid.add(number, 1, 1);
        grid.add(deleteButton, 2, 2);

        disableButtons();
        box.getChildren().add(grid);
        deleteDialog.setHeight(200);

        deleteButton.setOnAction(event -> {
            if (radioButtons.getSelectedToggle() == null) {
                // алерт
            } else {
                RadioButton selected = (RadioButton) radioButtons.getSelectedToggle();
                setStudents(controller.deleteByNumberOfSiblings(selected.getText(), number.getText()));
                showAlert();
            }});
    }

    private void deleteByParentsIncome() {
        ToggleGroup radioButtons = new ToggleGroup();
        RadioButton mother = new RadioButton("mother's income");
        RadioButton father = new RadioButton("father's income");

        radioButtons.getToggles().addAll(mother, father);
        Label lowerBoundLabel = new Label("lower bound:");
        Label higherBoundLabel = new Label("higher bound:");

        TextField lowerBound = new TextField();
        TextField higherBound = new TextField();

        Button deleteButton = new Button("delete");

        GridPane grid = new GridPane();
        grid.add(mother, 0, 0);
        grid.add(father, 1, 0);
        grid.add(lowerBoundLabel, 0, 1);
        grid.add(lowerBound, 1, 1);
        grid.add(higherBoundLabel, 0, 2);
        grid.add(higherBound, 1, 2);
        grid.add(deleteButton, 2, 2);

        box.getChildren().add(grid);
        disableButtons();
        deleteDialog.setHeight(300);

        deleteButton.setOnAction(event -> {
            if (radioButtons.getSelectedToggle() == null) {
                // алерт
            } else {
                RadioButton selected = (RadioButton) radioButtons.getSelectedToggle();
                setStudents(controller.deleteByParentsIncome(selected.getText(), lowerBound.getText(), higherBound.getText()));
                showAlert();
            }
        });
    }

    private void disableButtons() {
        deleteByStudentNameButton.setDisable(true);
        deleteByParentNameButton.setDisable(true);
        deleteByNumberOfSiblingsButton.setDisable(true);
        deleteByParentIncomeButton.setDisable(true);
    }

    private void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Deletion!");
        alert.setHeaderText("Hey");
        if (students.isEmpty()) {
            alert.setContentText("Nothing found");
        } else {
            alert.setContentText(students.size() + " students have been deleted just now.");
//            paginator.refreshPages();
        }

        alert.showAndWait();
        deleteDialog.close();
    }

}