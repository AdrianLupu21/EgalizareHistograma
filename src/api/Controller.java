package api;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;


public class Controller {

    @FXML
    private VBox minimize;

    @FXML
    private VBox close;

    @FXML
    private Button Incarca;

    @FXML
    private Button salveaza;

    @FXML
    private ImageView incarcat;
    
    @FXML
    private ImageView deAfisat;
    
    private FileChooser fileChooser;

    private Mat modifiedImage;
    
    @FXML
    void onClose(MouseEvent event) {
    	Platform.exit();
    }  
    
    @FXML
    void initialize() {
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	System.out.println(System.getProperty("user.dir"));
  
    	fileChooser = new FileChooser();  
    	
    }
    
    @FXML
    void onIncarca(ActionEvent event) throws Exception {
    	 
    	File file =  fileChooser.showOpenDialog((Stage) Incarca.getScene().getWindow());
    	
    	if(file != null) {
    		
    		Model myModel = new Model();
    		myModel.setOriginalImage(Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE));
    		myModel.setModifiedImage(new Mat(myModel.getOriginalImage().rows(), myModel.getOriginalImage().cols(), myModel.getOriginalImage().type()));
    		
    		Imgproc.equalizeHist(myModel.getOriginalImage(), myModel.getModifiedImage());

    		 BufferedImage og = Mat2BufferedImage(myModel.getOriginalImage());
    		 BufferedImage md = Mat2BufferedImage(myModel.getModifiedImage());
    		 
    		 modifiedImage = myModel.getModifiedImage();
    		 Image original = SwingFXUtils.toFXImage(og, null);
    		 Image modified = SwingFXUtils.toFXImage(md, null);
    		 incarcat.setImage(original);
    		 deAfisat.setImage(modified);

    		 
    	}
    } 

    @FXML
    void onMinimize(MouseEvent event) {
    	Stage stage = (Stage) minimize.getScene().getWindow();
    	stage.setIconified(true);
    }

    @FXML
    void onSalveaza(ActionEvent event) {
    	
    	if(modifiedImage != null) {
    		
    		File saveFile = fileChooser.showSaveDialog((Stage) salveaza.getScene().getWindow());
    		Imgcodecs.imwrite(saveFile.getAbsolutePath() + ".png", modifiedImage);
    	
    	}else {
    		
    		System.out.println(modifiedImage);
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setContentText("Imaginea nu poate fi salvata");
    		alert.show();
    		
    	}
    	
    }
    
    
    void address(String str1, String str2) {
    	
    	String[] addr1 = str1.split("\\\\");
    	
    	for (String str: addr1){
    		System.out.println(str);
    	}
    	

    }
     BufferedImage Mat2BufferedImage(Mat matrix)throws Exception {        
       
    	MatOfByte mob=new MatOfByte();
        Imgcodecs.imencode(".jpg", matrix, mob);
        byte ba[]=mob.toArray();

        BufferedImage bi=ImageIO.read(new ByteArrayInputStream(ba));
        return bi;
        
    }
     
}
