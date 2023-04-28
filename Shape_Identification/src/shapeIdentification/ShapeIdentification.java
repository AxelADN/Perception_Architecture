/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package shapeIdentification;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import shapeIdentification.areas.V1;
import utils.ImageUtils;

/**
 *
 * @author axeladn
 */
public class ShapeIdentification {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		//ImageUtils.showImg(Mat.zeros(new Size(200,200), CvType.CV_8U), "01010101");
		V1 v1 = new V1();
		v1.Retrieval();

	}

}
