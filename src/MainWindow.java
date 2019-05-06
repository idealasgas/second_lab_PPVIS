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


        add.setOnAction(event -> onAddButton(studentsController, paginator));
        search.setOnAction(event -> onSearchButton(studentsController));
        remove.setOnAction(event -> onDeleteButton(studentsController, paginator));

        toolBar.getItems().addAll(add, remove, search);
        VBox vBox = new VBox(toolBar, paginator.getView());

        return vBox;
    }

    private void onAddButton(StudentsController controller, Paginator paginator) {
        new AddView(controller, paginator).getDialog().showAndWait();
    }

    private void onSearchButton(StudentsController controller){
        new SearchView(controller).getDialog().showAndWait();
    }

    private void onDeleteButton(StudentsController controller, Paginator paginator) {
        new DeleteView(controller, paginator).getDialog().showAndWait();
    }
}
