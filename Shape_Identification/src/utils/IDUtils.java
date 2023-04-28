/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author axeladn
 */
public class IDUtils {

	public static String toString(ArrayList<Byte> ID) {
		Object[] ids =  ID.toArray();
		String idString = new String();
		for(Object id : ids){
			idString = idString+id.toString()+"0";
		}
		return idString;
	}
	
}
