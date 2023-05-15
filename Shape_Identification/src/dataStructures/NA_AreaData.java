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
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author axeladn
 */
public class NA_AreaData {

    

    
    private final String currentArea;
    
    private HashSet<String> areaTypes;
    
    
    
    
    

    public NA_AreaData(String area0, HashSet<String> types0) {
        if (!ConfigFile.DATA_TYPES_PER_AREA.containsKey(ConfigFile.Areas.valueOf(area0))) {
            System.out.println("ERROR: This area is not registered! (" + area0 + ")");
            this.currentArea = ConfigFile.Areas.DEFAULT_AREA.name();
            this.areaTypes = new HashSet<>();
            
            return;
        }
        this.currentArea = ConfigFile.Areas.valueOf(area0).name();
        this.areaTypes = types0;
        
    }
    
    

    public void update() {

    }

    

    

    

    

    

    

    

}
