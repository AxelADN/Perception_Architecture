/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import org.opencv.core.Mat;

/**
 *
 * @author axeladn
 */
public class Activation {
    
    private Mat mainMat;
    private Identificator mainID;
    private double value;
    private Reference2D position;
    
    public Activation(){
        this.mainID = new Identificator();
        this.mainMat = new Mat();
        this.value = 0.0;
        this.position = new Reference2D(0,0);
    }

  

    public Activation(Identificator currentID0, Mat mat0) {
        this.mainID = currentID0;
        this.mainMat = mat0;
        this.value = 0.0;
        this.position = this.mainID.getLocation();
    }
    
    public Activation(Identificator currentID0) {
        this.mainID = currentID0;
        this.mainMat = null;
        this.value = 0.0;
        this.position = this.mainID.getLocation();
    }


    public Identificator getID() {
        return this.mainID;
    }

    public Mat getMat() {
        return this.mainMat;
    }

    public Activation withoutMat() {
        return new Activation(this.mainID);
    }
    
}
