/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapeIdentification.areas;

import java.util.ArrayList;
import java.util.Iterator;
import org.opencv.imgcodecs.Imgcodecs;
import dataStructures.AreaData;
import dataStructures.Matrix;
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
	private ArrayList<Matrix> matrices;
	private Dictionary dict;
	private final HashMap<String, Byte> keyWords;

	public V1() {
		imgNames = new ArrayList<>();
		currentImg = imgNames.iterator();
		storage = new AreaData("V1");
		rootFile = "/home/axeladn/Documents/Tesis_Doctorado/Preprocessed_DataSet/DataSet4/V1/";
		matrices = new ArrayList<>();
		dict = new Dictionary("V1");

		keyWords = new HashMap<>();
		keyWords.put("ComplexCells", (byte) 1);
		keyWords.put("DoubleOpponent", (byte) 2);
		keyWords.put("HyperComplexCells", (byte) 3);
		keyWords.put("MergedHyperComplexCells", (byte) 4);
		keyWords.put("SimpleCells", (byte) 5);
		keyWords.put("Or_0", (byte) 6);
		keyWords.put("Or_1", (byte) 7);
		keyWords.put("Or_2", (byte) 8);
		keyWords.put("Or_3", (byte) 9);
		keyWords.put("Bank_0", (byte) 11);
		keyWords.put("Bank0", (byte) 11);
		keyWords.put("Eye_0", (byte) 12);
		keyWords.put("Dp", (byte) 13);
		keyWords.put("Kp", (byte) 14);
		keyWords.put("Lp", (byte) 15);
		keyWords.put("Type_0", (byte) 16);
		keyWords.put("Type_1", (byte) 17);
		keyWords.put("Phase1", (byte) 18);
		keyWords.put("Phase2", (byte) 19);

		dict.setKeyWords(keyWords);
		

	}

	public void Retrieval() {
		File file = new File(rootFile);
		String[] directories = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		});
		for (String dir : directories) {
			dir = dir+"/";
			File currentDir = new File(this.rootFile+dir);
			File[] files = currentDir.listFiles();
			System.out.println(dir);
			for (File fileName : files) {
				Matrix currentMatrix = new Matrix();
				String path = this.rootFile + dir + fileName.getName();
				System.out.println(path);
				ArrayList<Byte> bytes = this.dict.getKeyBytes(path);
				this.dict.add(path, bytes);
				Mat currentMat = Imgcodecs.imread(path, Imgcodecs.IMREAD_COLOR);
				currentMat.convertTo(currentMat, CvType.CV_64F);
				currentMatrix.setMatrix(currentMat);
				currentMatrix.setID(bytes);
				matrices.add(currentMatrix);
				
			}

		}

		for (Matrix matrix : matrices) {
			matrix.printImg();
		}

	}

}
