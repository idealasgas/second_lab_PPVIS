import javafx.application.Application;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

public class MainWindow extends Application {
    public static void main(String[] args) {
        launch(args);
    }

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

        TableView<Student> table = new TableView<>();
        ObservableList<Student> students = FXCollections.observableArrayList(new SAXExample().getStudents());
        table.setItems(students);
        TableColumn<Student, String> studentsNameColumn = new TableColumn<>("Student's name");
        TableColumn<Student, String> fathersNameColumn = new TableColumn<>("Father's name");
        TableColumn<Student, String> fathersIncomeColumn = new TableColumn<>("Father's income");
        TableColumn<Student, String> mothersNameColumn = new TableColumn<>("Mother's name");
        TableColumn<Student, String> mothersIncomeColumn = new TableColumn<>("Mother's income");

        studentsNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFullName()));
        fathersNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFather().getFullName()));
        fathersIncomeColumn.setCellValueFactory(param -> new SimpleStringProperty(Integer.toString(param.getValue().getFather().getIncome())));
        mothersNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getMother().getFullName()));
        mothersIncomeColumn.setCellValueFactory(param -> new SimpleStringProperty(Integer.toString(param.getValue().getMother().getIncome())));

        table.getColumns().setAll(studentsNameColumn, fathersNameColumn, fathersIncomeColumn, mothersNameColumn, mothersIncomeColumn);

        toolBar.getItems().addAll(add, remove, search);
        VBox vBox = new VBox(toolBar, table);

        return vBox;
    }
}
