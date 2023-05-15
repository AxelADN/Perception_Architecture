/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import java.util.HashMap;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import utils.ImageUtils;

/**
 *
 * @author axeladn
 */
public class Matrix {

    private Identificator preIdentificator;
    //private HashSet<RetinotopicMatrix> retinotopicMatrices;
    private HashMap<RetinotopicPatch, Array2D> retinotopicPatches;
    private Mat mainMat;

    public Matrix() {
        this.preIdentificator = new Identificator();
        this.mainMat = new Mat();

    }
    
    public Matrix(Identificator preIdentificator0,HashMap<RetinotopicPatch,Array2D> retinotopicPatches0){
        this.retinotopicPatches = retinotopicPatches0;
        this.mainMat = null;
        this.preIdentificator = preIdentificator0;
    }

    public Matrix(String excentricity0, String path0, Mat mat0) {
        //this.retinotopicMatrices = new HashSet<>();
        this.preIdentificator = new Identificator(path0);
        this.preIdentificator.setExcentricity(excentricity0);
        this.mainMat = mat0;
        this.retinotopicPatches = new HashMap<>();
        Activation currentActivation;
        Identificator currentID;

        Array2D<Activation> subActivationPerLevel;
        for (int level = 1; level <= RetinotopicPatch.maxLevel; level += 1) {
            RetinotopicPatch patch = new RetinotopicPatch(level);
            subActivationPerLevel = new Array2D<>(patch.getSize().getArray());
            Reference2D sizePatch = patch.getSize();
            double rectX = 0;
            double rectY = 0;
            double rectW = 0;
            double rectH = 0;
            for (int j = 0; j < sizePatch.getSizeY(); j += 1) {
                for (int i = 0; i < sizePatch.getSizeX(); i += 1) {
                    rectX = patch.getIndexPos(i, j).getFactorX() * this.mainMat.cols();
                    rectY = patch.getIndexPos(i, j).getFactorY() * this.mainMat.rows();
                    rectW = patch.getBlockSize() * this.mainMat.cols();
                    rectH = patch.getBlockSize() * this.mainMat.rows();
                    Rect block = new Rect(new double[]{rectX, rectY, rectW, rectH});
                    currentID = new Identificator(i, j, level, -1, this.preIdentificator);
                    currentActivation = new Activation(currentID, this.mainMat.submat(block));
                    subActivationPerLevel.add(new int[]{i, j}, currentActivation);
                }
            }
            this.retinotopicPatches.put(patch, subActivationPerLevel);
        }

    }

    public void showImg(int level0) {
        Identificator ID;
        Activation currentActivation;
        if (level0 < 0) {
            for (RetinotopicPatch level : this.retinotopicPatches.keySet()) {
                Array2D<Activation> activationArray = this.retinotopicPatches.get(level);
                for (int j = 0; j < activationArray.size()[1]; j += 1) {
                    for (int i = 0; i < activationArray.size()[0]; i += 1) {
                        currentActivation = activationArray.get(new int[]{i, j});
                        ID = currentActivation.getID();
                        ImageUtils.showImg(currentActivation.getMat(), ID.toString());
                    }
                }
            }
        } else {
            Array2D<Activation> activationArray = this.retinotopicPatches.get(new RetinotopicPatch(level0));
            for (int j = 0; j < activationArray.size()[1]; j += 1) {
                for (int i = 0; i < activationArray.size()[0]; i += 1) {
                    currentActivation = activationArray.get(new int[]{i, j});
                    ID = currentActivation.getID();
                    ImageUtils.showImg(currentActivation.getMat(), ID.toString());
                }
            }
        }
    }

    public Matrix withoutMat() {
        Array2D<Activation> newActivationArray;
        HashMap<RetinotopicPatch,Array2D> newRetinotopicPatches = new HashMap<>();
        for (RetinotopicPatch level : this.retinotopicPatches.keySet()) {
            Array2D<Activation> activationArray = this.retinotopicPatches.get(level);
            newActivationArray = new Array2D<>(activationArray.size());
            for (int j = 0; j < activationArray.size()[1]; j += 1) {
                    for (int i = 0; i < activationArray.size()[0]; i += 1) {
                        newActivationArray.add(new int[]{i,j}, activationArray.get(new int[]{i,j}).withoutMat());
                    }
            }
            newRetinotopicPatches.put(level, newActivationArray);
        }
        return new Matrix(this.preIdentificator,newRetinotopicPatches);
    }

    public Array2D<Activation> get(int level0) {
        return this.retinotopicPatches.get(level0);
    }

}
