import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Paginator {
    private MainModel model;
    private ArrayList<ArrayList<Student>> pages = new ArrayList<>();
    private TableView<Student> table;
    private int currentPage;
    private int recordsOnPage;
    private Label currentPageLabel;
    private ArrayList<Student> studentArrayList;;

    public Paginator(MainModel model) {
        this.model = model;
        this.recordsOnPage = 10;
        for (int i = 0; i < model.studentArrayList.size(); i += recordsOnPage) {
            pages.add(new ArrayList<>(model.studentArrayList.subList(i, Math.min(model.studentArrayList.size(), i + 10))));
        }
        this.currentPage = 0;
    }

    public Paginator(ArrayList<Student> arrayListOfStudents) {
        this.studentArrayList = arrayListOfStudents;
        for (int i = 0; i < arrayListOfStudents.size(); i += 10) {
            pages.add(new ArrayList<>(arrayListOfStudents.subList(i, Math.min(arrayListOfStudents.size(), i + 10))));
        }
        this.currentPage = 0;
    }

    public VBox getView() {
        table  = new Table().getTable(getPage(0));

        Button next = new Button("next");
        Button previous = new Button("previous");
        Button first = new Button("first");
        Button last = new Button("last");
        currentPageLabel = new Label("1/" + (pages.size()));
        TextField recordsOnPage = new TextField();
        Button setRecordsOnPageButton = new Button("set");
        Label recordsOnPageLabel = new Label("records on page:");

        GridPane grid = new GridPane();
        grid.add(first, 0, 0);
        grid.add(previous, 1, 0);
        grid.add(currentPageLabel, 2, 0);
        grid.add(next, 3, 0);
        grid.add(last, 4, 0);
        grid.add(recordsOnPageLabel, 6, 0);
        grid.add(recordsOnPage, 7, 0);
        grid.add(setRecordsOnPageButton, 8,  0);

        setRecordsOnPageButton.setOnAction(event -> {
            if (model == null) {
                refreshPagesOnSearch(Integer.parseInt(recordsOnPage.getText()));
            } else {
                refreshPages(Integer.parseInt(recordsOnPage.getText()));
            }
        });

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

        VBox container = new VBox();
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
        pages.clear();
        for (int i = 0; i < model.studentArrayList.size(); i += recordsOnPage) {
            pages.add(new ArrayList<>(model.studentArrayList.subList(i, Math.min(model.studentArrayList.size(), i + recordsOnPage))));
        }
        currentPage = 0;
        table.setItems(FXCollections.observableArrayList(pages.get(0)));
        currentPageLabel.setText("1/" + pages.size());
    }

    public void refreshPagesOnSearch(int recordsOnPage) {
        this.recordsOnPage = recordsOnPage;
        refreshPagesOnSearch();
    }

    public void refreshPagesOnSearch() {
        pages.clear();
        for (int i = 0; i < studentArrayList.size(); i += recordsOnPage) {
            pages.add(new ArrayList<>(studentArrayList.subList(i, Math.min(studentArrayList.size(), i + recordsOnPage))));
        }
        currentPage = 0;
        table.setItems(FXCollections.observableArrayList(pages.get(0)));
        currentPageLabel.setText("1/" + pages.size());
    }
}
