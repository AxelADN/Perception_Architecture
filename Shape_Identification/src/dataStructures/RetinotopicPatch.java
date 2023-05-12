/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author axeladn
 */
public class RetinotopicPatch {

	public static int maxLevel;
	public static double maxRetinotopicSize;
	public static double minRetinotopicSize;
	public static int retinotopicStep;

	static {
		RetinotopicPatch.maxLevel = 4;
		RetinotopicPatch.minRetinotopicSize = 1 / Math.pow(2, RetinotopicPatch.maxLevel-1);
	}

	private final int level;
	private Reference2D size;
	private HashMap<List, List> map;

	public RetinotopicPatch(int level0) {
		double plainSize = 1 / Math.pow(2, level0-1);
		1/plainSize
		this.size = new Reference2D(plainSize,plainSize);
		for(int i=0; i<this.size.getFactorX();i+=1){
			for(int j=0; j<this.size.getFactorY();j+=1){
				
			}
		}
		if (level0 > RetinotopicPatch.maxLevel) {
			this.level = RetinotopicPatch.maxLevel;
		}
	}

	/**
	 *
	 * @return
	 */
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 67 * hash + this.level;
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
		final RetinotopicPatch other = (RetinotopicPatch) obj;
		return this.level == other.level;
	}

}
