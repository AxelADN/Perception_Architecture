/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapeIdentification.areas.V1;

import java.util.HashMap;

/**
 *
 * @author axeladn
 */
public class V1_Metadata {
    
    public static final HashMap<String, double[]> excentricityPatches = new HashMap<>();
    public static final String FOVEA = "FOVEA";
    public static final String PARAFOVEA = "PARAFOVEA";
    public static final String PERIFOVEA = "PERIFOVEA";

    static {
        double[] foveaPatch = new double[]{0.33, 0.33, 0.33, 0.33};
        double[] paraFoveaPatch = new double[]{0.166, 0.166, 0.66, 0.66};
        double[] periFoveaPatch = new double[]{0.0, 0.0, 1.0, 1.0};

        V1_Metadata.excentricityPatches.put(V1_Metadata.FOVEA, foveaPatch);
        V1_Metadata.excentricityPatches.put(V1_Metadata.PARAFOVEA, paraFoveaPatch);
        V1_Metadata.excentricityPatches.put(V1_Metadata.PERIFOVEA, periFoveaPatch);

    }
    
}
