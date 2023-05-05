/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import java.util.HashMap;
import java.util.HashSet;
import org.opencv.core.Mat;

/**
 *
 * @author axeladn
 */
public class Matrix {

    private Identificator preIdentificator;
    private RetinotopicMatrix retinotopicMatrix;
    private Activation mainActivation;

    public Matrix() {
        this.preIdentificator = new Identificator();
        this.retinotopicMatrix = new RetinotopicMatrix();
        this.mainActivation = new Activation();
    }

    public Matrix(String preIdentificatorStr0, Mat mat0) {
        this.preIdentificator = new Identificator(preIdentificatorStr0);
        this.mainActivation = new Activation(mat0);
        this.retinotopicMatrix = new RetinotopicMatrix(this.preIdentificator, this.mainActivation);
    }

    void setWithMatData(String path0, Mat mat0) {
        this.preIdentificator = new Identificator(path0);
        HashSet<Activation> subActivationSet = this.mainActivation.getSubActivations();
        for (Activation subActivation : subActivationSet) {
            this.retinotopicMatrix = new RetinotopicMatrix(this.preIdentificator, subActivation);
            
        }

    }

}
