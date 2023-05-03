/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import interfaces.Copyable;
import java.util.ArrayList;
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

	private ArrayList<ArrayList<ArrayList<Double>>> activation3d;
	private ArrayList<Double> activation1d;
	private ArrayList<Byte> ID;
	private Mat matPrimitive;
	private int channels;

	public Activation() {
		this.activation3d = new ArrayList<>();
		this.activation1d = null;
		this.ID = new ArrayList<>();
		this.matPrimitive = new Mat();
		this.channels = 3;
	}

	public Activation(int channels0) {
		this();
		this.channels = channels0;
		if (this.channels == 1) {
			this.activation3d = null;
			this.activation1d = new ArrayList<>();
		}
	}
	
	public Activation(ArrayList<Byte> ID0, Mat matPrimitive0, int channels0, ArrayList<Double> activation1d0, ArrayList<ArrayList<ArrayList<Double>>> activation3d0){
		this.ID = (ArrayList<Byte>) ID0.clone();
		this.channels = channels0;
		this.matPrimitive = matPrimitive0.clone();
		this.activation1d = (ArrayList<Double>) activation1d0.clone();
		this.activation3d = new ArrayList<>();
		ArrayList<ArrayList<Double>> aux2 = new ArrayList<>();
		ArrayList<Double> aux1;
		for(ArrayList<ArrayList<Double>> array2 : activation3d0){
			for(ArrayList<Double> array1 : array2){
				aux1 = (ArrayList<Double>) array1.clone();
				aux2.add(aux1);
			}
			this.activation3d.add(aux2);
		}
	}

	public void setActivation(Mat imread0) {
		if (this.channels != imread0.channels()) {
			System.out.println("ERROR: incorrect number of channels -> " + this.channels + "!=" + imread0.channels());
			return;
		}
		this.matPrimitive = imread0;
		int i;
		int cols;
		int rows;
		if (this.channels == 3) {
			ArrayList<Mat> auxChannels = new ArrayList<>();
			Core.split(imread0, auxChannels);
			double[] channelData;
			ArrayList<ArrayList<Double>> auxArray2;
			ArrayList<Double> auxArray1;
			for (Mat channel : auxChannels) {
				auxArray2 = new ArrayList<>();
				channelData = new double[(int) channel.total()];
				channel.get(0, 0, channelData);
				cols = channel.cols();
				rows = channel.rows();
				for (int row = 0; row < rows; row++) {
					auxArray1 = new ArrayList<>();
					for (int col = 0; col < cols; col++) {
						i = (cols * row) + col;
						auxArray1.add(channelData[i]);
					}
					auxArray2.add(auxArray1);
				}
				activation3d.add(auxArray2);
			}
		}
		if (this.channels == 1) {
			double[] auxData;
			auxData = new double[(int) imread0.total()];
			imread0.get(0, 0, auxData);
			cols = imread0.cols();
			rows = imread0.rows();
			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < cols; col++) {
					i = (cols * row) + col;
					this.activation1d.add(auxData[i]);
				}
			}
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
		Activation activationAux = new Activation(this.ID, this.matPrimitive, this.channels, this.activation1d, this.activation3d);
		return activationAux;
		
	}

}
