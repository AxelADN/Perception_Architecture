/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

/**
 *
 * @author axeladn
 */
public class RetinotopicMatrix {

    private Array2D<RetinotopicCognit> matrix;

    public RetinotopicMatrix() {
        this.matrix = new Array2D<>();
    }

    public RetinotopicMatrix(Identificator preIdentificator0, Activation activation0) {
        this.matrix = new Array2D<>();
        this.merge(preIdentificator0, activation0);
    }

    private void merge(Identificator preIdentificator0, Activation activation0){
        Reference2D size = activation0.size();
        Identificator identificator;
        double value;
        for(int row = 0; row<size.getRows(); row+=1){
            for(int col =0; col<size.getCols(); col+=1){
                value = activation0.getValue(col,row);
                identificator = new Identificator(value,col,row,activation0.getDepthIndex());
            }
        }
    }

}
