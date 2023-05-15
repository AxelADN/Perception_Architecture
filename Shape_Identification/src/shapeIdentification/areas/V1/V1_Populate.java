/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapeIdentification.areas.V1;

import java.util.HashMap;
import utils.FileHelper;

/**
 *
 * @author axeladn
 */
public class V1_Populate {
    
    private FileHelper fileHelper;
    private String rootFile;
    private HashMap<String,HashMap> excentricityMap;

    public V1_Populate() {
        fileHelper = new FileHelper();
        rootFile = "/home/axeladn/Documents/Tesis_Doctorado/Perception_System/Sensory_Data_Set/V1/";
        
        fileHelper.setPath(this.rootFile);
        fileHelper.setDataFileList();
        fileHelper.extractDataFromFileList();
        excentricityMap = fileHelper.setExcentricity();
    }

    public HashMap<String, HashMap> processData() {
        return this.excentricityMap;
    }

}
