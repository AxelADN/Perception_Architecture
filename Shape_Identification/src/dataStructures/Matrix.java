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

	public void print() {
		for(RetinotopicMatrix matrix : retinotopicMatrices){
			matrix.print();
		}
	}

}
