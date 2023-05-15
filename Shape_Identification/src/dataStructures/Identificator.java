/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import interfaces.Copyable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import utils.Dictionary;

/**
 *
 * @author axeladn
 */
public class Identificator implements Copyable {

    public static long UID = 0;
    public static final String AREA = "AREA";
    public static final String TYPE = "TYPE";
    public static final String SUBTYPE = "SUBTYPE";
    public static final String LOCATION = "LOCATION";
    public static final String LEVEL = "LEVEL";
    public static final String VALUE = "VALUE";
    public static final String EXCENTRICITY = "EXCENTRICITY";
    private static String X;
    private static String Y;
    private static String Z;

    private long id;
    private HashMap<String, Object> idArray;


    /*public Identificator(HashSet<Byte> idByte0) {
		Identificator.UID += 1;
		this.id = Identificator.UID;
		this.idByte = (HashSet<Byte>) idByte0.clone();
	}*/
    public Identificator(String str0) {
        this();
        this.idArray.put(Identificator.AREA, Dictionary.parseArea(str0));
        this.idArray.put(Identificator.TYPE, Dictionary.parseType(str0));
        this.idArray.put(Identificator.SUBTYPE, Dictionary.parseSubType(str0));

    }

    public Identificator(Identificator preIdentificator) {
        this();
        this.idArray.put(Identificator.AREA, preIdentificator.getArea());
        this.idArray.put(Identificator.TYPE, preIdentificator.getType());
        this.idArray.put(Identificator.SUBTYPE, preIdentificator.getSubType());
    }

    //for Copyable
    public Identificator(long id0, HashMap<String, Object> idArray0) {
        this.idArray = (HashMap<String, Object>) this.idArray.clone();
        this.id = id0;
    }

    public Identificator() {
        Identificator.UID += 1;
        this.id = Identificator.UID;
        this.idArray = new HashMap<>();
    }

    public Identificator(int x0, int y0, int level0, double value0, Identificator preIdentification0) {
        this();
        List<Integer> location = new ArrayList<>();
        location.add(x0);
        location.add(y0);
        this.idArray.put(Identificator.EXCENTRICITY, preIdentification0.getExcentricity());
        this.idArray.put(Identificator.LEVEL, level0);
        this.idArray.put(Identificator.LOCATION, location);
        this.idArray.put(Identificator.VALUE, value0);
        this.idArray.put(Identificator.AREA, preIdentification0.getArea());
        this.idArray.put(Identificator.TYPE, preIdentification0.getType());
        this.idArray.put(Identificator.SUBTYPE, preIdentification0.getSubType());
    }

    public HashSet<String> getSubType() {
        return (HashSet<String>) this.idArray.get(Identificator.SUBTYPE);
    }

    public String getType() {
        return (String) this.idArray.get(Identificator.TYPE);
    }

    public String getArea() {
        return (String) this.idArray.get(Identificator.AREA);
    }

    @Override
    public String toString() {
        String str = new String();
        str += this.id + "+";
        str += this.idArray.get(Identificator.EXCENTRICITY).toString() + "+";
        str += this.idArray.get(Identificator.AREA).toString() + "+";
        str += this.idArray.get(Identificator.TYPE).toString() + "+";
        str += this.idArray.get(Identificator.SUBTYPE).toString() + "+";
        str += this.idArray.get(Identificator.LEVEL).toString() + "+";
        str += this.idArray.get(Identificator.LOCATION).toString() + "+";
        str += this.idArray.get(Identificator.VALUE).toString();

        return str;
    }

    @Override
    public Identificator copy() {
        Identificator identificator = new Identificator(this.id, this.idArray);
        return identificator;
    }

    public void setExcentricity(String excentricity0) {
        this.idArray.put(Identificator.EXCENTRICITY, excentricity0);
    }

    public String getExcentricity() {
        return (String) this.idArray.get(Identificator.EXCENTRICITY);
    }

    public Reference2D getLocation() {
        return new Reference2D((List<Integer>) this.idArray.get(Identificator.LOCATION));
    }

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + (int) (this.id ^ (this.id >>> 32));
		hash = 31 * hash +  this.idArray.get(Identificator.EXCENTRICITY).hashCode();
		hash = 31 * hash +  this.idArray.get(Identificator.AREA).hashCode();
		hash = 31 * hash +  this.idArray.get(Identificator.LEVEL).hashCode();
		hash = 31 * hash +  this.idArray.get(Identificator.SUBTYPE).hashCode();
		hash = 31 * hash +  this.idArray.get(Identificator.TYPE).hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Identificator other = (Identificator) obj;
		if (this.id != other.id) {
			return false;
		}
		if(this.idArray.get(Identificator.EXCENTRICITY) != other.idArray.get(Identificator.EXCENTRICITY)){
			return false;
		}
		if(this.idArray.get(Identificator.EXCENTRICITY) != other.idArray.get(Identificator.EXCENTRICITY)){
			return false;
		}
	}
	
	

}
