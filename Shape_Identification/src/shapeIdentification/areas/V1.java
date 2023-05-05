/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapeIdentification.areas;

import java.util.ArrayList;
import java.util.Iterator;
import org.opencv.imgcodecs.Imgcodecs;
import dataStructures.AreaData;
import dataStructures.Activation;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.HashMap;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import utils.Dictionary;

/**
 *
 * @author axeladn
 */
public class V1 {

	private AreaData storage;
	private ArrayList<String> imgNames;
	private Iterator currentImg;
	private String rootFile;
	private ArrayList<Activation> activations;
	private Dictionary dict;
	

	public V1() {
		imgNames = new ArrayList<>();
		currentImg = imgNames.iterator();
		
		rootFile = "/home/axeladn/Documents/Tesis_Doctorado/Preprocessed_DataSet/DataSet4/V1/";
		activations = new ArrayList<>();
		dict = new Dictionary("V1");

		dict.addKeyType("ComplexCells", (byte) 1);
		dict.addKeyType("DoubleOpponent", (byte) 2);
		dict.addKeyType("HyperComplexCells", (byte) 3);
		dict.addKeyType("MergedHyperComplexCells", (byte) 4);
		dict.addKeyType("SimpleCells", (byte) 5);
		dict.addKeySubtype("Or_0", (byte) 6);
		dict.addKeySubtype("Or_1", (byte) 7);
		dict.addKeySubtype("Or_2", (byte) 8);
		dict.addKeySubtype("Or_3", (byte) 9);
		dict.addKeySubtype("Bank_0", (byte) 11);
		dict.addKeySubtype("Bank0", (byte) 11);
		dict.addKeySubtype("Eye_0", (byte) 12);
		dict.addKeySubtype("Dp", (byte) 13);
		dict.addKeySubtype("Kp", (byte) 14);
		dict.addKeySubtype("Lp", (byte) 15);
		dict.addKeySubtype("Type_0", (byte) 16);
		dict.addKeySubtype("Type_1", (byte) 17);
		dict.addKeySubtype("Phase1", (byte) 18);
		dict.addKeySubtype("Phase2", (byte) 19);
		
		storage = new AreaData("V1", dict.getTypes());
	}

	public void Populate() {
		storage.setPath(this.rootFile);
		storage.setDataFileList();
		storage.extractDataFromFileList();
			for (File fileName : files) {
				Activation currentActivation = new Activation();
				String path = this.rootFile + dir + fileName.getName();
				System.out.println(path);
				ArrayList<Byte> bytes = this.dict.getKeyBytes(path);
				this.dict.add(path, bytes);
				Mat currentMat = Imgcodecs.imread(path, Imgcodecs.IMREAD_COLOR);
				currentMat.convertTo(currentMat, CvType.CV_64F);
				activations.add(currentActivation);
			}

		for (Activation matrix : activations) {
			matrix.printImg();
		}

	}

}
