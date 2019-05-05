import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

        // добавить флаг, который будет передаваться в контроллер чтобы понимать по чему искать
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
                ObservableList<Student> studentsForTable = FXCollections.observableArrayList(controller.searchByStudentName(firstName.getText(), secondName.getText(), surname.getText()));
                TableView searchTable = new Table().getTable(studentsForTable);
                box.getChildren().add(searchTable);
            }
            if (type.equals("parent")) {
                if (radioButtons.getSelectedToggle() == null) {
                    // алерт
                } else {
                    RadioButton selected = (RadioButton) radioButtons.getSelectedToggle();
                    ObservableList<Student> studentsForTable = FXCollections.observableArrayList(controller.searchByParentsName(selected.getText(), firstName.getText(), secondName.getText(), surname.getText()));
                    TableView searchTable = new Table().getTable(studentsForTable);
                    box.getChildren().add(searchTable);
                }
            }
        });
    }

    private void searchByNumberOfSiblings() {
        ToggleGroup radioButtons = new ToggleGroup();
        Button searchButton = new Button("search");
        RadioButton sisters = new RadioButton("sisters");
        RadioButton brothers = new RadioButton("brothers");

        radioButtons.getToggles().addAll(sisters, brothers);

        TextField number = new TextField();
        HBox container = new HBox();
        container.setSpacing(10);
        container.getChildren().addAll(sisters, brothers, number, searchButton);

        box.getChildren().add(container);
        disableButtons();

        searchButton.setOnAction(event -> {
            if (radioButtons.getSelectedToggle() == null) {
                // алерт
            } else {
//                RadioButton selected = (RadioButton) radioButtons.getSelectedToggle();
//                TableView<Student> searchTable = new ViewTable().getTable(search.searchByNumberOfSiblings(selected.getText(), number.getText()));
//                box.getChildren().add(searchTable);
//                searchDialog.setHeight(300);
            }
        });
    }

    private void searchByParentIncome() {
        ToggleGroup radioButtons = new ToggleGroup();

        RadioButton mother = new RadioButton("mother");
        RadioButton father = new RadioButton("father");

        radioButtons.getToggles().addAll(mother, father);

        TextField number = new TextField();

        HBox container = new HBox();
        container.setSpacing(10);
        container.getChildren().addAll(mother, father, number);

        box.getChildren().add(container);
        disableButtons();
        searchDialog.setHeight(200);
    }
}
