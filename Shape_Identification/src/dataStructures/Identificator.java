/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author axeladn
 */
public class Identificator {

	public static long UID = 0;
	
	private long id;
	private ArrayList<Byte> idByte;
	
	public Identificator(ArrayList<Byte> idByte0){
		Identificator.UID+=1;
		this.id = Identificator.UID;
		this.idByte = (ArrayList<Byte>) idByte0.clone();
	}
	
}
