/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import java.util.ArrayList;
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
		RetinotopicPatch.minRetinotopicSize = 1 / Math.pow(2, RetinotopicPatch.maxLevel - 1);
	}

	private final int level;
	private Reference2D size;
	private double blockSize;
	private HashMap<List, List> map;

	public RetinotopicPatch(int level0) {
		double blockSize = 1 / Math.pow(2, level0 - 1);
		this.map = new HashMap<>();
		int jj = 0;
		int ii = 0;
		for (double j = 0; j < 1; j += blockSize) {
			ii = 0;
			for (double i = 0; i < 1; i += blockSize) {
				List<Integer> index = new ArrayList<>();
				List<Double> pos = new ArrayList<>();
				index.add(ii);
				index.add(jj);
				pos.add(i);
				pos.add(j);
				this.map.put(index, pos);
				ii += 1;
			}
			jj += 1;
		}
		this.size = new Reference2D(ii, jj);
		this.blockSize = blockSize;
		if (level0 > RetinotopicPatch.maxLevel) {
			this.level = RetinotopicPatch.maxLevel;
		} else {
			this.level = level0;
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

	public Reference2D getSize() {
		return this.size;
	}
	
	public Reference2D getIndexPos(int i0, int j0){
		List<Integer> index = new ArrayList<>();
		index.add(i0);
		index.add(j0);
		List<Double> pos = this.map.get(index);
		return new Reference2D(pos.get(0),pos.get(1));
	}
	
	public double getBlockSize(){
		return this.blockSize;
	}

	public int getLevel() {
		return this.level;
	}

}
