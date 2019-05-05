import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.image.AreaAveragingScaleFilter;
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

    DeleteView(StudentsController controller) {
        this.controller = controller;
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
//            getAlert();
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

    private void disableButtons() {
        deleteByStudentNameButton.setDisable(true);
        deleteByParentNameButton.setDisable(true);
        deleteByNumberOfSiblingsButton.setDisable(true);
        deleteByParentIncomeButton.setDisable(true);
    }

    private void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

}