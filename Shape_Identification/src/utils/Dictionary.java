/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author axeladn
 */
public class Dictionary {
	
	private static final HashMap<String,Byte> keyWords;
	
	static {
		keyWords = new HashMap<>();
	}
	
	public static HashSet<Byte> parse(String str0){
		HashSet<Byte> bytes = new HashSet<>();
		Object[] orderedDict = Dictionary.keyWords.keySet().toArray();
		Arrays.sort(orderedDict);
		for(Object keyWord : orderedDict){
			if(str0.contains(keyWord.toString())){
				bytes.add(Dictionary.keyWords.get(keyWord.toString()));
			}
		}
		return bytes;
	}
	
	
	
	
	private final String areaCategory;
	private final HashSet<String> types;
	private final HashSet<String> subTypes;
	
	public Dictionary(String areaCategory0){
		this.areaCategory = areaCategory0;
		this.subTypes = new HashSet<>();
		this.types = new HashSet<>();
	}
	
	public void addKeyType(String name0, byte byte0){
		Dictionary.keyWords.put(name0, byte0);
		this.types.add(name0);
	}
	
	public void addKeySubtype(String name0, byte byte0){
		Dictionary.keyWords.put(name0, byte0);
		this.subTypes.add(name0);
	}
	
	public ArrayList<String> getTypes(){
		return (ArrayList<String>) this.types.clone();
	}
	
	public String getArea(){
		return areaCategory;
	}
	
}
