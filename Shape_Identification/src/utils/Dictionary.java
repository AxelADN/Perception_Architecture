/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import config.ConfigFile;
import java.io.File;
import java.util.HashMap;

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
	
	private HashMap<String,Integer[]> pairs;
	private String category;
	
	public Dictionary(String category0){
		this.category = category0;
		this.pairs = getDictConfig();
	}

	private HashMap<String, Integer[]> getDictConfig() {
		String dictFile = ConfigFile.dictFiles.get(this.category);
		return extractDict(dictFile);
	}

	private HashMap<String, Integer[]> extractDict(String dictFile) {
		try{
			File inputFile = new File(dictFile);
			SAXReader reader = new SAXReader();
			Document document = reader.read( inputFile );
			
			Element root = document.getRootElement();
			if(root.getText().equals(ConfigFile.XML_DictRoot.get(this.category))){
				
			}
			
			List<Node> nodes = 
		}
	}
	
}
