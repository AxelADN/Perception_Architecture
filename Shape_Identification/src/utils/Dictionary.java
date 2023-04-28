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
	
	private HashMap<String,ArrayList<Byte>> pairs;
	private String category;
	private HashMap<String,Byte> keyWords;
	
	public Dictionary(String category0){
		this.category = category0;
		this.pairs = new HashMap<>();
		this.keyWords = new HashMap<>();
	}

	public boolean add(String name0, ArrayList<Byte> id0){
		if(pairs.containsKey(name0)){
			return false;
		}
		pairs.put(name0, id0);
		return true;
	}
	
	public void setKeyWords(HashMap<String,Byte> keyWords0){
		keyWords = keyWords0;
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
