/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author axeladn
 */
public class ConfigFile {

	public static String SAVE_FILE = "/home/axeladn/Documents/Tesis_Doctorado/Perception_System/Perception_Architecture/Shape_Identification/saved_files/";
	
	public static enum Areas{
		DEFAULT_AREA,V1,V2,hV4,
	}
	
	public static enum AreaDataTypes{
		BORDERS,
	}

	public static final HashMap<String,String> DictFiles = new HashMap<>();
	
	public static final HashMap<String,String> XML_DictRoot = new HashMap<>();
	
	public static final HashMap<ConfigFile.Areas,ArrayList<ConfigFile.AreaDataTypes>> DATA_TYPES_PER_AREA = new HashMap<>();
	
	static {
		ArrayList<ConfigFile.AreaDataTypes> types = new ArrayList<>();
		types.add(AreaDataTypes.BORDERS);
		DATA_TYPES_PER_AREA.put(Areas.V1, types);
	}
	
	
	
	
}
