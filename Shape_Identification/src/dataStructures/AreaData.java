/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import config.ConfigFile;
import control.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author axeladn
 */
public class AreaData {

    private HashSet<Matrix> matrices;
    private final String currentArea;
    private String[] typeDirectories;
    private ArrayList<String> areaTypes;
    private String dataRootFile;
    private HashMap<String, File[]> dataFilesTypeMap;
    private HashMap<String, Mat> rawMatData;

    public AreaData(String area0, ArrayList<String> types0) {
        if (!ConfigFile.DATA_TYPES_PER_AREA.containsKey(ConfigFile.Areas.valueOf(area0))) {
            System.out.println("ERROR: This area is not registered! (" + area0 + ")");
            this.currentArea = ConfigFile.Areas.DEFAULT_AREA.name();
            this.areaTypes = new ArrayList<>();
            this.typeDirectories = new String[0];
            this.rawMatData = new HashMap<>();
            return;
        }
        this.currentArea = ConfigFile.Areas.valueOf(area0).name();
        this.areaTypes = types0;
        this.rawMatData = new HashMap<>();
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
                System.out.println("Ccahnnels: " + mat.channels());
                rawMatData.put(path, mat);
            }
        }
    }

    public void setData() {
        for (String path : this.rawMatData.keySet()) {
            Matrix matrix = new Matrix();
            matrix.setWithMatData(path,this.rawMatData.get(path));
        }

    }

}
