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
	
	private HashMap<Integer[],T> array2D;
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
	public Array2D(HashMap<Integer[],T> array2D0, int cols0, int rows0){
		this.array2D = (HashMap<Integer[],T>) array2D0.clone();
		this.cols = cols0;
		this.rows = rows0;
	}
	
	public void setSize(int[] size0)
	{
		this.cols = size0[0];
		this.rows = size0[1];
	}	
	
	public void add(int[] index, T object) throws ArrayIndexOutOfBoundsException{
		this.checkIndexBounds(index);
		this.array2D.put(this.getIndexIntegerWrapper(index), object);
	}
	
	public T get(int[] index) throws NullPointerException{
		this.checkIndexBounds(index);
		this.checkEmptyness(index);
		return this.array2D.get(this.getIndexIntegerWrapper(index));
	}
	
	private void checkIndexBounds(int[] index) throws ArrayIndexOutOfBoundsException{
		if(index[0] >= this.cols || index[1] >= this.rows){
			throw new ArrayIndexOutOfBoundsException("Rows or Cols out of bounds! --> cols: " + index[0] + ", rows: " + index[1]);
		}
	}
	
	public boolean isEmpty(){
		return this.array2D.isEmpty();
	}

	private Integer[] getIndexIntegerWrapper(int[] index) {
		return new Integer[]{index[0],index[1]};
	}

	public void checkEmptyness(int[] index) throws NullPointerException{
		if(this.array2D.get(this.getIndexIntegerWrapper(index)) == null){
			throw new NullPointerException("Index points to null value! --> x: " + index[0] + ", y: " + index[1]);
		}
	}

	@Override
	public Array2D<T> copy() {
		return new Array2D<>(this.array2D, this.cols, this.rows);
	}

    public int[] size() {
        return new int[] {this.cols,this.rows};
    }

	void print() {
		String str = new String();
		str += "[";
		for(int i=0; i<this.rows; i+=0){
			str+="[";
			for(int j=0; j<this.cols; j+=1){
				str += this.get(new int[]{cols,rows});
				str += ",";
			}
			str+= "],\n";
		}
		str+="]";
		System.out.println(str);
	}
	
}
