/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import interfaces.Copyable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import utils.DataConversion;

/**
 *
 * @author axeladn
 */
public class NA_Activation implements Copyable {

	private NA_Array3D<Double> activation;
	private HashMap<List,Array2D> patchMap;
	private Mat matPrimitive;
	private int channels;

	public NA_Activation(Mat mat0) {
		this.activation = new NA_Array3D<>();
		this.matPrimitive = mat0;
		this.channels = mat0.channels();
		this.setActivation();
	}

	//for Copyable
	public NA_Activation(Mat matPrimitive0, int channels0, NA_Array3D<Double> activation0) {
		this.channels = channels0;
		this.matPrimitive = matPrimitive0.clone();
		this.activation = activation0;
	}

	public NA_Activation() {
		this.activation = new NA_Array3D<>();
		this.channels = 0;
		this.matPrimitive = new Mat();
	}

	private NA_Activation(int channel0, NA_Array3D<Double> subActivation) {
		this.channels = channel0;
		this.activation = subActivation;
		this.matPrimitive = null;
	}

	public NA_Activation(NA_Activation subActivation, RetinotopicPatch patch) {
		this.channels = subActivation.getDepthIndex();
		this.patchMap = new HashMap<>();
		this.setPatch(subActivation, patch);
	}

	public final void setActivation() {
		ArrayList<Mat> splitedMat = new ArrayList<>();
		Core.split(this.matPrimitive, splitedMat);
		Array2D<Double> array2D;
		for (Mat channel : splitedMat) {
			array2D = DataConversion.MatToArray2DDouble(channel);
			this.activation.add(array2D);
		}
	}

	/*public void setID(ArrayList<Byte> bytes) {
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
	}*/
	public void print() {

	}

	@Override
	public NA_Activation copy() {
		NA_Activation activationAux = new NA_Activation(this.matPrimitive, this.channels, this.activation);
		return activationAux;

	}

	public HashSet<NA_Activation> getSubActivations() {
		HashMap<Integer, NA_Array3D> auxArray3D = this.activation.split();
		NA_Activation subActivation;
		HashSet<NA_Activation> subActivations = new HashSet<>();
		for (int channel : auxArray3D.keySet()) {
			subActivation = new NA_Activation(channel, auxArray3D.get(channel));
			subActivations.add(subActivation);
		}
		return subActivations;
	}

	public Reference2D size() {
		return new Reference2D(this.activation.size());
	}

	public double getValue(int col0, int row0) {
		try {
			return this.activation.get(new int[]{col0, row0, this.channels});
		} catch (NullPointerException ex) {
			return Double.valueOf(0);
		}
	}

	public int getDepthIndex() {
		return this.channels;
	}

	private void setPatch(NA_Activation subActivation, RetinotopicPatch patch) {
		Reference2D sizePatch = patch.getSize();
		Array2D<Double> activity ;
		for (int j = 0; j < sizePatch.getSizeY(); j += 1) {
			for (int i = 0; i < sizePatch.getSizeX(); i += 1) {
				List<Integer> index = new ArrayList<>();
				index.add(i);
				index.add(j);
				Reference2D indexPos = patch.getIndexPos(i,j);
				int blockSize = (int) Math.floor(patch.getBlockSize()*subActivation.size().getSizeX());
				activity = this.getBoxActivation(subActivation,indexPos,blockSize);
				this.patchMap.put(index, activity);
			}
		}
	}

	private Array2D getBoxActivation(NA_Activation subActivation, Reference2D indexPos, int blockSize) {
		int x = indexPos.getX();
		int y = indexPos.getY();
		Array2D<Double> array2D = new Array2D<>(blockSize,blockSize);
		for(int j=y; j<y+blockSize; j+=1){
			for(int i=x; i<x+blockSize;i+=1){
				array2D.add(new int[]{i,j}, subActivation.getValue(i, j));
			}
		}
		return array2D;
	}
}
