/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import config.ConfigFile;
import dataStructures.NA_AreaData;
import dataStructures.Reference2D;
import java.io.File;
import java.util.HashMap;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import shapeIdentification.areas.V1.V1_Metadata;

/**
 *
 * @author axeladn
 */
public class FileHelper {
    
    private String[] typeDirectories;
    private String dataRootFile;
    private HashMap<String, File[]> dataFilesTypeMap;
    private Reference2D mainSize;
    private HashMap<String, Mat> rawMatData;
    private HashMap<String, HashMap> excentricityRawMatData;
    
    public FileHelper(){
        this.typeDirectories = new String[]{};
        this.dataFilesTypeMap = new HashMap<>();
        this.dataRootFile = new String();
        this.excentricityRawMatData = new HashMap<>();
        this.mainSize = new Reference2D(0,0);
        this.rawMatData = new HashMap<>();
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
                this.mainSize = new Reference2D(mat.cols(), mat.rows());
                Imgproc.adaptiveThreshold(mat, mat, 255,
                        Imgproc.ADAPTIVE_THRESH_MEAN_C,
                        Imgproc.THRESH_BINARY, 9, 10);
                Core.bitwise_not(mat, mat);
                //Imgproc.resize(mat, mat, new Size(10,10));
                //System.out.println("Ccahnnels: " + mat.channels());
                if (Core.countNonZero(mat) > mat.total() * mat.channels() * ConfigFile.MIN_PIXELS_FACTOR) {
                    System.out.println("PIXELS: " + (mat.total() * mat.channels() * ConfigFile.MIN_PIXELS_FACTOR));
                    this.rawMatData.put(path, mat);
                }

            }
        }
    }
    
    public HashMap<String,HashMap> setExcentricity() {
        this.excentricityRawMatData.put(V1_Metadata.FOVEA, new HashMap());
        this.excentricityRawMatData.put(V1_Metadata.PARAFOVEA, new HashMap());
        this.excentricityRawMatData.put(V1_Metadata.PERIFOVEA, new HashMap());
        HashMap<String, Mat> currentRawMatData;
        for (String excentricity : this.excentricityRawMatData.keySet()) {
            currentRawMatData = new HashMap<>();
            for (String currentPath : this.rawMatData.keySet()) {
                Mat currentMat = this.rawMatData.get(currentPath).submat(
                        this.getPatchRect(V1_Metadata.excentricityPatches.get(excentricity))
                );
                if (currentMat != null) {
                    Imgproc.resize(currentMat, currentMat, new Size(this.mainSize.getCols(), this.mainSize.getRows()));
                    currentRawMatData.put(currentPath, currentMat);
                }

            }
            this.excentricityRawMatData.put(excentricity, currentRawMatData);
        }
        return this.excentricityRawMatData;

    }
    
    private Rect getPatchRect(double[] factorArray) {
        double[] rectData = new double[]{
            factorArray[0] * this.mainSize.getSizeX(),
            factorArray[1] * this.mainSize.getSizeY(),
            factorArray[2] * this.mainSize.getSizeX(),
            factorArray[3] * this.mainSize.getSizeY(),};
        return new Rect(rectData);

    }
    
    
}
