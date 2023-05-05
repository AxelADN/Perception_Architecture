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
public class Array2D <T> implements Copyable{
	
	private HashMap<String,T> array2D;
	private int rows;
	private int cols;
	
	public Array2D(int cols0 , int rows0){
		this.array2D = new HashMap<>();
		this.cols = cols0;
		this.rows = rows0;
	}
	
	public Array2D(){
		this.array2D = new HashMap<>();
		this.cols = 0;
		this.rows = 0;
	}
	
	//for Copyable
	public Array2D(HashMap<String,T> array2D0, int cols0, int rows0){
		this.array2D = (HashMap<String,T>) array2D0.clone();
		this.cols = cols0;
		this.rows = rows0;
	}
	
	public void setSize(int[] size0)
	{
		this.cols = size0[0];
		this.rows = size0[1];
	}	
	
	public void add(int[] index, T object){
		this.checkIndexBounds(index);
		this.array2D.put(getIndexString(index), object);
	}
	
	public T get(int[] index){
		this.checkIndexBounds(index);
		this.checkEmptyness(index);
		return this.array2D.get(this.getIndexString(index));
	}
	
	private void checkIndexBounds(int[] index){
		if(index[0] >= cols || index[1] >= rows){
			throw new ArrayIndexOutOfBoundsException("Rows or Cols out of bounds!");
		}
	}
	
	public boolean isEmpty(){
		return this.array2D.isEmpty();
	}

	private String getIndexString(int[] index) {
		String str = new String();
		str += index[0] + "," + index[1];
		return str;
	}

	public void checkEmptyness(int[] index) {
		if(this.array2D.get(this.getIndexString(index)) == null){
			throw new NullPointerException("Index points to null value!");
		}
	}

	@Override
	public Array2D<T> copy() {
		return new Array2D<>(this.array2D, this.cols, this.rows);
	}

    public int[] size() {
        return new int[] {this.cols,this.rows};
    }
	
}
