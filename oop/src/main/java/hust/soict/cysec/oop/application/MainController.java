package hust.soict.cysec.oop.application;

import java.util.Arrays;
import java.util.List;

import hust.soict.cysec.oop.model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private VBox relicItem;

    @FXML
    private TextField searchInfo;

    @FXML
    private TableView<?> tableData;
    
    private VBox[] items;
    private VBox[] selectedItem;
//    private ObservableList<HistoricalFigure> historicalFigureList;
    private ObservableList<Relic> relicList;
    private ObservableList<HistoricalEvent> historicalEventList;
    private ObservableList<Dynasty> dynastyList;
    private ObservableList<Festival> festivalList;
    
//    private TableView<HistoricalFigure> historicalFigureTable = new TableView<>();
    private TableView<Relic> relicTable = new TableView<>();
    private TableView<HistoricalEvent> historicalEventTable = new TableView<>();
    private TableView<Dynasty> dynastyTable = new TableView<>();
    private TableView<Festival> festivalTable = new TableView<>();
    
    private List<String> relicTableFieldName = Arrays.asList("Tên", "Địa điểm", "Loại hình","Xếp hạng", "Mô tả");
    private List<String> relicTableFieldProperty = Arrays.asList("name", "location", "type", "rank", "desc");
    private List<String> historicalEventTableFieldName = Arrays.asList("Tên", "Bắt đầu", "Kết thúc", "Mô tả");
    private List<String> historicalEventTableFieldProperty = Arrays.asList("name", "startYear", "endYear", "desc");
    private List<String> dynastyTableFieldName = Arrays.asList("Tên", "Bắt đầu", "Kết thúc", "Thủ đô");
    private List<String> dynastyTableFieldProperty = Arrays.asList("name", "startYear", "endYear", "capital");
    private List<String> festivalTableFieldName = Arrays.asList("Tên", "Địa điểm", "Thời gian", "Mô tả");
    private List<String> festivalTableFieldProperty = Arrays.asList("name", "location", "time", "desc");
    
    @SuppressWarnings("unchecked")
    @FXML
    private void initialize() {
    	
    }
    
    
    @FXML
    void handleItemClicked(MouseEvent event) {

    }

    @FXML
    void pressEnter(KeyEvent event) {

    }
}
