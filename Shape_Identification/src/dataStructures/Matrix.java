/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import java.util.ArrayList;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 *
 * @author axeladn
 */
public class Matrix {
	
	private ArrayList<ArrayList<ArrayList<Double>> > data;
	
	public Matrix(){
		data = new ArrayList<>();
	}

	public void setMatrix(Mat imread0) {
		ArrayList<Mat> channels = new ArrayList<>();
		Core.split(imread0, channels);
		double[] channelData; 
		ArrayList<ArrayList<Double>> auxArray2;
		ArrayList<Double> auxArray1;
		int i;
		int cols;
		int rows;
		for(Mat channel : channels){
			auxArray2 = new ArrayList<>();
			channelData = new double[(int)channel.total()];
			channel.get(0, 0, channelData);
			cols = channel.cols();
			rows = channel.rows();
			for(int row=0; row<rows; row++){
				auxArray1 = new ArrayList<>();
				for(int col=0; col<cols; col++){
					i=(cols*row)+col;
					auxArray1.add(channelData[i]);
				}
				auxArray2.add(auxArray1);
			}
			data.add(auxArray2);
		}
	}
		
	
}
