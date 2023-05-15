/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapeIdentification.areas.LO1;

import dataStructures.Matrix;
import java.util.HashSet;
import shapeIdentification.areas.V1.V1;

/**
 *
 * @author axeladn
 */
public class LO1_Populate {
    
   
    
    public LO1_Populate(){
        
    }
    
    public HashSet<Matrix> requestVisualData(){
        
        return V1.requestVisualID(LO1_Metadata.EXCENTRICITY);
        
    }
    
}
