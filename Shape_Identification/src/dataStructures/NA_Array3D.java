/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import interfaces.Copyable;
import java.util.HashMap;

/**
 *
 * @author axeladn
 */
public class NA_Array3D<T> implements Copyable {

	private HashMap<Integer, Array2D> array3D;
	private int rows;
	private int cols;
	private int depth;
	private int depthReference;

	public NA_Array3D(int cols0, int rows0, int depth0) {
		this.array3D = new HashMap<>();
		this.cols = cols0;
		this.rows = rows0;
		this.depth = depth0;
		this.depthReference = 0;
	}

	//for Copyable
	public NA_Array3D(HashMap<Integer, Array2D> array3D0, int cols0, int rows0, int depth0, int depthReference0) {
		this.array3D = new HashMap<>();
		for (int index : array3D0.keySet()) {
			this.array3D.put(index, array3D0.get(index));
		}
		this.cols = cols0;
		this.depth = depth0;
		this.depthReference = depthReference0;
		this.rows = rows0;
	}

	public NA_Array3D() {
		this.array3D = new HashMap<>();
		this.cols = 0;
		this.depth = 0;
		this.depthReference = 0;
		this.rows = 0;
	}

	public void add(int[] index, T object) {
		this.checkIndexBounds(index);
		Array2D<T> array2D;
		int[] index2D = this.getIndex2D(index);
		if (this.array3D.containsKey(index[2])) {
			array2D = this.array3D.get(index[2]);
			array2D.add(index2D, object);
			this.array3D.put(index[2], array2D);
		} else {
			array2D = new Array2D<>(this.cols, this.rows);
			array2D.add(index2D, object);
			this.array3D.put(index[2], array2D);
		}
	}

	public void add(Array2D<T> array2D) {
		this.array3D.put(this.depthReference, array2D);
		this.depthReference += 1;
	}

	public void add(int depth0, Array2D<T> array2D) {
		this.depth = depth0 + 1;
		int[] size = array2D.size();
		this.cols = size[0];
		this.rows = size[1];
		this.array3D.put(depth0, array2D);
	}

	public T get(int[] index) throws NullPointerException{
			this.checkIndexBounds(index);
			this.checkEmptyness(index);
			Array2D<T> array2D = this.array3D.get(index[2]);
			int[] index2D = this.getIndex2D(index);
			return array2D.get(index2D);
	}

	private int[] getIndex2D(int[] index) {
		int[] index2D = new int[2];
		index2D[0] = index[0];
		index2D[1] = index[1];
		return index2D;
	}

	private void checkIndexBounds(int[] index) {
		if (index[0] >= this.cols || index[1] >= this.rows || index[2] >= this.depth) {
			throw new ArrayIndexOutOfBoundsException("Rows or Cols out of bounds!");
		}
	}

	public boolean isEmpty() {
		return this.array3D.isEmpty();
	}

	private void checkEmptyness(int[] index) throws NullPointerException {
		if (this.array3D.get(index[2]) == null) {
			throw new NullPointerException("Index points to null value! ->  z: " + index[2]);
		}
		Array2D<T> aux2D = this.array3D.get(index[2]);
		int[] index2D = this.getIndex2D(index);
		aux2D.checkEmptyness(index2D);
	}

	@Override
	public NA_Array3D<T> copy() {
		return new NA_Array3D(this.array3D, this.rows, this.cols, this.depth, this.depthReference);
	}

	public HashMap<Integer, NA_Array3D> split() {
		HashMap<Integer, NA_Array3D> auxArrayMap = new HashMap<>();
		NA_Array3D<T> auxArray3D;
		for (int i : this.array3D.keySet()) {
			auxArray3D = new NA_Array3D<>();
			auxArray3D.add(i, this.array3D.get(i));
			auxArrayMap.put(i, auxArray3D);
		}
		return auxArrayMap;
	}

	public int[] size() {
		return new int[]{this.cols, this.rows};
	}

	public int depthIndex() {
		return this.depth - 1;
	}

	public int depthSize() {
		return this.array3D.keySet().size();
	}

}
