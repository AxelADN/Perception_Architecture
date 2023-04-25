/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapeIdentification.areas;

import java.util.ArrayList;
import java.util.Iterator;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import control.Error;
import control.Log;
import dataStructures.AreaData;
import dataStructures.Matrix;

/**
 *
 * @author axeladn
 */
public class V1 {

	private AreaData storage;
	private ArrayList<String> imgNames;
	private Iterator currentImg;

	public V1(){
		imgNames = new ArrayList<>();
		currentImg = imgNames.iterator();
		storage = new AreaData("V1");
	}
	
	public void Retrieval() {
		if(!this.currentImg.hasNext()){
			Log.error(Error.error.NO_INSTANCE_IN_MEMORY,this.getClass().getSimpleName());
			return;
		}
		String imgName = (String)currentImg.next();
		getMatrix(imgName);
	}
	
	public void unitRetrieval(){
		
	}

	public void Identification() {

	}

	private void getMatrix(String imgName0) {
		
		Matrix currentMatrix = new Matrix();
		currentMatrix.setMatrix(Imgcodecs.imread(imgName0, Imgcodecs.IMREAD_COLOR));
	}

	private String getImgName() {
		return 
	}

}
