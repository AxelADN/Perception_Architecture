/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import interfaces.Copyable;
import java.util.HashSet;
import utils.Dictionary;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author axeladn
 */
public class Identificator implements Copyable {

	public static long UID = 0;

	private long id;
	private JSONObject descriptor;
	private JSONArray idArray;

	/*public Identificator(HashSet<Byte> idByte0) {
		Identificator.UID += 1;
		this.id = Identificator.UID;
		this.idByte = (HashSet<Byte>) idByte0.clone();
	}*/

	public Identificator(String str0) {
		Identificator.UID += 1;
		this.id = Identificator.UID;
		this.idByte = Dictionary.parse(str0);
	}

	//for Copyable
	public Identificator(long id0, HashSet<Byte> idByte0) {
		this.idByte = (HashSet<Byte>) idByte0.clone();
		this.id = id0;
	}

	public Identificator() {
		Identificator.UID += 1;
		this.id = Identificator.UID;
		this.idByte = new HashSet<>();
	}

	public Identificator(double value0, int col0, int row0, int depthIndex0) {
		
	}

	@Override
	public Identificator copy() {
		Identificator identificator = new Identificator(this.id, this.idByte);
		return identificator;
	}

}
