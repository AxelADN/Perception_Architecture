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
import org.opencv.core.Range;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author axeladn
 */
public class AreaData {

        private static final HashMap<String, double[]> excentricityPatches = new HashMap<>();
        private static final String FOVEA = "FOVEA";
        private static final String PARAFOVEA = "PARAFOVEA";
        private static final String PERIFOVEA = "PERIFOVEA";

        static {
                double[] foveaPatch = new double[]{0.33, 0.33, 0.33, 0.33};
                double[] paraFoveaPatch = new double[]{0.166, 0.166, 0.66, 0.66};
                double[] periFoveaPatch = new double[]{0.0, 0.0, 1.0, 1.0};

                AreaData.excentricityPatches.put(AreaData.FOVEA, foveaPatch);
                AreaData.excentricityPatches.put(AreaData.PARAFOVEA, paraFoveaPatch);
                AreaData.excentricityPatches.put(AreaData.PERIFOVEA, periFoveaPatch);

        }

        private HashSet<Matrix> matrices;
        private final String currentArea;
        private String[] typeDirectories;
        private HashSet<String> areaTypes;
        private String dataRootFile;
        private HashMap<String, File[]> dataFilesTypeMap;
        private HashMap<String, Mat> rawMatData;
        private HashMap<String, HashMap> excentricityRawMatData;
        private Reference2D mainSize;

        public AreaData(String area0, HashSet<String> types0) {
                if (!ConfigFile.DATA_TYPES_PER_AREA.containsKey(ConfigFile.Areas.valueOf(area0))) {
                        System.out.println("ERROR: This area is not registered! (" + area0 + ")");
                        this.currentArea = ConfigFile.Areas.DEFAULT_AREA.name();
                        this.areaTypes = new HashSet<>();
                        this.typeDirectories = new String[0];
                        this.rawMatData = new HashMap<>();
                        this.excentricityRawMatData = new HashMap<>();
                        return;
                }
                this.currentArea = ConfigFile.Areas.valueOf(area0).name();
                this.areaTypes = types0;
                this.rawMatData = new HashMap<>();
                this.dataFilesTypeMap = new HashMap<>();
                this.dataRootFile = "DEFAULT";
                this.typeDirectories = new String[]{"DEFAULT"};
                this.matrices = new HashSet<>();
                this.excentricityRawMatData = new HashMap<>();
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

        public void setExcentricity() {
                this.excentricityRawMatData.put(AreaData.FOVEA, new HashMap());
                this.excentricityRawMatData.put(AreaData.PARAFOVEA, new HashMap());
                this.excentricityRawMatData.put(AreaData.PERIFOVEA, new HashMap());
                HashMap<String, Mat> currentRawMatData;
                for (String excentricity : this.excentricityRawMatData.keySet()) {
                        currentRawMatData = new HashMap<>();
                        for (String currentPath : this.rawMatData.keySet()) {
                                Mat currentMat = this.rawMatData.get(currentPath).submat(
                                        this.getPatchRect(AreaData.excentricityPatches.get(excentricity))
                                );
                                if (currentMat != null) {
                                        Imgproc.resize(currentMat, currentMat, new Size(this.mainSize.getCols(), this.mainSize.getRows()));
                                        currentRawMatData.put(currentPath, currentMat);
                                }

                        }
                        this.excentricityRawMatData.put(excentricity, currentRawMatData);
                }

        }

        public void setData() {
                HashMap<String, Mat> currentRawMatData;
                for (String excentricity : this.excentricityRawMatData.keySet()) {
                        currentRawMatData = this.excentricityRawMatData.get(excentricity);
                        for (String path : currentRawMatData.keySet()) {
                                Matrix matrix = new Matrix(excentricity, path, currentRawMatData.get(path));
                                this.matrices.add(matrix);
                        }
                }

        }

        public void showImg(int level0) {

                for (Matrix matrix : this.matrices) {
                        matrix.showImg(level0);
                }
        }

        private Rect getPatchRect(double[] factorArray) {
                double[] rectData = new double[]{
                        factorArray[0]*this.mainSize.getSizeX(),
                        factorArray[1]*this.mainSize.getSizeY(),
                        factorArray[2]*this.mainSize.getSizeX(),
                        factorArray[3]*this.mainSize.getSizeY(),
                };
                return new Rect(rectData);
                
        }

}
