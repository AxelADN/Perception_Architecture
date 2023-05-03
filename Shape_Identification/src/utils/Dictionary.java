/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import config.ConfigFile;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 *
 * @author axeladn
 */
public class Dictionary {
	
	private String areaCategory;
	private HashMap<String,Byte> keyWords;
	private ArrayList<String> types;
	private ArrayList<String> subTypes;
	
	public Dictionary(String areaCategory0){
		this.areaCategory = areaCategory0;
		this.keyWords = new HashMap<>();
		this.subTypes = new ArrayList<>();
		this.types = new ArrayList<>();
	}
	
	public void addKeyType(String name0, byte byte0){
		this.keyWords.put(name0, byte0);
		this.types.add(name0);
	}
	
	public void addKeySubtype(String name0, byte byte0){
		this.keyWords.put(name0, byte0);
		this.subTypes.add(name0);
	}
	
	public ArrayList<String> getTypes(){
		return (ArrayList<String>) this.types.clone();
	}
	
	public ArrayList<Byte> getKeyBytes(String rawName0){
		ArrayList<Byte> bytes = new ArrayList<>();
		Object[] orderKeyWords = this.keyWords.keySet().toArray();
		Arrays.sort(orderKeyWords);
		for(Object namePart : orderKeyWords){
			if(rawName0.contains(namePart.toString())){
				bytes.add(this.keyWords.get(namePart));
			}
		}
		return bytes;
	}
	
}
