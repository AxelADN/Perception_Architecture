/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import interfaces.Copyable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import utils.DataConversion;
import utils.IDUtils;
import utils.ImageUtils;

/**
 *
 * @author axeladn
 */
public class Activation implements Copyable {

    private Array3D<Double> activation;
    private Mat matPrimitive;
    private int channels;

    public Activation(Mat mat0) {
        this.matPrimitive = mat0;
        this.channels = mat0.channels();
        this.setActivation();
    }

    //for Copyable
    public Activation(Mat matPrimitive0, int channels0, Array3D<Double> activation0) {
        this.channels = channels0;
        this.matPrimitive = matPrimitive0.clone();
        this.activation = activation0.copy();
    }

    public Activation() {
        this.activation = new Array3D<Double>();
        this.channels = 0;
        this.matPrimitive = new Mat();
    }

    private Activation(int channel0, Array3D<Double> subActivation) {
        this.channels = channel0;
        this.activation = subActivation;
        this.matPrimitive = null;
    }

    public final void setActivation() {
        int i;
        int cols = this.matPrimitive.cols();
        int rows = this.matPrimitive.rows();
        ArrayList<Mat> splitedMat = new ArrayList<>();
        Core.split(this.matPrimitive, splitedMat);
        Array2D<Double> array2D;
        for (Mat channel : splitedMat) {
            array2D = DataConversion.MatToArray2DDouble(channel);
            activation.add(array2D);
        }
    }

    public void setID(ArrayList<Byte> bytes) {
        int i = 0;
        int j = 0;
        int k = 0;
        ArrayList<Byte> bytes_i;
        ArrayList<Byte> bytes_j;
        ArrayList<Byte> bytes_k;
        ArrayList<Byte> bytes_0 = DataConversion.IntToByteArray(0);
        ArrayList<Byte> bytes_total;
        for (ArrayList<ArrayList<Double>> d2 : this.activation3d) {
            for (ArrayList<Double> d1 : d2) {
                for (double d0 : d1) {
                    bytes_i = DataConversion.IntToByteArray(i);
                    bytes_j = DataConversion.IntToByteArray(j);
                    bytes_k = DataConversion.IntToByteArray(k);
                    bytes_total = new ArrayList<>();
                    bytes_total.addAll(bytes);
                    bytes_total.addAll(bytes_0);
                    bytes_total.addAll(bytes_i);
                    bytes_total.addAll(bytes_j);
                    bytes_total.addAll(bytes_k);
                    Identificator id = new Identificator(bytes_total);
                    i += 1;
                }
                j += 1;
            }
            k += 1;
        }
    }

    public void printImg() {
        ImageUtils.showImg(this.matPrimitive, IDUtils.toString(ID));
    }

    public void print() {

    }

    @Override
    public Activation copy() {
        Activation activationAux = new Activation(this.matPrimitive, this.channels, this.activation);
        return activationAux;

    }

    public HashSet<Activation> getSubActivations() {
        HashMap<Integer, Array3D<Double>> auxArray3D = this.activation.split();
        Activation subActivation;
        HashSet<Activation> subActivations = new HashSet<>();
        for (int channel : auxArray3D.keySet()) {
            subActivation = new Activation(channel, auxArray3D.get(channel));
            subActivations.add(subActivation);
        }
        return subActivations;
    }

    public Reference2D size() {
        return new Reference2D(this.activation.size());
    }

    public double getValue(int col0, int row0) {
        return this.activation.get(new int[]{col0, row0, this.channels});
    }

    public int getDepthIndex() {
        return this.channels;
    }
}
