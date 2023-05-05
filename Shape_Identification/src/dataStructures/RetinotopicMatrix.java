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
        this.integrate(preIdentificator0, activation0);
    }

    private void integrate(Identificator preIdentificator0, Activation activation0){
        Reference2D size = activation0.size();
        for(int row = 0; row<activation0.rows(); row+=1){
            for(int col =0; col<activation0.cols(); col+=1){
                
            }
        }
    }

}
