package hust.soict.cysec.oop.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
			root.setStyle(
					"-fx-background-image:url('https://img4.thuthuatphanmem.vn/uploads/2020/12/26/hinh-nen-powerpoint-trong-dong-dong-son-dep_091305907.jpg');-fx-background-size : 100% 100%");
			primaryStage.setTitle("Project OOP");
			Scene scene = new Scene(root, 1024, 768);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("file:src/main/iconProfile/time.png"));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
