/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapeIdentification.areas.V1;

import dataStructures.Matrix;
import java.util.HashSet;
import utils.Dictionary;

/**
 *
 * @author axeladn
 */
public class V1 {

    private static final V1_Populate populate;
    private static final V1_Data data;

    static {
        populate = new V1_Populate();
        data = new V1_Data();
    }

    public static HashSet<Matrix> requestVisualID(String excentricity0) {
        return V1.data.getIdStructure(excentricity0);
    }

    public V1() {

        Dictionary dict;
        dict = new Dictionary("V1");

        dict.addKeyType("ComplexCells");
        dict.addKeyType("DoubleOpponent");
        dict.addKeyType("HyperComplexCells");
        dict.addKeyType("MergedHyperComplexCells");
        dict.addKeyType("SimpleCells");
        dict.addKeySubtype("Or_0");
        dict.addKeySubtype("Or_1");
        dict.addKeySubtype("Or_2");
        dict.addKeySubtype("Or_3");
        dict.addKeySubtype("Bank_0");
        dict.addKeySubtype("Bank0");
        dict.addKeySubtype("Eye_0");
        dict.addKeySubtype("Dp");
        dict.addKeySubtype("Kp");
        dict.addKeySubtype("Lp");
        dict.addKeySubtype("Type_0");
        dict.addKeySubtype("Type_1");
        dict.addKeySubtype("Phase1");
        dict.addKeySubtype("Phase2");

        V1.data.setDict(dict);
        this.init();
    }

    public void init() {
        V1.data.setData(V1.populate.processData());
        this.showImgs(2);

    }

    private void showImgs(int level0) {

        for (Matrix matrix : V1.data.getUnorderedData()) {
            matrix.showImg(level0);
        }
    }

}
