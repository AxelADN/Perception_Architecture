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
public class Matrix {
	
	private Identificator preIdentificator;
	private RetinotopicArray retinotopicArray;
	
	public Matrix(){
		preIdentificator = new Identificator();
		retinotopicArray = new RetinotopicArray();
	}

	public Matrix(String preIdentificatorStr0, Mat mat0) {
		this. preIdentificator = new Identificator(preIdentificatorStr0);
		Activation activation = new Activation(mat0);
		this.retinotopicArray = new RetinotopicArray(this.preIdentificator,activation);
	}
	
}
