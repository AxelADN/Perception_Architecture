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
	private HashSet<RetinotopicMatrix> retinotopicMatrices;
	private HashMap<RetinotopicPatch,HashSet> retinotopicPatches;
	private Activation mainActivation;

	public Matrix() {
		this.preIdentificator = new Identificator();
		this.retinotopicMatrices = new HashSet<>();
		this.mainActivation = new Activation();
	}

	public Matrix(String path0, Mat mat0) {
		this.retinotopicMatrices = new HashSet<>();
		this.preIdentificator = new Identificator(path0);
		this.mainActivation = new Activation(mat0);
		HashSet<Activation> subActivationSet = this.mainActivation.getSubActivations();
		
		for (Activation subActivation : subActivationSet) {
			this.retinotopicMatrices.add(new RetinotopicMatrix(this.preIdentificator, subActivation));
		}

	}
	
	public void getPartitions(){
		
	}
	
	public void getPartitions( int initSize0, int finalSize0, int step0){
		int chunkNum;
		int currentSize;
		int matNum = Math.floorDiv((finalSize0-initSize0),step0);
		for(int n = 0; n<matNum; n+=1){
			currentSize = initSize0 + (step0*n);
			chunkNum = Math.floorDiv(mat0.cols(), currentSize);
			for(int j=0; j<chunkNum; j+=1){
				for(int i=0; i<chunkNum; i+=1){
					
				}
			}
		} 
	}

	public void saveToFile() {
		for(RetinotopicMatrix matrix : retinotopicMatrices){
			matrix.saveToFile();
		}
	}

}
