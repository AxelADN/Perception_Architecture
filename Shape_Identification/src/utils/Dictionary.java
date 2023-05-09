/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.json.JSONTokener;

/**
 *
 * @author axeladn
 */
public class Dictionary {

	private static final HashSet<String> keyWords;
	private static final HashSet<String> typeKeyWords;
	private static final HashSet<String> subTypeKeyWords;
	private static final HashSet<String> areaKeyWords;
	
	private static final String DEFAULT = "DEFAULT";


	static {
		keyWords = new HashSet<>();
		typeKeyWords = new HashSet<>();
		subTypeKeyWords = new HashSet<>();
		areaKeyWords = new HashSet<>();
	}

	public static HashSet<String> parse(String str0) {
		HashSet<String> parsedArray = new HashSet<>();
		for (Object keyWord : Dictionary.keyWords) {
			if (str0.contains(keyWord.toString())) {
				parsedArray.add(keyWord.toString());
			}
		}
		return parsedArray;
	}

	public static String parseType(String str0) {
		for (Object keyWord : Dictionary.typeKeyWords) {
			if (str0.contains(keyWord.toString())) {
				return keyWord.toString();
			}
		}
		return Dictionary.DEFAULT;
	}

	public static HashSet<String> parseSubType(String str0) {
		HashSet<String> parsedArray = new HashSet<>();
		for (Object keyWord : Dictionary.subTypeKeyWords) {
			if (str0.contains(keyWord.toString())) {
				parsedArray.add(keyWord.toString());
			}
		}
		return parsedArray;
	}
	
	public static String parseArea(String str0) {
		for (Object keyWord : Dictionary.areaKeyWords) {
			if (str0.contains(keyWord.toString())) {
				return keyWord.toString();
			}
		}
		return Dictionary.DEFAULT;
	}
	
	

//-------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	private final String areaCategory;
	private final HashSet<String> types;
	private final HashSet<String> subTypes;

	public Dictionary(String areaCategory0) {
		this.areaCategory = areaCategory0;
		Dictionary.areaKeyWords.add(areaCategory);
		this.subTypes = new HashSet<>();
		this.types = new HashSet<>();
	}

	public void addKeyType(String name0) {
		Dictionary.keyWords.add(name0);
		Dictionary.typeKeyWords.add(name0);
		this.types.add(name0);
	}

	public void addKeySubtype(String name0) {
		Dictionary.keyWords.add(name0);
		Dictionary.subTypeKeyWords.add(name0);
		this.subTypes.add(name0);
	}

	public HashSet<String> getTypes() {
		return this.types;
	}

	public String getArea() {
		return areaCategory;
	}

}
