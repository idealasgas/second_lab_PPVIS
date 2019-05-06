import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Paginator {
    private MainModel model;
    private ArrayList<ArrayList<Student>> pages = new ArrayList<>();
    private TableView<Student> table;
    private int currentPage;

    public Paginator(MainModel model) {
        this.model = model;
        for (int i = 0; i < model.studentArrayList.size(); i += 10) {
            pages.add(new ArrayList<>(model.studentArrayList.subList(i, Math.min(model.studentArrayList.size(), i + 10))));
        }
        this.currentPage = 0;
    }

    public VBox getView() {
        table  = new Table().getTable(getPage(0));

        Button next = new Button("next");
        Button previous = new Button("previous");
        Button first = new Button("first");
        Button last = new Button("last");
        Label currentPageLabel = new Label("1");

        GridPane grid = new GridPane();
        grid.add(first, 0, 0);
        grid.add(previous, 1, 0);
        grid.add(currentPageLabel, 2, 0);
        grid.add(next, 3, 0);
        grid.add(last, 4, 0);

        first.setOnAction(event -> {
            table.setItems(getPage(0));
            currentPage = 0;
            currentPageLabel.setText(Integer.toString(currentPage + 1));

        });

        previous.setOnAction(event -> {
            if (currentPage == 0) {
            } else {
                table.setItems(getPage(currentPage - 1));
                currentPage -= 1;
                currentPageLabel.setText(Integer.toString(currentPage + 1));
            }
        });

        next.setOnAction(event -> {
            if (currentPage == pages.size() - 1) {

            } else {
                table.setItems(getPage(currentPage + 1));
                currentPage += 1;
                currentPageLabel.setText(Integer.toString(currentPage + 1));
            }
        });

        last.setOnAction(event -> {
            table.setItems(getPage(pages.size() - 1));
            currentPageLabel.setText(Integer.toString(pages.size()));
        });

        VBox container = new VBox();
        container.getChildren().addAll(table, grid);
        return container;
    }

    private ObservableList<Student> getPage(int index) {
        ObservableList<Student> page = FXCollections.observableArrayList(pages.get(index));
        return page;
    }

    public void refreshPages() {
        pages.clear();
        for (int i = 0; i < model.studentArrayList.size(); i += 10) {
            pages.add(new ArrayList<>(model.studentArrayList.subList(i, Math.min(model.studentArrayList.size(), i + 10))));
        }
        currentPage = 0;
        table.setItems(FXCollections.observableArrayList(pages.get(0)));
    }
}
