/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

/**
 *
 * @author axeladn
 */
public class Reference2D {
    
    private int referenceX;
    private int referenceY;
    
    public Reference2D(int x0, int y0){
        this.referenceX = x0;
        this.referenceY = y0;
    }
    
    public Reference2D(int[] xy0){
        this.referenceX = xy0[0];
        this.referenceY = xy0[1];
    }
    
    public int[] getArray(){
        return new int[]{this.referenceX,this.referenceY};
    }
    
    public int getRows(){
        return this.referenceY;
    }
    
    public int getCols(){
        return this.referenceX;
    }
    public int[] getSize(){
        return this.getArray();
    }
    
    public int getSizeX(){
        return this.getCols();
    }
    
    public int getSizeY(){
        return this.getRows();
    }
    
    public int getRow(){
        return this.getRows();
    }
    
    public int getCol(){
        return this.getCols();
    }
    
    public int getX(){
        return this.getSizeX();
    }
    
    public int getY(){
        return this.getSizeY();
    }
    
//----------------------------------------------------------------------
    
    public void setArray(int[] array0){
        this.referenceX = array0[0];
        this.referenceY = array0[1];
    }
    
    public void setRows(int rows0){
        this.referenceY = rows0;
    }
    
    public void setCols(int cols0){
        this.referenceX = cols0;
    }
    public void setSize(int[] size0){
        this.setArray(size0);
    }
    
    public void setSizeX(int sizeX0){
        this.setCols(sizeX0);
    }
    
    public void setSizeY(int sizeY0){
        this.setRows(sizeY0);
    }
    
    public void setRow(int row0){
        this.setRows(row0);
    }
    
    public void setCol(int col0){
        this.setCols(col0);
    }
    
    public void setX(int x0){
        this.setSizeX(x0);
    }
    
    public void setY(int y0){
        this.setSizeY(y0);
    }
    
}
