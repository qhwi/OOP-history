package hust.soict.cysec.oop.application;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.lang.reflect.Field;

import hust.soict.cysec.oop.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.*;

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
    private VBox selectedItem;
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
    	settingTable(figureTable, figureList, figureTableFieldName, figureTableFieldProperty);
    	settingTable(kingTable, kingList, kingTableFieldName, kingTableFieldProperty);
    	settingTable(relicTable, relicList, relicTableFieldName, relicTableFieldProperty);
    	settingTable(eventTable, eventList, eventTableFieldName, eventTableFieldProperty);
    	settingTable(dynastyTable, dynastyList, dynastyTableFieldName, dynastyTableFieldProperty);
    	settingTable(festivalTable, festivalList, festivalTableFieldName, festivalTableFieldProperty);
    	
    	copyTable(figureTable, (TableView<Figure>) tableData);
    	
    	items = new VBox[] {figureItem, kingItem, relicItem, eventItem, dynastyItem, festivalItem};
    	selectedItem = figureItem;
    	for (VBox item: items) {
    		item.setStyle("-fx-cursor: hand");
    	}
    	selectedItem.setStyle("-fx-background-color: #ccc");
    	
    	setEventClickOnRow((TableView<Figure>) tableData);
    }
    
    // *****
    private <T> void settingTable(TableView<T> table, ObservableList<T> data, List<String> columnName, List<String> columnProperty) {
    	table.setItems(data);
    	for (int i = 0; i < columnName.size(); ++i) {
    		TableColumn<T, String> column = new TableColumn<T, String>(columnName.get(i));
    		column.prefWidthProperty().bind(tableData.widthProperty().multiply((1 - 0.1) / (columnName.size() - 1)));
    		column.setCellValueFactory(new PropertyValueFactory<T,String>(columnProperty.get(i)));
    		table.getColumns().add(column);
    	}
    }
    
    private <T> void copyTable(TableView<T> originalTable, TableView<T> newTable) {
    	newTable.setItems((ObservableList<T>) originalTable.getItems());
    	for (TableColumn<T, ?> column : originalTable.getColumns()) {
    		newTable.getColumns().add(column);
    	}
    	setEventClickOnRow((TableView<T>) tableData);
    }
    
    private <T> void setEventClickOnRow(TableView<T> mainTable) {
    	mainTable.setRowFactory(tv -> {
    		TableRow<T> row = new TableRow<>();
    		row.setOnMouseClicked(event -> {
    			if (event.getClickCount() == 2 && (!row.isEmpty())) {
    				T rowData = (T) row.getItem();
    				try {
    					if (rowData instanceof Figure) {
    						popupData((Figure) rowData, figureTableFieldName,
									figureTableFieldProperty, "Nhân vật lịch sử");
    					}
    					if (rowData instanceof King) {
    						popupData((King) rowData, kingTableFieldName,
									kingTableFieldProperty, "Vua");
    					}
    					if (rowData instanceof Relic) {
    						popupData((Relic) rowData, relicTableFieldName,
									relicTableFieldProperty, "Di tích lịch sử");
    					}
    					if (rowData instanceof HistoricalEvent) {
    						popupData((HistoricalEvent) rowData, eventTableFieldName,
									eventTableFieldProperty, "Sự kiện lịch sử");
    					}
    					if (rowData instanceof Dynasty) {
    						popupData((Dynasty) rowData, dynastyTableFieldName,
									dynastyTableFieldProperty, "Triều đại");
    					}
    					if (rowData instanceof Festival) {
    						popupData((Festival) rowData, festivalTableFieldName,
									festivalTableFieldProperty, "Lễ hội");
    					}
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			}
    		});
    		return row;
    	});
    }
    
    
    @FXML
    void handleItemClicked(MouseEvent event) {
    	VBox clickedItem = (VBox) event.getSource();
    	selectedItem.setStyle("-fx-cursor: hand");
    	selectedItem = clickedItem;
    	selectedItem.setStyle("-fx-background-color: #ccc");
    	
    	tableData.getColumns().clear();
    	String labelText = "";
    	for (Node node: selectedItem.getChildren()) {
    		if (node instanceof Label) {
    			Label label = (Label) node;
    			labelText = label.getText();
    		}
    	}
    	switch (labelText) {
    	case "Nhân vật lịch sử":
    		copyTable(figureTable, (TableView<Figure>) tableData);
    		break;
    	case "Vua":
    		copyTable(kingTable, (TableView<King>) tableData);
    		break;
    	case "Di tích lịch sử":
    		copyTable(relicTable, (TableView<Relic>) tableData);
    		break;
    	case "Sự kiện lịch sử":
    		copyTable(eventTable, (TableView<HistoricalEvent>) tableData);
    		break;
    	case "Triều đại":
    		copyTable(dynastyTable, (TableView<Dynasty>) tableData);
    		break;
    	case "Lễ hội":
    		copyTable(festivalTable, (TableView<Festival>) tableData);
    		break;
    	default:
    		System.out.println("Error.");
    	}
    }
    
    @FXML
    void pressEnter(KeyEvent event) {

    }
    
    private VBox createPopupElement(String field, List<String> content, String type) {
		VBox newVbox = new VBox();
		try {
			newVbox = (VBox) FXMLLoader.load(getClass().getResource(type + ".fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView image = (ImageView) (((HBox) (newVbox.getChildren().get(0))).getChildren().get(0));
		if (Arrays.asList("Quê quán", "Địa điểm", "Thủ đô").contains(field)) {
			File file = new File("src/main/iconProfile/address.png");
			image.setImage(new Image(file.toURI().toString()));
		}
		if (Arrays.asList("Tiền nhiệm", "Kế nhiệm").contains(field)) {
			File file = new File("src/main/iconProfile/person.png");
			image.setImage(new Image(file.toURI().toString()));
		}
		if (Arrays.asList("Năm sinh", "Năm mất", "Năm trị vì","Bắt đầu", "Kết thúc","Thời gian")
				.contains(field)) {
			File file = new File("src/main/iconProfile/time.png");
			image.setImage(new Image(file.toURI().toString()));
		}
		if (Arrays.asList("Ghi chú", "Mô tả").contains(field)) {
			File file = new File("src/main/iconProfile/description.png");
			image.setImage(new Image(file.toURI().toString()));
		}
		else {
			File file = new File("src/main/iconProfile/other.png");
			image.setImage(new Image(file.toURI().toString()));
		}
		Label label = (Label) (((HBox) (newVbox.getChildren().get(0))).getChildren().get(1));
		Text text = (Text) (((HBox) (newVbox.getChildren().get(0))).getChildren().get(2));
		label.setText(field);
		text.setText(content.get(0));

		return newVbox;
	}
    
    private <T> void popupData(T data, List<String> fieldName, List<String> property, String title) throws IOException {
		BorderPane root = FXMLLoader.load(getClass().getResource("profile.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Thông tin chi tiết");
		// set title
		VBox vBoxTop = (VBox) root.getTop();
		Text titleEle = (Text) vBoxTop.getChildren().get(1);
		titleEle.setText(title);

		// set avatar
		ImageView avatar = (ImageView) vBoxTop.getChildren().get(0);
		if (data instanceof HistoricalFigure) {
			File file = new File("src/main/iconProfile/figure.png");
			avatar.setImage(new Image(file.toURI().toString()));
		}
		if (data instanceof Relic) {
			File file = new File("src/main/iconProfile/relic.png");
			avatar.setImage(new Image(file.toURI().toString()));
		}
		if (data instanceof Festival) {
			File file = new File("src/main/iconProfile/festival.png");
			avatar.setImage(new Image(file.toURI().toString()));
		}
		if (data instanceof Dynasty) {
			File file = new File("src/main/iconProfile/dynasty.png");
			avatar.setImage(new Image(file.toURI().toString()));
		}
		if (data instanceof HistoricalEvent) {
			File file = new File("src/main/iconProfile/event.png");
			avatar.setImage(new Image(file.toURI().toString()));
		}
		if (data instanceof King) {
			File file = new File("src/main/iconProfile/King.png");
			avatar.setImage(new Image(file.toURI().toString()));
		}

		// get field element
		VBox vBoxCenter = (VBox) root.getCenter();

		vBoxCenter.getChildren().clear();
		
		// set field element
		for (int i = 0; i < fieldName.size(); ++i) {
			try {
				Class<T> clazz = (Class<T>) data.getClass();
				Field field = clazz.getDeclaredField(property.get(i));
				field.setAccessible(true);
				Object propertyValue = field.get(data);
				vBoxCenter.getChildren().add(createPopupElement(fieldName.get(i),
						Arrays.asList(propertyValue.toString()), "itemProfile"));
			} catch (NoSuchFieldException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		stage.setScene(new Scene(root));
		stage.show();
	}
}
