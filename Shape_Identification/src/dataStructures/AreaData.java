/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import config.ConfigFile;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author axeladn
 */
public class AreaData {

	private HashSet<Matrix> matrices;
	private final String currentArea;
	private String[] typeDirectories;
	private HashSet<String> areaTypes;
	private String dataRootFile;
	private HashMap<String, File[]> dataFilesTypeMap;
	private HashMap<String, Mat> rawMatData;

	public AreaData(String area0, HashSet<String> types0) {
		if (!ConfigFile.DATA_TYPES_PER_AREA.containsKey(ConfigFile.Areas.valueOf(area0))) {
			System.out.println("ERROR: This area is not registered! (" + area0 + ")");
			this.currentArea = ConfigFile.Areas.DEFAULT_AREA.name();
			this.areaTypes = new HashSet<>();
			this.typeDirectories = new String[0];
			this.rawMatData = new HashMap<>();
			return;
		}
		this.currentArea = ConfigFile.Areas.valueOf(area0).name();
		this.areaTypes = types0;
		this.rawMatData = new HashMap<>();
		this.dataFilesTypeMap = new HashMap<>();
		this.dataRootFile = "DEFAULT";
		this.typeDirectories = new String[]{"DEFAULT"};
		this.matrices = new HashSet<>();
	}

	public void update() {

	}

	public void setPath(String rootFile) {
		this.dataRootFile = rootFile;
		File file = new File(rootFile);
		this.typeDirectories = file.list((File current, String name) -> new File(current, name).isDirectory());
	}

	public void setDataFileList() {
		for (String dir : this.typeDirectories) {
			File currentDir = new File(this.dataRootFile + dir + "/");
			File[] files = currentDir.listFiles();
			this.dataFilesTypeMap.put(dir, files);
		}
	}

	public void extractDataFromFileList() {
		String path;
		Mat mat;
		for (String dirType : this.dataFilesTypeMap.keySet()) {
			for (File dataFile : this.dataFilesTypeMap.get(dirType)) {
				path = this.dataRootFile + dirType + "/" + dataFile.getName();
				mat = Imgcodecs.imread(path, Imgcodecs.IMREAD_ANYCOLOR);
				Imgproc.adaptiveThreshold(mat, mat, 255,
					Imgproc.ADAPTIVE_THRESH_MEAN_C,
					Imgproc.THRESH_BINARY, 13, 12);
				Core.bitwise_not(mat, mat);
				//Imgproc.resize(mat, mat, new Size(10,10));
				//System.out.println("Ccahnnels: " + mat.channels());
				rawMatData.put(path, mat);
			}
		}
	}

	public void setData() {
		for (String path : this.rawMatData.keySet()) {
			Matrix matrix = new Matrix(path, this.rawMatData.get(path));
			this.matrices.add(matrix);
		}

	}

	public void showImg(int level0) {

		for (Matrix matrix : this.matrices) {
			matrix.showImg(level0);
		}
	}

}
