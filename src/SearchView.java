import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchView {
    private Button searchByStudentNameButton;
    private Button searchByParentNameButton;
    private Button searchByNumberOfSiblingsButton;
    private Button searchByParentIncomeButton;
    private VBox box;
    private Dialog searchDialog;
    private Search search;

    SearchView(Search searchController) {
        this.search = searchController;
    }

    public Dialog getDialog() {
        searchDialog = new Dialog();

        searchDialog.setTitle("Search for a student");
//        ButtonType searchButtonType = new ButtonType("Search", ButtonBar.ButtonData.OK_DONE);
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
        searchByStudentNameButton.setOnAction(event -> searchByName());
        searchByParentNameButton.setOnAction(event -> searchByName());
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

    private void searchByName() {
        Label studentFirstNameLabel = new Label("first name:");
        Label studentSecondNameLabel = new Label("second name:");
        Label studentSurnameLabel = new Label("surname:");

        TextField studentFirstName = new TextField();
        TextField studentSecondName = new TextField();
        TextField studentSurname = new TextField();

        Button searchButton = new Button("search");
        searchButton.setOnAction(event -> {
            TableView searchTable = new ViewTable().getTable(search.searchByStudentName(studentFirstName.getText(), studentSecondName.getText(), studentSurname.getText()));
            HBox tableBox = new HBox();
            tableBox.getChildren().add(searchTable);
            box.getChildren().add(tableBox);
        });

        VBox labels = new VBox();
        VBox inputs = new VBox();
        HBox container = new HBox();

        labels.getChildren().addAll(studentSurnameLabel, studentFirstNameLabel, studentSecondNameLabel);
        inputs.getChildren().addAll(studentSurname, studentFirstName, studentSecondName);
        container.getChildren().addAll(labels, inputs, searchButton);

        searchDialog.setHeight(400);
        box.getChildren().add(container);

        disableButtons();
    }

    private void searchByNumberOfSiblings() {
        ToggleGroup radioButtons = new ToggleGroup();

        RadioButton sisters = new RadioButton("sisters");
        RadioButton brothers = new RadioButton("brothers");

        radioButtons.getToggles().addAll(sisters, brothers);

        TextField number = new TextField();

        HBox container = new HBox();
        container.setSpacing(10);
        container.getChildren().addAll(sisters, brothers, number);

        box.getChildren().add(container);
        disableButtons();
        searchDialog.setHeight(200);
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
