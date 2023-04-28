/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import utils.DataConversion;
import utils.IDUtils;
import utils.ImageUtils;

/**
 *
 * @author axeladn
 */
public class Matrix {

	private ArrayList<ArrayList<ArrayList<Double>>> activation;
	private HashMap<Identificator, byte[]> data;
	private ArrayList<Byte> ID;
	private Mat matPrimitive;
	private int channels;

	public Matrix() {
		this.activation = new ArrayList<>();
		this.ID = new ArrayList<>();
		this.matPrimitive = new Mat();
		this.channels = 3;
	}

	public Matrix(int channels0) {
		this();
		this.channels = channels0;
	}

	public void setMatrix1D(Mat imread0) {
		if (channels != 1) {
			System.out.println("ERROR: incorrect number of channels (1D) -> " + imread0.channels());
			return;
		}
		this.matPrimitive = imread0;
		ArrayList<Mat> channels = new ArrayList<>();
		Core.split(imread0, channels);
		double[] channelData;
		ArrayList<ArrayList<Double>> auxArray2;
		ArrayList<Double> auxArray1;
		int i;
		int cols;
		int rows;
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
		activation.add(auxArray2);

	}

	public void setMatrix3D(Mat imread0) {
		if (channels != 3) {
			System.out.println("ERROR: incorrect number of channels (3D) -> " + imread0.channels());
			return;
		}
		this.matPrimitive = imread0;
		ArrayList<Mat> channels = new ArrayList<>();
		Core.split(imread0, channels);
		double[] channelData;
		ArrayList<ArrayList<Double>> auxArray2;
		ArrayList<Double> auxArray1;
		int i;
		int cols;
		int rows;
		for (Mat channel : channels) {
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
			activation.add(auxArray2);
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
		for (ArrayList<ArrayList<Double>> d2 : this.activation) {
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
		for (double cognit )
	}

}
