import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class SearchView {
    private Button searchByStudentNameButton;
    private Button searchByParentNameButton;
    private Button searchByNumberOfSiblingsButton;
    private Button searchByParentIncomeButton;
    private VBox box;
    private Dialog searchDialog;
    private StudentsController controller;

    SearchView(StudentsController controller) {
        this.controller = controller;
    }

    public Dialog getDialog() {
        searchDialog = new Dialog();

        searchDialog.setTitle("Search for a student");
        searchDialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);

        box = new VBox();
        box.setPadding(new Insets(20, 150, 10, 10));

        searchByStudentNameButton = new Button("search by student's name");
        searchByParentNameButton = new Button("search by parent's name");
        searchByNumberOfSiblingsButton = new Button("search by number of siblings");
        searchByParentIncomeButton = new Button("search by income");

        HBox buttons = new HBox();

        buttons.getChildren().addAll(searchByStudentNameButton, searchByParentNameButton, searchByNumberOfSiblingsButton, searchByParentIncomeButton);
        box.getChildren().add(buttons);

        searchByStudentNameButton.setOnAction(event -> searchByName("student"));
        searchByParentNameButton.setOnAction(event -> searchByName("parent"));
        searchByNumberOfSiblingsButton.setOnAction(event -> searchByNumberOfSiblings());
        searchByParentIncomeButton.setOnAction(event -> searchByParentIncome());

        searchDialog.getDialogPane().setContent(box);
        return searchDialog;
    }

    private void disableButtons() {
        searchByNumberOfSiblingsButton.setDisable(true);
        searchByParentIncomeButton.setDisable(true);
        searchByParentNameButton.setDisable(true);
        searchByStudentNameButton.setDisable(true);
    }

    private void searchByName(String type) {
        Label firstNameLabel = new Label("first name:");
        Label secondNameLabel = new Label("second name:");
        Label surnameLabel = new Label("surname:");

        TextField firstName = new TextField();
        TextField secondName = new TextField();
        TextField surname = new TextField();

        Button searchButton = new Button("search");

        GridPane grid = new GridPane();
        grid.add(firstNameLabel, 0, 0);
        grid.add(firstName, 1, 0);
        grid.add(secondNameLabel, 0, 1);
        grid.add(secondName, 1, 1);
        grid.add(surnameLabel, 0, 2);
        grid.add(surname, 1, 2);
        grid.add(searchButton, 1, 3);
        ToggleGroup radioButtons = new ToggleGroup();


        if (type.equals("parent")) {
            RadioButton mother = new RadioButton("mother");
            RadioButton father = new RadioButton("father");

            radioButtons.getToggles().addAll(mother, father);
            grid.add(mother, 0, 4);
            grid.add(father, 1, 4);
        }

        searchDialog.setHeight(400);
        box.getChildren().add(grid);

        disableButtons();

        searchButton.setOnAction(event -> {
            if (type.equals("student")) {
                ArrayList<Student> studentsForTable = controller.searchByStudentName(firstName.getText(), secondName.getText(), surname.getText());
                showResultsTable(studentsForTable);

            }
            if (type.equals("parent")) {
                if (radioButtons.getSelectedToggle() == null) {
                    // алерт
                } else {
                    RadioButton selected = (RadioButton) radioButtons.getSelectedToggle();
                    ArrayList<Student> studentsForTable = controller.searchByParentsName(selected.getText(), firstName.getText(), secondName.getText(), surname.getText());
                    showResultsTable(studentsForTable);
                }
            }
            searchDialog.setHeight(500);
            searchDialog.setWidth(1200);
        });
    }

    private void searchByNumberOfSiblings() {
        ToggleGroup radioButtons = new ToggleGroup();
        Button searchButton = new Button("search");
        RadioButton sisters = new RadioButton("sisters");
        RadioButton brothers = new RadioButton("brothers");

        radioButtons.getToggles().addAll(sisters, brothers);

        TextField number = new TextField();
        setNumericInput(number);
        HBox container = new HBox();
        container.setSpacing(10);
        container.getChildren().addAll(sisters, brothers, number, searchButton);

        box.getChildren().add(container);
        disableButtons();

        searchButton.setOnAction(event -> {
            if (radioButtons.getSelectedToggle() == null) {
                // алерт
            } else {
                RadioButton selected = (RadioButton) radioButtons.getSelectedToggle();
                ArrayList<Student> studentsForTable = controller.searchByNumberOfSiblings(selected.getText(), number.getText());
                showResultsTable(studentsForTable);
                searchDialog.setHeight(500);
                searchDialog.setWidth(1200);
            }
        });
    }

    private void searchByParentIncome() {
        ToggleGroup radioButtons = new ToggleGroup();

        RadioButton mother = new RadioButton("mother");
        RadioButton father = new RadioButton("father");
        Button search = new Button("search");
        Label lowerBoundLabel = new Label("lower bound:");
        Label higherBoundLabel = new Label("higher bound:");
        TextField lowerBound = new TextField();
        setNumericInput(lowerBound);
        TextField higherBound = new TextField();
        setNumericInput(higherBound);

        GridPane grid = new GridPane();
        grid.add(mother, 0, 0);
        grid.add(father, 1, 0);
        grid.add(lowerBoundLabel, 0, 1);
        grid.add(lowerBound, 1, 1);
        grid.add(higherBoundLabel, 0, 2);
        grid.add(higherBound, 1, 2);
        grid.add(search, 1, 3);

        radioButtons.getToggles().addAll(mother, father);

        search.setOnAction(event -> {
            if (radioButtons.getSelectedToggle() == null) {
                // алерт
            } else {
                RadioButton selected = (RadioButton) radioButtons.getSelectedToggle();
                ArrayList<Student> studentsForTable = controller.searchByParentsIncome(selected.getText(), lowerBound.getText(), higherBound.getText());
                showResultsTable(studentsForTable);
            }
        });

        box.getChildren().add(grid);
        disableButtons();
        searchDialog.setHeight(500);
        searchDialog.setWidth(1200);
    }

    private void showResultsTable(ArrayList<Student> studentArrayList) {
        if (studentArrayList.size() == 0) {
            Text message = new Text("Nothing found.");
            box.getChildren().add(message);
        } else {
            Paginator paginator = new Paginator(studentArrayList);
            box.getChildren().add(paginator.getView());
        }
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
