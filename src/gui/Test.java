package gui;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat m = Mat.eye(3, 3, CvType.CV_8UC1);
		System.out.println("mat \n"+m.dump());
	}

}
