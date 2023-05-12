/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import utils.ImageUtils;

/**
 *
 * @author axeladn
 */
public class Matrix {

	private Identificator preIdentificator;
	//private HashSet<RetinotopicMatrix> retinotopicMatrices;
	private HashMap<RetinotopicPatch, Array2D> retinotopicPatches;
	private Mat mainActivation;

	public Matrix() {
		this.preIdentificator = new Identificator();
		//this.retinotopicMatrices = new HashSet<>();
		this.mainActivation = new Mat();
	}

	public Matrix(String path0, Mat mat0) {
		//this.retinotopicMatrices = new HashSet<>();
		this.preIdentificator = new Identificator(path0);
		this.mainActivation = mat0;
		this.retinotopicPatches = new HashMap<>();
		
		Array2D<Mat> subActivationPerLevel;
		for (int level = 1; level <= RetinotopicPatch.maxLevel; level += 1) {
			RetinotopicPatch patch = new RetinotopicPatch(level);
			subActivationPerLevel = new Array2D<>(patch.getSize().getArray());
			Reference2D sizePatch = patch.getSize();
			double rectX = 0;
			double rectY = 0;
			double rectW = 0;
			double rectH = 0;
			for (int j = 0; j < sizePatch.getSizeY(); j += 1) {
				for (int i = 0; i < sizePatch.getSizeX(); i += 1) {
					rectX = patch.getIndexPos(i, j).getFactorX() * this.mainActivation.cols();
					rectY = patch.getIndexPos(i, j).getFactorY() * this.mainActivation.rows();
					rectW = patch.getBlockSize() * this.mainActivation.cols();
					rectH = patch.getBlockSize() * this.mainActivation.rows();
					Rect block = new Rect(new double[]{rectX, rectY, rectW, rectH});
					subActivationPerLevel.add(new int[]{i, j}, this.mainActivation.submat(block));
				}
			}
			this.retinotopicPatches.put(patch, subActivationPerLevel);
		}

	}

	public void showImg(int level0) {
		Identificator ID;
		if (level0 < 0) {
			for (RetinotopicPatch level : this.retinotopicPatches.keySet()) {
				Array2D<Mat> matArray = this.retinotopicPatches.get(level);
				for (int j = 0; j < matArray.size()[1]; j += 1) {
					for (int i = 0; i < matArray.size()[0]; i += 1) {
						ID = new Identificator(i, j, level.getLevel(), -1, this.preIdentificator);
						ImageUtils.showImg(matArray.get(new int[]{i, j}), ID.toString());
					}
				}
			}
		} else {
			Array2D<Mat> matArray = this.retinotopicPatches.get(new RetinotopicPatch(level0));
			for (int j = 0; j < matArray.size()[1]; j += 1) {
				for (int i = 0; i < matArray.size()[0]; i += 1) {
					ID = new Identificator(i, j, level0, -1, this.preIdentificator);
					ImageUtils.showImg(matArray.get(new int[]{i, j}), ID.toString());
				}
			}
		}
	}

}
