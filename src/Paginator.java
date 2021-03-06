import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Paginator {
    private List<List<Student>> pages = new ArrayList<>();
    private TableView<Student> table;
    private int currentPage;
    private int recordsOnPage;
    private Label currentPageLabel;
    private Label totalRecordsLabel;
    private List<Student> studentArrayList;
    private VBox container;

    public Paginator(List<Student> arrayListOfStudents) {
        if (arrayListOfStudents.isEmpty()) {

        } else {
            for (int i = 0; i < arrayListOfStudents.size(); i += 10) {
                pages.add(arrayListOfStudents.subList(i, Math.min(arrayListOfStudents.size(), i + 10)));
            }
        }
        this.studentArrayList = arrayListOfStudents;
        this.currentPage = 0;
    }

    public VBox getView() {
        if (studentArrayList.isEmpty()) {
            table = new TableView<>();
        } else {
            table = getTable(getPage(0));
        }

        Button next = new Button("next");
        Button previous = new Button("previous");
        Button first = new Button("first");
        Button last = new Button("last");
        currentPageLabel = new Label("1/" + (pages.size()));
        totalRecordsLabel = new Label(Integer.toString(studentArrayList.size()));
        TextField recordsOnPage = new TextField();
        Button setRecordsOnPageButton = new Button("set");
        Label recordsOnPageLabel = new Label("records on page:");
        recordsOnPage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    recordsOnPage.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        GridPane grid = new GridPane();
        grid.add(first, 0, 0);
        grid.add(previous, 1, 0);
        grid.add(currentPageLabel, 2, 0);
        grid.add(next, 3, 0);
        grid.add(last, 4, 0);
        grid.add(recordsOnPageLabel, 6, 0);
        grid.add(recordsOnPage, 7, 0);
        grid.add(setRecordsOnPageButton, 8,  0);
        grid.add(totalRecordsLabel, 9, 0);

        setButton(first);
        setButton(previous);
        setButton(next);
        setButton(last);
        setButton(setRecordsOnPageButton);

        recordsOnPage.setMaxWidth(Double.MAX_VALUE);
        recordsOnPage.setMaxHeight(Double.MAX_VALUE);
        GridPane.setHgrow(recordsOnPage, Priority.ALWAYS);
        GridPane.setVgrow(recordsOnPage, Priority.ALWAYS);
        GridPane.setMargin(recordsOnPage, new Insets(10, 0, 10, 60));



        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column3 = new ColumnConstraints();
        ColumnConstraints column4 = new ColumnConstraints();
        ColumnConstraints column5 = new ColumnConstraints();
        ColumnConstraints column6 = new ColumnConstraints();
        ColumnConstraints column7 = new ColumnConstraints();
        ColumnConstraints column8 = new ColumnConstraints();
        ColumnConstraints column9 = new ColumnConstraints();

        column1.setPercentWidth(10);
        column2.setPercentWidth(10);
        column3.setPercentWidth(3);
        column4.setPercentWidth(10);
        column5.setPercentWidth(10);
        column6.setPercentWidth(10);
        column7.setPercentWidth(10);
        column8.setPercentWidth(10);
        column9.setPercentWidth(10);

        grid.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9);


        grid.getColumnConstraints().add(new ColumnConstraints(80));

        setRecordsOnPageButton.setOnAction(event -> refreshPages(Integer.parseInt(recordsOnPage.getText())));

        first.setOnAction(event -> {
            table.setItems(getPage(0));
            currentPage = 0;
            currentPageLabel.setText((currentPage + 1) + "/" + pages.size());

        });

        previous.setOnAction(event -> {
            if (currentPage == 0) {
            } else {
                table.setItems(getPage(currentPage - 1));
                currentPage -= 1;
                currentPageLabel.setText((currentPage + 1) + "/" + pages.size());
            }
        });

        next.setOnAction(event -> {
            if (currentPage == pages.size() - 1) {

            } else {
                table.setItems(getPage(currentPage + 1));
                currentPage += 1;
                currentPageLabel.setText((currentPage + 1) + "/" + pages.size());
            }
        });

        last.setOnAction(event -> {
            table.setItems(getPage(pages.size() - 1));
            currentPage = pages.size() - 1;
            currentPageLabel.setText((pages.size()) + "/" + pages.size());
        });

        container = new VBox();
        container.getChildren().addAll(table, grid);
        return container;
    }

    private ObservableList<Student> getPage(int index) {
        ObservableList<Student> page = FXCollections.observableArrayList(pages.get(index));
        return page;
    }

    public void refreshPages(int recordsOnPage) {
        this.recordsOnPage = recordsOnPage;
        refreshPages();
    }

    public void refreshPages() {
        if (studentArrayList.size() == 1) {
            for (int i = 0; i < studentArrayList.size(); i += recordsOnPage) {
                pages.add(studentArrayList.subList(i, Math.min(studentArrayList.size(), i + recordsOnPage)));
            }
            table = getTable(getPage(0));
            container.getChildren().remove(0);
            container.getChildren().add(0, table);
        } else {
            pages.clear();
            for (int i = 0; i < studentArrayList.size(); i += recordsOnPage) {
                pages.add(studentArrayList.subList(i, Math.min(studentArrayList.size(), i + recordsOnPage)));
            }
        }

        currentPage = 0;
        table.setItems(getPage(0));
        currentPageLabel.setText("1/" + pages.size());
        totalRecordsLabel.setText(Integer.toString(studentArrayList.size()));
    }

    public void refreshPages(List<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
        recordsOnPage = 10;
        refreshPages();
    }

    private void setButton(Button button) {
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);
        GridPane.setMargin(button, new Insets(10));
    }

    private TableView getTable(ObservableList<Student> students) {
        TableView<Student> table = new TableView<>();
        table.setItems(students);
        TableColumn<Student, String> studentsNameColumn = new TableColumn<>("Student's name");
        TableColumn<Student, String> fathersNameColumn = new TableColumn<>("Father's name");
        TableColumn<Student, String> fathersIncomeColumn = new TableColumn<>("Father's income");
        TableColumn<Student, String> mothersNameColumn = new TableColumn<>("Mother's name");
        TableColumn<Student, String> mothersIncomeColumn = new TableColumn<>("Mother's income");
        TableColumn<Student, String> brothersColumn = new TableColumn<>("Brothers");
        TableColumn<Student, String> sistersColumn = new TableColumn<>("Sisters");


        studentsNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFirstName() + " " + param.getValue().getSecondName() + " " + param.getValue().getSurname()));
        fathersNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFather().getFirstName() + " " + param.getValue().getFather().getSecondName() + " " + param.getValue().getFather().getSurname()));
        fathersIncomeColumn.setCellValueFactory(param -> new SimpleStringProperty(Integer.toString(param.getValue().getFather().getIncome())));
        mothersNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getMother().getFirstName() + " " + param.getValue().getMother().getSecondName() + " " + param.getValue().getMother().getSurname()));
        mothersIncomeColumn.setCellValueFactory(param -> new SimpleStringProperty(Integer.toString(param.getValue().getMother().getIncome())));
        brothersColumn.setCellValueFactory(param -> new SimpleStringProperty(Integer.toString(param.getValue().getBrothers())));
        sistersColumn.setCellValueFactory(param -> new SimpleStringProperty(Integer.toString(param.getValue().getSisters())));

        table.getColumns().setAll(studentsNameColumn, fathersNameColumn, fathersIncomeColumn, mothersNameColumn, mothersIncomeColumn, brothersColumn, sistersColumn);
        return table;
    }
}
