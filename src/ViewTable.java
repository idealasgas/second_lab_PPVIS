import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class ViewTable {
    public TableView<Student> getTable(ArrayList<Student> arrayList) {
        TableView<Student> table = new TableView<>();
        ObservableList<Student> students = FXCollections.observableArrayList(arrayList);
        table.setItems(students);

        TableColumn<Student, String> studentsNameColumn = new TableColumn<>("Student's name");
        TableColumn<Student, String> fathersNameColumn = new TableColumn<>("Father's name");
        TableColumn<Student, String> fathersIncomeColumn = new TableColumn<>("Father's income");
        TableColumn<Student, String> mothersNameColumn = new TableColumn<>("Mother's name");
        TableColumn<Student, String> mothersIncomeColumn = new TableColumn<>("Mother's income");
        TableColumn<Student, String> brothersColumn = new TableColumn<>("Brothers");
        TableColumn<Student, String> sistersColumn = new TableColumn<>("Sisters");


        studentsNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFullName()));
        fathersNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFather().getFullName()));
        fathersIncomeColumn.setCellValueFactory(param -> new SimpleStringProperty(Integer.toString(param.getValue().getFather().getIncome())));
        mothersNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getMother().getFullName()));
        mothersIncomeColumn.setCellValueFactory(param -> new SimpleStringProperty(Integer.toString(param.getValue().getMother().getIncome())));
        brothersColumn.setCellValueFactory(param -> new SimpleStringProperty(Integer.toString(param.getValue().getBrothers())));
        sistersColumn.setCellValueFactory(param -> new SimpleStringProperty(Integer.toString(param.getValue().getSisters())));

        table.getColumns().setAll(studentsNameColumn, fathersNameColumn, fathersIncomeColumn, mothersNameColumn, mothersIncomeColumn, brothersColumn, sistersColumn);
        return table;
    }
}
