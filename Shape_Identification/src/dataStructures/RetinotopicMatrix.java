/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import config.ConfigFile;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author axeladn
 */
public class RetinotopicMatrix {

	private Array2D<RetinotopicCognit> matrix;
	private Activation activationMatrix;

	public RetinotopicMatrix() {
		this.matrix = new Array2D<>();
		this.activationMatrix = new Activation();
	}

	public RetinotopicMatrix(Identificator preIdentificator0, Activation activation0) {
		Reference2D size = activation0.size();
		this.matrix = new Array2D<>(size.getCols(), size.getRows());
		this.activationMatrix = activation0;
		this.merge(preIdentificator0, activation0);
	}

	private void merge(Identificator preIdentificator0, Activation activation0) {
		Reference2D size = activation0.size();
		Identificator identificator;
		double value;
		for (int row = 0; row < size.getRows(); row += 1) {
			for (int col = 0; col < size.getCols(); col += 1) {
				value = activation0.getValue(col, row);
				identificator = new Identificator(col, row, activation0.getDepthIndex(), value, preIdentificator0);
				//System.out.println("value: "+value);
				this.matrix.add(new int[]{col, row}, new RetinotopicCognit(identificator, value));
				//System.out.println("value: "+this.matrix.get(new int[]{col,row}).getActivation());
			}
		}
	}

	private String getPrintable() {
		String str = new String();
		str += "[";
		System.out.println("size: "+new Reference2D(this.matrix.size()).toString());
		for (int i = 0; i < this.matrix.size()[0]; i += 1) {
			str += "[";
			for (int j = 0; j < this.matrix.size()[1]; j += 1) {
				double value = this.matrix.get(new int[]{i, j}).getActivation();
				//if (value > 0.0){
					str += value;
					//System.out.println(this.get(new int[]{i,j}).toString());
					str += ",";
					//System.out.print(str);
				//}

			}
			str += "],\n";
		}
		str += "]";
		System.out.println(str);
		return str;
	}
	
	public void saveToFile(){
		FileWriter root = null;
		try {
			root = new FileWriter(ConfigFile.SAVE_FILE+"V1_Test.txt");
			BufferedWriter writer = new BufferedWriter(root);
			String str = this.getPrintable();
			writer.write(str);
			writer.close();
		} catch (IOException ex) {
			Logger.getLogger(RetinotopicMatrix.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				root.close();
			} catch (IOException ex) {
				Logger.getLogger(RetinotopicMatrix.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
	}

}
