/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapeIdentification.areas.LO1;

import dataStructures.Activation;
import dataStructures.Array2D;
import dataStructures.Graph;
import dataStructures.Matrix;
import dataStructures.RetinotopicPatch;
import java.util.ArrayList;
import java.util.HashSet;
import utils.Dictionary;

/**
 *
 * @author axeladn
 */
public class LO1_Data {

    private HashSet<Dictionary> dictionaries;
    private Graph<Graph> totalGraph;

    public LO1_Data() {
        this.dictionaries = new HashSet<>();
        this.totalGraph = new Graph<>();
    }

    public void setDict(Dictionary dict0) {
        this.dictionaries.add(dict0);
    }

    public void processData(HashSet<Matrix> matrices0) {
        Array2D<Activation> activationMatrix;
        for (Matrix matrix : matrices0) {
            for (int level = 1; level <= RetinotopicPatch.maxLevel; level += 1) {
                activationMatrix = matrix.get(level);
                this.generateGraph(level, activationMatrix);
            }

        }
    }

    private void generateGraph(int level0, Array2D<Activation> activationMatrix0) {
        Graph<Activation> localGraph = new Graph<>();
        for (int j = 0; j < activationMatrix0.size()[1]; j += 1) {
            for (int i = 0; i < activationMatrix0.size()[0]; i += 1) {
this.buildAdjacencyByProximityH(activationMatrix0.get(new int[]{i,j}),activationMatrix0.getCol(i));
            }
        }
    }

    private void buildAdjacencyByProximityH(Activation currentActivation,ArrayList<Activation> activationArray0) {
        HashSet<Activation> adjacentActivations = new HashSet<>();
		HashMap<Activation,HashSet<ArrayList<Activation>>> subGraph
        for (int j = 0; j < activationArray0.size(); j += 1) {
            if()
        }
    }

}
