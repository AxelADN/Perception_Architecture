/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapeIdentification.areas.LO1;

import utils.Dictionary;

/**
 *
 * @author axeladn
 */
public class LO1 {
    
    private LO1_Populate populate;
    private LO1_Data data;

    public LO1() {

        Dictionary dict = new Dictionary("LO1");
        dict.addKeyType("Retinotopic_Proximity_FOVEA");
        
        populate = new LO1_Populate();
        data = new LO1_Data();
        
    }
    
    private void init(){
        
        data.processData(populate.requestVisualData());
        
    }

}
