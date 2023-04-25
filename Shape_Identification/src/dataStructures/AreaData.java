/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import config.ConfigFile;
import control.Log;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author axeladn
 */
public class AreaData {
	
	private HashMap<ConfigFile.AreaDataTypes,ArrayList<Matrix>> data;
	private final String currentArea;
	
	public AreaData(String area){
		if(!ConfigFile.DATA_TYPES_PER_AREA.containsKey(ConfigFile.Areas.valueOf(area))){
			Log.error(control.Error.error.AREA_NOT_DEFINED,this.getClass().getSimpleName());
			currentArea = ConfigFile.Areas.DEFAULT_AREA.name();
			data = new HashMap<>();
			return;
		}
		currentArea = ConfigFile.Areas.valueOf(area).name();
		data = new HashMap<>();
	}
	
	public void add(Matrix matrix0, String type0){
		if(!data.containsKey(ConfigFile.AreaDataTypes.valueOf(type0))){
			Log.error(control.Error.error.AREA_TYPE_NOT_DEFINED,this.getClass().getSimpleName());
			return;
		}
		ArrayList<Matrix> temp = data.get(ConfigFile.AreaDataTypes.valueOf(type0));
		temp.add(matrix0);
		data.put(ConfigFile.AreaDataTypes.valueOf(type0),temp);
	}
	
	public void set(ArrayList<Matrix> matrices, String type0){
		if(!data.containsKey(ConfigFile.AreaDataTypes.valueOf(type0))){
			Log.error(control.Error.error.AREA_TYPE_NOT_DEFINED,this.getClass().getSimpleName());
			return;
		}
		data.put(ConfigFile.AreaDataTypes.valueOf(type0), matrices);
	}
	
}
