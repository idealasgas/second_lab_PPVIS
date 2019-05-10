import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainWindow extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    private MainModel model;
    private StudentsController studentsController;
    private Paginator paginator;


    public void start(Stage primaryStage) throws ParserConfigurationException, SAXException, IOException {
        primaryStage.setTitle("лабораторная 2");

        ToolBar toolBar = new ToolBar();
        Button add = new Button("Add");
        Button remove = new Button("Remove");
        Button search = new Button("Search");
        Button download = new Button("Download");
        Button upload = new Button("Upload");
        final FileChooser fileChooser = new FileChooser();


        add.setOnAction(event -> onAddButton(studentsController));
        search.setOnAction(event -> onSearchButton(studentsController));
        remove.setOnAction(event -> onDeleteButton(studentsController));
        download.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            new DOMExample().createNewFile(file, model.getStudentArrayList());
        });

        fileChooser.setInitialDirectory(new File("D:/Документы/универ/ППвИС/labs_1_sem/second_lab_ppvis"));
        toolBar.getItems().addAll(add, remove, search, download, upload);
        VBox vBox = new VBox(toolBar);

        MenuBar menuBar = new MenuBar();

        Menu menuActions = new Menu("Actions");
        Menu menuFile = new Menu("File");

        MenuItem downloadMenu = new MenuItem("Save");
        downloadMenu.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            new DOMExample().createNewFile(file, model.getStudentArrayList());
        });

        MenuItem uploadMenu = new MenuItem("Open");
        uploadMenu.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                try {
                    ArrayList<Student> studentArrayList = new SAXExample().getStudents(file);
                    model = new MainModel(studentArrayList);
                    paginator = new Paginator(model.getStudentArrayList());
                    studentsController = new StudentsController(model, paginator);
                    vBox.getChildren().add(paginator.getView());

                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
            }
        });

        upload.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                try {
                    ArrayList<Student> studentArrayList = new SAXExample().getStudents(file);
                    model = new MainModel(studentArrayList);
                    paginator = new Paginator(model.getStudentArrayList());
                    studentsController = new StudentsController(model, paginator);
                    vBox.getChildren().add(paginator.getView());

                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
            }
        });

        menuFile.getItems().addAll(uploadMenu, downloadMenu);

        MenuItem addMenu = new MenuItem("Add student");
        addMenu.setOnAction(event -> onAddButton(studentsController));
        MenuItem searchMenu = new MenuItem("Search for a student");
        searchMenu.setOnAction(event -> onSearchButton(studentsController));
        MenuItem removeMenu = new MenuItem("Remove a student");
        removeMenu.setOnAction(event -> onDeleteButton(studentsController));

        menuActions.getItems().addAll(addMenu, searchMenu, removeMenu);

        menuBar.getMenus().addAll(menuFile, menuActions);

        Scene scene = new Scene(vBox, 1120, 700);

        ((VBox) scene.getRoot()).getChildren().add(0, menuBar);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void onAddButton(StudentsController controller) {
        new AddView(controller).getDialog().showAndWait();
    }

    private void onSearchButton(StudentsController controller){
        new SearchView(controller).getDialog().showAndWait();
    }

    private void onDeleteButton(StudentsController controller) {
        new DeleteView(controller).getDialog().showAndWait();
    }
}
