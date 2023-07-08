package hust.soict.cysec.oop.application;

import java.util.Arrays;
import java.util.List;

import hust.soict.cysec.oop.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class MainController {
	@FXML
    private VBox dynastyItem;

    @FXML
    private VBox eventItem;

    @FXML
    private VBox festivalItem;

    @FXML
    private VBox figureItem;
    
    @FXML
    private VBox kingItem;

    @FXML
    private VBox relicItem;

    @FXML
    private TextField searchInfo;

    @FXML
    private TableView<?> tableData;
    
    private VBox[] items;
    private VBox[] selectedItem;
    private ObservableList<Figure> figureList;
    private ObservableList<King> kingList;
    private ObservableList<Relic> relicList;
    private ObservableList<HistoricalEvent> eventList;
    private ObservableList<Dynasty> dynastyList;
    private ObservableList<Festival> festivalList;
    
    private TableView<Figure> figureTable = new TableView<>();
    private TableView<King> kingTable = new TableView<>();
    private TableView<Relic> relicTable = new TableView<>();
    private TableView<HistoricalEvent> eventTable = new TableView<>();
    private TableView<Dynasty> dynastyTable = new TableView<>();
    private TableView<Festival> festivalTable = new TableView<>();
    
    private List<String> figureTableFieldName = Arrays.asList("Tên", "Năm sinh", "Năm mất", "Bí danh", "Quê quán", "Ghi chú");
    private List<String> figureTableFieldProperty = Arrays.asList("name", "birth", "death", "alias", "hometown", "note");
    private List<String> kingTableFieldName = Arrays.asList("Tên", "Năm sinh", "Năm mất", "Miếu hiệu", "Thụy hiệu", "Niên hiệu", "Tên húy", "Thế thứ", "Năm trị vì", "Tiền nhiệm", "Kế nhiệm");
    private List<String> kingTableFieldProperty = Arrays.asList("name", "birth", "death", "templateName", "posthumousName", "eraName", "courtesyName", "successionOrder", "reignYear", "predecessor", "successor");
    private List<String> relicTableFieldName = Arrays.asList("Tên", "Địa điểm", "Loại hình","Xếp hạng", "Mô tả");
    private List<String> relicTableFieldProperty = Arrays.asList("name", "location", "type", "rank", "desc");
    private List<String> eventTableFieldName = Arrays.asList("Tên", "Bắt đầu", "Kết thúc", "Mô tả");
    private List<String> eventTableFieldProperty = Arrays.asList("name", "startYear", "endYear", "desc");
    private List<String> dynastyTableFieldName = Arrays.asList("Tên", "Bắt đầu", "Kết thúc", "Thủ đô");
    private List<String> dynastyTableFieldProperty = Arrays.asList("name", "startYear", "endYear", "capital");
    private List<String> festivalTableFieldName = Arrays.asList("Tên", "Địa điểm", "Thời gian", "Mô tả");
    private List<String> festivalTableFieldProperty = Arrays.asList("name", "location", "time", "desc");
    
    @SuppressWarnings("unchecked")
    @FXML
    private void initialize() {
    	// Data
    	MainModel model = new MainModel();
    	figureList = FXCollections.observableArrayList(model.getFigures());
    	kingList = FXCollections.observableArrayList(model.getKings());
    	relicList = FXCollections.observableArrayList(model.getRelics());
    	eventList = FXCollections.observableArrayList(model.getEvents());
    	dynastyList = FXCollections.observableArrayList(model.getDynasties());
    	festivalList = FXCollections.observableArrayList(model.getFestivals());
    	
    	tableData.getColumns().clear();
    	settingTable();
    }
    
    private <T> void settingTable(TableView<T> table, ObservableList<T> data, List<String> columnName, List<String> columnProperty) {
    	table.setItems(data);
    	for (int i = 0; i < columnName.size(); ++i) {
    		TableColumn<T, ?> column = new TableColumn<>(columnName.get(i));
    		column.prefWidthProperty().bind(tableData.widthProperty().multiply((1 - 0.1) / (columnName.size() - 1)));
    		column.setCellValueFactory(new PropertyValueFactory<>(columnProperty.get(i)));
    		table.getColumns().add(column);
    	}
    }
    
    
    @FXML
    void handleItemClicked(MouseEvent event) {

    }

    @FXML
    void pressEnter(KeyEvent event) {

    }
}
