import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class MainWindow extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    private MainModel model;


    public void start(Stage primaryStage) throws ParserConfigurationException, SAXException, IOException {
        primaryStage.setTitle("лабораторная 2");

        Scene scene = new Scene(getView(), 680, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public VBox getView() throws ParserConfigurationException, SAXException, IOException {
        ToolBar toolBar = new ToolBar();
        Button add = new Button("Add");
        Button remove = new Button("Remove");
        Button search = new Button("Search");


        ArrayList<Student> studentArrayList = new SAXExample().getStudents();
        ObservableList<Student> students = FXCollections.observableArrayList(studentArrayList);
        model = new MainModel(studentArrayList, students);
        StudentsController studentsController = new StudentsController(model);
        Paginator paginator = new Paginator(model);


        add.setOnAction(event -> onAddButton());
        search.setOnAction(event -> onSearchButton(studentsController, model));
        remove.setOnAction(event -> onDeleteButton(studentsController, model));

        toolBar.getItems().addAll(add, remove, search);
        VBox vBox = new VBox(toolBar, paginator.getView());

        return vBox;
    }

    private void onAddButton() {

        Dialog addDialog = new Dialog();

        addDialog.setTitle("Add student");
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        addDialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField studentFirstName = new TextField();
        TextField studentSecondName = new TextField();
        TextField studentSurname = new TextField();
        TextField brothers = new TextField();
        TextField sisters = new TextField();

        TextField fathersFirstName = new TextField();
        TextField fathersSecondName = new TextField();
        TextField fathersSurname = new TextField();
        TextField fathersIncome = new TextField();

        TextField mothersFirstName = new TextField();
        TextField mothersSecondName = new TextField();
        TextField mothersSurname = new TextField();
        TextField mothersIncome = new TextField();

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

        // переделать грид, шоб не так страшно было

        grid.add(studentFirstNameLabel, 0, 0);
        grid.add(studentFirstName, 1, 0);
        grid.add(studentSecondNameLabel, 0, 1);
        grid.add(studentSecondName, 1, 1);
        grid.add(studentSurnameLabel, 0, 2);
        grid.add(studentSurname, 1, 2);

        // это не текст поля, а для циферок
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
                System.out.println("works");
                // добавить валидацию, если поля пустые
                Student newStudent = new Student(studentFirstName.getText(), studentSecondName.getText(),
                        studentSurname.getText(), Integer.parseInt(sisters.getText()), Integer.parseInt(brothers.getText()));
                Parent mother = new Parent(Integer.parseInt(mothersIncome.getText()), mothersFirstName.getText(),
                        mothersSecondName.getText(), mothersSurname.getText());
                Parent father = new Parent(Integer.parseInt(fathersIncome.getText()), fathersFirstName.getText(),
                        fathersSecondName.getText(), fathersSurname.getText());
                newStudent.setMother(mother);
                newStudent.setFather(father);

                model.students.add(newStudent);

                 DOMExample dom = new DOMExample();
                 dom.addRecord(studentFirstName.getText(), studentSecondName.getText(), studentSurname.getText(),
                         mothersFirstName.getText(), mothersSecondName.getText(), mothersSurname.getText(),
                         fathersFirstName.getText(), fathersSecondName.getText(), fathersSurname.getText(),
                         Integer.parseInt(fathersIncome.getText()), Integer.parseInt(mothersIncome.getText()),
                         Integer.parseInt(brothers.getText()), Integer.parseInt(sisters.getText())
                         );

            }
            return null;
        });

        addDialog.showAndWait();




    }

    private void onSearchButton(StudentsController controller, MainModel model){
        new SearchView(controller).getDialog().showAndWait();
    }

    private void onDeleteButton(StudentsController controller, MainModel model) {
        new DeleteView(controller).getDialog().showAndWait();
    }
}
