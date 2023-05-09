/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import dataStructures.Array2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author axeladn
 */
public class DataConversion {

	public static byte[] IntToByte(int data) {
		byte[] bytes = ByteBuffer.allocate(Integer.BYTES).putInt(data).array();
		return bytes;
	}
	
	public static ArrayList<Byte> IntToByteArray(int data){
		ArrayList<Byte> byteArray = new ArrayList<>();
		byte[] bytes = ByteBuffer.allocate(Integer.BYTES).putInt(data).array();
		for(byte b : bytes){
			byteArray.add(b);
		}
		return byteArray;
	}

	public static byte[] LongToByte(long data) {
		byte[] bytes = ByteBuffer.allocate(Long.BYTES).putLong(data).array();
		return bytes;
	}

	public static byte[] DoubleToByte(double data) {
		byte[] bytes = ByteBuffer.allocate(Double.BYTES).putDouble(data).array();
		return bytes;
	}

	public static ArrayList<Byte> DoubleToByteArray(double data) {
		ArrayList<Byte> byteArray = new ArrayList<>();
		byte[] bytes = ByteBuffer.allocate(Double.BYTES).putDouble(data).array();
		for (byte b : bytes) {
			byteArray.add(b);
		}
		return byteArray;
	}

	public static ArrayList<Double> BytesToDoubleArray(byte[] bytes) {
		ArrayList<Double> doubles = new ArrayList<>();
		byte[] aux;
		for (int i = 0; i < bytes.length; i += Double.BYTES) {
			aux = new byte[Double.BYTES];
			for (int j = 0; j < Double.BYTES; j++) {
				aux[j] = bytes[j + i];
			}
			doubles.add(ByteToDouble(aux));
		}
		return doubles;
	}

	public static double ByteToDouble(byte[] data) {
		ByteBuffer wrapped = ByteBuffer.wrap(data);
		return wrapped.getDouble();
	}

	public static int ByteToInt(byte[] data) {
		ByteBuffer wrapped = ByteBuffer.wrap(data);
		return wrapped.getInt();
	}

	public static long ByteToLong(byte[] data) {
		ByteBuffer wrapped = ByteBuffer.wrap(data);
		return wrapped.getLong();
	}
	
	public static Array2D MatToArray2DDouble(Mat mat0){
		Array2D<Double> array2D =  new Array2D<>(mat0.cols(),mat0.rows());
		int[] index = new int[2];
		int i;
		double[] data = new double[(int)mat0.total()];
		mat0.convertTo(mat0, CvType.CV_64F);
		mat0.get(0, 0, data);
		for(int row=0; row<mat0.rows(); row+=1){
			for(int col=0; col<mat0.cols(); col+=1){
				index[0] = col;
				index[1] = row;
				i = (mat0.cols() * row) + col;
				array2D.add(index, data[i]);
			}
		}
		return array2D;
	}
	
	
	
	
	
	
	

	public static Mat ByteToMat(byte[] bytes, int cols, int rows, int type) {
		Mat receivedImg = new Mat(new Size(cols, rows), type);
		receivedImg.put(0, 0, bytes);
		return receivedImg;
	}

	public static Mat ByteToMatD(byte[] bytes, int cols, int rows) {
		Mat receivedImg = new Mat(new Size(cols, rows), CvType.CV_64FC1);
		ArrayList<Double> doubleArray = BytesToDoubleArray(bytes);
		double[] matDoubles = new double[doubleArray.size()];
		for (int i = 0; i < matDoubles.length; i++) {
			matDoubles[i] = doubleArray.get(i);
		}
		receivedImg.put(0, 0, matDoubles);
		return receivedImg;
	}

	public static byte[] MatToByte(Mat img) {
		byte[] imgBytes = new byte[(int) (img.total() * img.channels())];
		img.get(0, 0, imgBytes);
		return imgBytes;
	}

	public static byte[] MatToByteD(Mat img) {
		ArrayList<Byte> byteArray = new ArrayList<>();
		byte[] imgBytes;
		double[] imgDoubles = new double[(int) (img.total() * img.channels())];
		img.get(0, 0, imgDoubles);
		for (int i = 0; i < imgDoubles.length; i++) {
			byteArray.addAll(DoubleToByteArray(imgDoubles[i]));
		}
		imgBytes = new byte[byteArray.size()];
		for (int i = 0; i < imgBytes.length; i++) {
			imgBytes[i] = byteArray.get(i);
		}
		return imgBytes;
	}

	public static Mat LongToMat(long val, int cols, int rows) {
		Mat outputMat = new Mat(cols, rows, CvType.CV_64FC1);
		outputMat.setTo(new Scalar(val));

		return outputMat;
	}

	public static byte[] MatKPToByte(MatOfKeyPoint img) {
		float[] imgFloats = new float[(int) (img.total() * img.channels())];
		img.get(0, 0, imgFloats);
		ByteBuffer byteBuffer = ByteBuffer.allocate(imgFloats.length * 4);
		for (float x : imgFloats) {
			byteBuffer.putFloat(x);
		}
		return byteBuffer.array();
	}

	public static String DoubleArrayToString(ArrayList<Double> objectData) {
		StringBuilder str = new StringBuilder();
		objectData.forEach((obj) -> {
			str.append(obj.toString());
		});
		return str.toString();
	}

	public static BufferedImage Mat2Img(Mat img) {
		
		try {
			MatOfByte matOfByte = new MatOfByte();
			Imgcodecs.imencode(".jpg", img, matOfByte);
			byte[] byteArray = matOfByte.toArray();
			InputStream in = new ByteArrayInputStream(byteArray);
			return ImageIO.read(in);
			
			
		} catch (IOException ex) {
			Logger.getLogger(DataConversion.class.getName()).log(Level.SEVERE, null, ex);
			return new BufferedImage(0, 0, 0);
		}
	}

	public static Mat Img2Mat(BufferedImage buffImg) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(buffImg, "jpg", byteArrayOutputStream);
			byteArrayOutputStream.flush();
			return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.IMREAD_UNCHANGED);
		} catch (IOException ex) {
			Logger.getLogger(DataConversion.class.getName()).log(Level.SEVERE, null, ex);
		}
		return new Mat();
	}

	public static double[] doubleMat2Vect(double[][] mat) {
		double[] vect = new double[mat.length * mat[0].length];
		int index = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				vect[index] = mat[i][j];
				index++;
			}
		}
		return vect;
	}

}
