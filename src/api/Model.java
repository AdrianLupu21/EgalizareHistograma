package api;

import org.opencv.core.Mat;

public class Model {
	
	private Mat originalImage;
	private Mat modifiedImage;
	
	public Mat getOriginalImage() {
		return originalImage;
	}
	
	public void setOriginalImage(Mat originalImage) {
		this.originalImage = originalImage;
	}
	
	public Mat getModifiedImage() {
		return modifiedImage;
	}
	
	public void setModifiedImage(Mat modifiedImage) {
		this.modifiedImage = modifiedImage;
	}
	
}
