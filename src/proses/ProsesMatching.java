package proses;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

class Matching{
	
	public void run(String inFile,String tempFile,String outFile,int method){
		
		Mat a = Highgui.imread(inFile);
		Mat b = Highgui.imread(tempFile);
		
		int resultCols = b.cols()-(a.cols()+1);
		int resultRows = b.rows()-(a.rows()+1);
		Mat result = new Mat(resultRows, resultCols, CvType.CV_32FC1);
		
		Imgproc.matchTemplate(b, a, result, method);
		Core.normalize(result, result,0,1,Core.NORM_MINMAX,-1,new Mat());
		
		MinMaxLocResult mmr = Core.minMaxLoc(result);
		
		Point matchLock;
		matchLock = mmr.maxLoc;
		
		Core.rectangle(b, matchLock, new Point(matchLock.x+a.cols(), matchLock.y+a.rows()), new Scalar(0, 0, 255));
		
		Highgui.imwrite(outFile, b);
	}
}

public class ProsesMatching {
	
	public ProsesMatching(){
		super();
	}

	private String i,t,lokasi;
	
	public void getInput(String a,String b){
		i = a;
		t = b;
	}
	
	public void Jalan(){
		lokasi = new JFileChooser().getFileSystemView().getDefaultDirectory().toString()+File.separator+"HasilMatching.jpg";
		//System.out.println(lokasi);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		new Matching().run(i, t, lokasi, Imgproc.TM_CCOEFF_NORMED);
	}
	
	public void Tampilkan(JLabel jl){
		File f = new File(lokasi);
		BufferedImage bfrImg;
		try {
			bfrImg = ImageIO.read(f);
			ImageIcon ico = new ImageIcon(bfrImg);
			jl.setIcon(ico);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
