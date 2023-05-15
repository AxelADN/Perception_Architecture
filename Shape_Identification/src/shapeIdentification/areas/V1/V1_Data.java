/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapeIdentification.areas.V1;

import dataStructures.Matrix;
import java.util.HashMap;
import java.util.HashSet;
import org.opencv.core.Mat;
import utils.Dictionary;

/**
 *
 * @author axeladn
 */
public class V1_Data {
    
    private HashSet<Matrix> matrices;
    private HashMap<String, HashSet> excentricityMap;
    private HashSet<Dictionary> dictionaries;
    
    public V1_Data(){
        this.matrices = new HashSet<>();
        this.excentricityMap = new HashMap<>();
        this.dictionaries = new HashSet<>();
    }

    public void setData(HashMap<String, HashMap> excentricityRawMatData) {
        HashMap<String, Mat> currentRawMatData;
        HashSet<Matrix> currentMatrices;
        for (String excentricity : excentricityRawMatData.keySet()) {
            currentRawMatData = excentricityRawMatData.get(excentricity);
            currentMatrices = new HashSet<>();
            for (String path : currentRawMatData.keySet()) {
                Matrix matrix = new Matrix(excentricity, path, currentRawMatData.get(path));
                this.matrices.add(matrix);
                currentMatrices.add(matrix);
            }
            this.excentricityMap.put(excentricity, currentMatrices);
        }

    }

    public void setDict(Dictionary dict0) {
        this.dictionaries.add(dict0);
    }

    public Iterable<Matrix> getUnorderedData() {
        return this.matrices;
    }

    public HashSet<Matrix> getIdStructure(String excentricity0) {
        HashSet<Matrix> matrices = this.excentricityMap.get(excentricity0);
        HashSet<Matrix> newMatrices = new HashSet<>();
        for(Matrix matrix : matrices){
            newMatrices.add(matrix.withoutMat());
        }
        return newMatrices;
    }
    
}
