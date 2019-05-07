package api;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

	public class Main extends Application {
		
		public static void main(String[] args) {
			Application.launch();
		}
		public static Stage myStage;
		private double xOffset;
		private double yOffset;
		@Override
		public void start(Stage primaryStage) throws IOException {
			
			myStage = primaryStage; 
			Pane myPane = (Pane)FXMLLoader.load(getClass().getResource("../View.fxml"));
			myStage.setScene(new Scene(myPane));
			myStage.setTitle("prioectAPI");
			myStage.setMinHeight(700);
			myStage.setMinWidth(1000);
			myStage.initStyle(StageStyle.UNDECORATED);
		  	  myPane.setOnMousePressed(new EventHandler<MouseEvent>() {
	              @Override
	              public void handle(MouseEvent event) {
	                  xOffset = event.getSceneX();
	                  yOffset = event.getSceneY();
	              }
	          });
	    	  
	    	  myPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
	              @Override
	              public void handle(MouseEvent event) {
	                  myStage.setX(event.getScreenX() - xOffset);
	                  myStage.setY(event.getScreenY() - yOffset);
	              }
	          });
	    	
			
			myStage.show();
		}
		
	}
