/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapeIdentification.areas;

import java.util.ArrayList;
import java.util.Iterator;
import dataStructures.AreaData;
import dataStructures.NA_Activation;
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
	private ArrayList<NA_Activation> activations;
	private Dictionary dict;

	public V1() {
		imgNames = new ArrayList<>();
		currentImg = imgNames.iterator();

		rootFile = "/home/axeladn/Documents/Tesis_Doctorado/Perception_System/Sensory_Data_Set/V1/";
		activations = new ArrayList<>();
		dict = new Dictionary("V1");

		dict.addKeyType("ComplexCells");
		dict.addKeyType("DoubleOpponent");
		dict.addKeyType("HyperComplexCells");
		dict.addKeyType("MergedHyperComplexCells");
		dict.addKeyType("SimpleCells");
		dict.addKeySubtype("Or_0");
		dict.addKeySubtype("Or_1");
		dict.addKeySubtype("Or_2");
		dict.addKeySubtype("Or_3");
		dict.addKeySubtype("Bank_0");
		dict.addKeySubtype("Bank0");
		dict.addKeySubtype("Eye_0");
		dict.addKeySubtype("Dp");
		dict.addKeySubtype("Kp");
		dict.addKeySubtype("Lp");
		dict.addKeySubtype("Type_0");
		dict.addKeySubtype("Type_1");
		dict.addKeySubtype("Phase1");
		dict.addKeySubtype("Phase2");

		storage = new AreaData("V1", dict.getTypes());
	}

	public void Populate() {
		storage.setPath(this.rootFile);
		storage.setDataFileList();
		storage.extractDataFromFileList();
                storage.setExcentricity();
		storage.setData();
		
		storage.showImg(2);

	}

}
